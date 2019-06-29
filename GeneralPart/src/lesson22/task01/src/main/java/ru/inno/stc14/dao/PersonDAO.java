package lesson22.task01.src.main.java.ru.inno.stc14.dao;



import lesson22.task01.src.main.java.ru.inno.stc14.entity.Person;

import java.util.List;

public interface PersonDAO {

    List<Person> getList();

    boolean checkPerson(Person person);
}
