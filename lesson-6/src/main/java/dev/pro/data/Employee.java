package dev.pro.data;

@AppTable()
public class Employee {
    @AppColumn(isPrimaryKey = true)
    private int id;

    @AppColumn
    private String firstName;

    @AppColumn
    private String lastName;

    @AppColumn
    private String position;

    public Employee(String firstName, String lastName, String position){
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.id= 0;
    }

    public Employee(int id, String firstName, String lastName, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
