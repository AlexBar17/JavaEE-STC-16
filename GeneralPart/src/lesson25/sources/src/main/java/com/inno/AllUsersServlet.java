package lesson25.sources.src.main.java.com.inno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/* Сервлет отображающий всех юзеров */
@WebServlet(urlPatterns = "/users", name = "Users")
public class AllUsersServlet extends HttpServlet {
    @Inject
    private UsersDB usersDB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> users = usersDB.getAllUsers();

        req.setAttribute("model", users);
        req.getRequestDispatcher("WEB-INF/jsp/allusers.jsp").forward(req, resp);
    }
}