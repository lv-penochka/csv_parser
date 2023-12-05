package org;

import java.util.Date;

public class Person {

    private final int personID;
    private final String name;
    private final String sex;
    private final Date birthday;
    private final Department department;
    private  final  int salary;

    public Person(int personID, String name, String sex, Date date, int salary, Department department) {
        this.personID = personID;
        this.name = name;
        this.sex = sex;
        this.birthday = date;
        this.salary = salary;
        this.department = department;
    }

    public int getPersonID() {
        return personID;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }



    public int getSalary() {
        return salary;
    }


}
