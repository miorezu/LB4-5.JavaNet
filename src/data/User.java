package data;

import java.io.Serial;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final DateFormat dateFormatter =
            new SimpleDateFormat("dd.MM.yyyy");
    private static final DateFormat dateParser = dateFormatter;
    private String name;
    private String surname;
    private String sex;
    private Date birthday;

    public User() {
    }

    public User(String name, String surName, String sex, String birthday) {
        this.name = name;
        this.surname = surName;
        this.sex = sex;
        try {
            this.birthday = dateParser.parse(birthday);
        } catch (ParseException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}