package org;
/**
 * The Person class represents a person with their personal details and department information.
 */
public class Person {

    private final int personID;
    private final String name;
    private final String sex;
    private final String birthday;
    private final Department department;
    private  final  int salary;

    /**
     * Constructs a new Person object with the given parameters.
     *
     * @param personID   the unique ID of the person
     * @param name       the name of the person
     * @param sex        the sex of the person
     * @param date       the birthday of the person in string format
     * @param salary     the salary of the person
     * @param department the department the person belongs to
     */
    public Person(int personID, String name, String sex, String date, int salary, Department department) {
        this.personID = personID;
        this.name = name;
        this.sex = sex;
        this.birthday = date;
        this.salary = salary;
        this.department = department;
    }
    /**
     * Get person`s id
     * @return the unique ID of the person
     */
    public int getPersonID() {
        return personID;
    }
    /**
     * Get person`s name
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Get person`s sex
     *
     * @return the sex of the person
     */
    public String getSex() {
        return sex;
    }
    /**
     * Get person`s birth date
     *
     * @return the birthday of the person in string format
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Returns the salary of the person.
     *
     * @return the salary of the person
     */
    public int getSalary() {
        return salary;
    }
    /**
     * Returns the department that person belongs to.
     *
     * @return Department instance
     */
    public Department getDepartment() {
        return department;
    }
    /**
     * Returns a string representation of the Person object.
     *
     * @return a string representation of the Person object
     */
    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday+ '\''
                + department.toString()+
                ", salary=" + salary + '}';
    }


}
