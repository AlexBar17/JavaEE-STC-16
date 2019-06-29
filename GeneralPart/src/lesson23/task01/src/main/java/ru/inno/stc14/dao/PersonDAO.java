package lesson23.task01.src.main.java.ru.inno.stc14.dao;


import lesson23.task01.src.main.java.ru.inno.stc14.entity.Person;

import java.util.List;

public interface PersonDAO {

    List<Person> getList();

    boolean addPerson(Person person);
}
