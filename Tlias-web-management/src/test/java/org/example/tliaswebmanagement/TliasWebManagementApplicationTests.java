package org.example.tliaswebmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    public  void testConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/tlias?useSSL=false";
        String user = "root";
        String password = System.getenv("MYSQL_PASSWORD");
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("连接成功！");
        }
    }


}
