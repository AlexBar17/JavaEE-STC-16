package lesson22.task01.src.main.java.ru.inno.stc14.service;



import lesson22.task01.src.main.java.ru.inno.stc14.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getList();

    boolean checkPerson(String name, String birth);
}
