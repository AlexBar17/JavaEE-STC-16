package lesson27.task01.src.main.java.com.inno;

import java.util.List;

/* Класс для создания объекта user */
public class User {

    private String name;
    private String phone;
    private String password;
    private String email;

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    private List<String> friends;

    /* Добавление новой сущности: email */
    public User(Builder builder) {
        this.name = builder.name;
        this.phone = builder.phone;
        this.password = builder.password;
        this.email = builder.email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /* Класс реализующий паттерн Builder */
    public static class Builder {
        private String name;
        private String phone;
        private String password;
        private String email;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withPhone(String phone){
            this.phone = phone;
            return this;
        }
        public Builder withPassword(String password){
            this.password = password;
            return this;
        }
        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
