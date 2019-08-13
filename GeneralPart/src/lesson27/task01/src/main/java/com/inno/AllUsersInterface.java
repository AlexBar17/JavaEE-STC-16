package lesson27.task01.src.main.java.com.inno;

import javax.servlet.ServletException;
import java.io.IOException;

/* Интерфейс для классов AllUsersServlet и AllUsersServletProxy для реализации структурного паттерна Proxy */
public interface AllUsersInterface {
    public void doGetTemp() throws ServletException, IOException;
}
