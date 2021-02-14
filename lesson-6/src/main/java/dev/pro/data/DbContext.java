package dev.pro.data;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DbContext {
    private ArrayList<Employee> eployees = new ArrayList<>();

    private StatementComposer composer = new StatementComposer();
    public void Init() {
        try {
        Class.forName("org.postgresql.Driver");
        Connection connection = getConnection();

        var description = TableDescriptionFactory.GetTableDescription(Employee.class);
        var stmt = connection.createStatement();

        var dropTableStatement = composer.getDropTableStatement(description);
        stmt.executeUpdate(dropTableStatement);
        var createTableQuery = composer.getCreateTableStatement(description);
        stmt.executeUpdate(createTableQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Save() {
        if (eployees.isEmpty()) {
            return;
        }
        try {
            var description = TableDescriptionFactory.GetTableDescription(Employee.class);
            try (Connection connection = getConnection()) {
                try (var stmt = connection.createStatement()) {
                    var newEmployees = this.eployees.stream()
                            .filter(emp -> emp.getId() <= 0)
                            .collect(Collectors.toList());
                    var insertQuery = composer.getInsertQuery(newEmployees, description);
                    stmt.executeUpdate(insertQuery);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_database", "postgres","postgres");
    }


    public ArrayList<Employee> getEployees() {
        return eployees;
    }
}

