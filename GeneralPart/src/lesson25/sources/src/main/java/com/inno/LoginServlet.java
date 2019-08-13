package lesson25.sources.src.main.java.com.inno;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Сервлет с возможностью авторизации с перенаправлением на страницу /users */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }
    @Inject
    private UsersDB usersDB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/loginView.jsp");
        dispatcher.forward(request, response);
    }

    /* Метод, который:
    * 1) отвечает за авторизацию;
    * 2) вызывает метод getHash() UsersDB для хеширования введенного пароля пользователем */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String passwordString = request.getParameter("password");
        String password = null;
        try {
            password = usersDB.getHash(passwordString);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User userAccount = UsersDB.findUser(userName, password);

        /* При неудачном входе в систему перенаправить на страницу /notfound */
        if (userAccount == null) {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/notfound.jsp");
            dispatcher.forward(request, response);
            return;
        }

        AppUtils.storeLoginedUser(request.getSession(), userAccount);

        /* После успешного входа в систему перенаправить на страницу /users */
        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception e) {
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
        if (requestUri != null) {
            response.sendRedirect(requestUri);
        } else {
            response.sendRedirect(request.getContextPath() + "/users");
        }
    }
}