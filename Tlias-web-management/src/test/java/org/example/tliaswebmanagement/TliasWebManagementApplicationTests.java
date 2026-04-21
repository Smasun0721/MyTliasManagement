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
        String password = "0217"; // 替换为配置中的密码
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("连接成功！");
        }
    }


}
