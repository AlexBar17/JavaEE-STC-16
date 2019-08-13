package lesson26.task01.src.main.java.com.inno;

import javax.ejb.EJB;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* Используем MD5 Using MessageDigest Class для хеширования паролей */
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* Бин для работы с имитацией БД */
@EJB
public class UsersDB {

    static Map<String, User> userMap = new HashMap<>();

    public UsersDB() {
        /* Инициализация и добавление пользователей для имитации БД */
        addUser("Pedro", "1234", "1@1.com", "7C6A180B36896A0A8C02787EEAFB0E4C", new String[]{"Rodrigez", "Mike", "David"}); // "password1"
        addUser("Rodrigez", "666", "2@1.com","6CB75F652A9B52798EB6CF2201057C73", new String[]{"Pedro", "Mike", "David", "Tiffany"}); // "password2"
        addUser("Mike", "2323", "3@1.com", "819B0643D6B89DC9B579FDFC9094F28E", new String[]{"Rodrigez", "David", "Pedro"}); // "password3"
        addUser("David", "2221", "4@1.com", "34CC93ECE0BA9E3F6F235D4AF979B16C", new String[]{"Rodrigez", "Mike", "Pedro"}); // "password4"
        addUser("Tiffany", "1111", "5@1.com", "DB0EDD04AAAC4506F7EDAB03AC855D56", new String[]{"Rodrigez", "Mike"}); // "password5"
        addUser("Ilia", "999", "6@1.com", "218DD27AEBECCECAE69AD8408D9A36BF", new String[]{"David", "Mike"}); // "password6"
        addUser("Max", "777", "7@1.com", "00CDB7BB942CF6B290CEB97D6ACA64A3", new String[]{"David", "Mike", "Rodrigez", "Ilia"}); // "password7"
    }

    /* Метод создающий юзера посредством класса Builder, реализующего соответствующий паттерн */
    private void addUser(String name, String phone, String email, String password, String[] friends) {
        User user = new User.Builder(name)
                .withPhone(phone)
                .withEmail(email)
                .withPassword(password)
                .build();
        user.setFriends(Arrays.asList(friends));
        userMap.put(name, user);
    }

    public User getUser(String userId) {
        return userMap.get(userId);
    }

    public Collection<User> getAllUsers() {
        return userMap.values();
    }

    /* Метод для проверки пароля пользователя из имитации БД */
    public static User findUser(String userName, String password) {
        User u = userMap.get(userName);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

    /* Метод для получения хешей паролей */
    public static String getHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest);
        return myHash;
    }
}