package ssg.library.dbio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public abstract class AbstractDBIO {

  //프로퍼티
    protected Connection getConnection () {
      Connection connection = null;
      try {
            Properties properties = new Properties();

            try(FileInputStream fis = new FileInputStream("resource/database.properties")) {
              properties.load(fis);
            }

            String driver = properties.getProperty("com.mysql.cj.jdbc.Driver");
            String url = properties.getProperty("jdbc:mysql://localhost:3306/category");
            String name = properties.getProperty("root");
            String password = properties.getProperty("Mysql1234!?");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    protected void close (Connection connection, PreparedStatement pStmt){
        try {
            if(pStmt != null) {
              pStmt.close();
            }
            if(connection != null) {
              connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    protected void close (Connection connection, PreparedStatement pStmt, ResultSet rs){
        try {
            if(rs != null) {
              rs.close();
            }
            if(pStmt != null) {
              pStmt.close();
            }
            if(connection != null) {
              connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


  protected void create(Object o) {

  }

  protected Object read(){
      return null;
  }

  protected void update(Object o){

  }

  protected void delete(Object o){

  }
}
