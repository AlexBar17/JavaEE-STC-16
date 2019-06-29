package lesson22.task01.src.main.java.ru.inno.stc14.servlet;

        import ru.inno.stc14.service.PersonService;
        import ru.inno.stc14.service.PersonServiceImpl;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;
        import java.sql.Connection;

@WebServlet(name = "person", urlPatterns = "/person")
public class PersonServlet extends HttpServlet {
    private PersonService person;

    @Override
    public void init() throws ServletException {
        System.out.println("Я начался");

        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        person = new PersonServiceImpl(connection);
        super.init();


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/form.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        System.out.println("Me too");
        HttpSession session = req.getSession(true);
        session.setAttribute("test", "tt");

        req.setCharacterEncoding("utf-8");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if (person.checkPerson(login, pass)) {
            session.setAttribute("chek", "");
            resp.sendRedirect(req.getContextPath());
        } else {
            resp.sendRedirect("/students_war/person");
        }

    }

}
