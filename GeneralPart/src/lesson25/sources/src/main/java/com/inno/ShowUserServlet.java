package lesson25.sources.src.main.java.com.inno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* Сервлет отображающий друзей пользователя */
@WebServlet("/showuser")
public class ShowUserServlet extends HttpServlet {
    @Inject
    private UsersDB usersDB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        if (userId == null) {
            throw new ServletException("Missing parameter id");
        }

        User user = usersDB.getUser(userId);
        if (user == null) {
            resp.setStatus(404);
            req.getRequestDispatcher("notfound.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("model", user);
        req.getRequestDispatcher("WEB-INF/jsp/showuser.jsp").forward(req, resp);
    }
}