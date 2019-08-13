package lesson22.task01.src.main.java.ru.inno.stc14.service;


import lesson22.task01.src.main.java.ru.inno.stc14.dao.PersonDAO;
import lesson22.task01.src.main.java.ru.inno.stc14.dao.jdbc.PersonDAOImpl;
import lesson22.task01.src.main.java.ru.inno.stc14.entity.Person;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonServiceImpl implements PersonService {
    private Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());
    private final PersonDAO personDAO;

    public PersonServiceImpl(Connection con) {
        personDAO = new PersonDAOImpl(con);
    }

    @Override
    public List<Person> getList() {
        return personDAO.getList();
    }

    @Override
    public boolean checkPerson(String login, String pass) {
        Person person = new Person();
        person.setLogin(login);

        person.setPass(pass);

        return personDAO.checkPerson(person);
    }

//    private Date safeParseDate(String birthStr) {
//        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//        try {
//            return format.parse(birthStr);
//        } catch (ParseException e) {
//            logger.log(Level.SEVERE, "Date parsing error", e);
//            throw new RuntimeException(e);
//        }
//    }

}
