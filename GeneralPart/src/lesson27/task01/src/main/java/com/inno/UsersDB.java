package lesson27.task01.src.main.java.com.inno;

import javax.ejb.EJB;
import java.util.*;

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
        List<String> users = new ArrayList<>(Arrays.asList("Pedro", "Rodrigez", "Mike", "David", "Tiffany", "Ilia", "Max"));
        List<String> phones = new ArrayList<>(Arrays.asList("1234", "666", "2323", "2221", "1111", "999", "777"));
        List<String> emails = new ArrayList<>(Arrays.asList("1@1.com", "2@1.com", "3@1.com", "4@1.com", "5@1.com", "6@1.com", "7@1.com"));
        List<String> passwords = new ArrayList<>(Arrays.asList("7C6A180B36896A0A8C02787EEAFB0E4C", "6CB75F652A9B52798EB6CF2201057C73", "819B0643D6B89DC9B579FDFC9094F28E",
                "34CC93ECE0BA9E3F6F235D4AF979B16C", "DB0EDD04AAAC4506F7EDAB03AC855D56", "218DD27AEBECCECAE69AD8408D9A36BF", "00CDB7BB942CF6B290CEB97D6ACA64A3"));
        List<String[]> friends = new ArrayList<>();
        friends.add(new String[]{"Rodrigez", "Mike", "David"});
        friends.add(new String[]{"Pedro", "Mike", "David", "Tiffany"});
        friends.add(new String[]{"Rodrigez", "David", "Pedro"});
        friends.add(new String[]{"Rodrigez", "Mike", "Pedro"});
        friends.add(new String[]{"Rodrigez", "Mike"});
        friends.add(new String[]{"David", "Mike"});
        friends.add(new String[]{"David", "Mike", "Rodrigez", "Ilia"});

        /* Реализация поведенческого паттерна Iterator */
        for (int i = 0; i < users.size();i++){
            addUser(users.get(i), phones.get(i), emails.get(i), passwords.get(i), friends.get(i));
        }
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