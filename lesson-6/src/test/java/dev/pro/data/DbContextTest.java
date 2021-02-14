package dev.pro.data;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.jupiter.api.Assertions.*;

class DbContextTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getTableDescription() {

        TableDescription description = null;
        try {
            description = TableDescriptionFactory.GetTableDescription(Employee.class);
        } catch (OperationNotSupportedException e) {
            fail(e.getMessage());
        }
        var columns = description.getColumns();


        assertEquals("Employee", description.name);
        assertEquals("id", columns.get(0).name);
        assertEquals("Integer", columns.get(0).type);
        assertTrue(columns.get(0).isPrimaryKey());

        assertEquals("firstName", columns.get(1).name);
        assertEquals("String", columns.get(1).type);

        assertEquals("lastName", columns.get(2).name);
        assertEquals("String", columns.get(2).type);

        assertEquals("position", columns.get(3).name);
        assertEquals("String", columns.get(3).type);

    }
}