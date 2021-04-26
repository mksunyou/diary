package gdu.diary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
   public Connection getConnection( ) {
      Connection conn = null;
      try {
         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/diary" ,"root" ,"java1004");
         conn.setAutoCommit(false);
      } catch (SQLException e) {
         e.printStackTrace();
      } 
      //파이널리로 클로즈하면 리턴할 커넥션이 닫혀있음.
      return conn;
   }
   
   // 메서드 호출쪽에서 자원을 해제 해야하는데 편하게 해제가능하도록 메서드 제공
   
}