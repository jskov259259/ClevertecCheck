package utils.cache;

import model.User;

import java.time.LocalDate;

public class UserBuilder {

    private Integer id;
    private String name;
    private String email;
    private LocalDate birthDay;
    private Integer age;

    public UserBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public UserBuilder withAge(Integer age) {
        this.age = age;
        return this;
    }

    public User build() {
        User user = new User(id, name, email, birthDay, age);
        return user;
    }

}
