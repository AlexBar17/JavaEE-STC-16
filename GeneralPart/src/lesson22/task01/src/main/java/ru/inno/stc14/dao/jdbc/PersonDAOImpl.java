package lesson22.task01.src.main.java.ru.inno.stc14.dao.jdbc;


import lesson22.task01.src.main.java.ru.inno.stc14.dao.PersonDAO;
import lesson22.task01.src.main.java.ru.inno.stc14.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonDAOImpl implements PersonDAO {
    private static Logger logger = Logger.getLogger(PersonDAOImpl.class.getName());
    private final Connection connection;

    public PersonDAOImpl(Connection con) {
        this.connection = con;
    }

    private static final String INSERT_PERSON_SQL_TEMPLATE =
            "insert into person (login, pass) values (?, ?)";
    private static final String SELECT_PERSON_SQL_TEMPLATE =
            "select id, login, pass from person";

    @Override
    public List<Person> getList() {
        List<Person> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_SQL_TEMPLATE)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Person person = new Person();
                    person.setId(resultSet.getInt(1));
                    person.setLogin(resultSet.getString(2));
                    person.setPass(resultSet.getString(3));
                    result.add(person);
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An exception occurred on the DAO layer.", e);
        }
        return result;
    }

    @Override
    public boolean checkPerson(Person person) {
        System.out.println("Enter");
        try (PreparedStatement statement = connection.prepareStatement("select login, pass from person where login = ? and pass = ?")) {
            statement.setString(1, person.getLogin());
            System.out.println(person.getLogin());
            statement.setString(2, person.getPass());
            System.out.println(statement.toString());
            ResultSet rs = statement.executeQuery();

//            String st = "select login, pass from person where login = 'al' and pass = '21'";
//Statement st1 = connection.createStatement();
// rs = st1.executeQuery(st);
            rs.first();
            System.out.println(rs.toString());
            if (rs.getRow() > 0) {
                System.out.println("Удачный вызов " + rs.getString(1));
                return true;

            } else {
                System.out.println("Неудачный вызов " + rs.getRow());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An exception occurred on the DAO layer.", e);
            return false;
        }
    }
}
