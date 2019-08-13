package lesson22.task01.src.main.java.ru.inno.stc14.servlet;


import lesson22.task01.src.main.java.ru.inno.stc14.dao.ConnectionManager;
import lesson22.task01.src.main.java.ru.inno.stc14.dao.jdbc.DBConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class AppContextListener implements ServletContextListener {
    private Logger logger = Logger.getLogger(AppContextListener.class.getName());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        String dbURL = "jdbc:h2:mem:students";
        String user = "sa";
        String pwd = "";



        try {
            final String dbInit = ";INIT=RUNSCRIPT FROM '" + ctx.getRealPath("/db/data.sql").replace("\\", "/") + "';";
            ConnectionManager connectionManager = new DBConnectionManager(dbURL + dbInit, user, pwd);
            ctx.setAttribute("DBConnection", connectionManager.getConnection());
            System.out.println("DB Connection initialized successfully.");

            Connection con = connectionManager.getConnection();

            Statement st = con.createStatement();

            st.execute("insert into person (login, pass) values ('al', '21')");

            st.execute("insert into person (login, pass) values ('bl', '12')");


        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while initializing the database connection.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection con = (Connection) sce.getServletContext().getAttribute("DBConnection");
        try {
            con.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while closing the database connection.", e);
        }
    }
}
