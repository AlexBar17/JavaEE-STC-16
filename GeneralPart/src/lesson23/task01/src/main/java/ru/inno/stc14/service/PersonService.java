package lesson23.task01.src.main.java.ru.inno.stc14.service;

import ru.inno.stc14.entity.Person;

import java.io.IOException;
import java.util.List;

public interface PersonService {

    List<Person> getList();

    boolean addPerson(String name, String birth, String email, String phone) throws IOException;
}
