package dev.pro.data;

public class MainApp {
    public static void main(String[] args) {
        DbContext context = new DbContext();

        context.Init();

        var employees = context.getEployees();
        employees.add(new Employee("Ivan","Ivanov","Frontend developer"));
        employees.add(new Employee("Petr","Petrov","Backend developer"));

        context.Save();
    }
}
