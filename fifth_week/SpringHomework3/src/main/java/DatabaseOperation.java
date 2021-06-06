import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseOperation {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "root";
    static final String PASS = "123456";
    private static final HikariDataSource ds;

    static {
        HikariConfig conf = new HikariConfig();
        conf.setUsername("root");
        conf.setPassword("123456");
        conf.setJdbcUrl("jdbc:mysql://localhost:3306/test_db");
        ds = new HikariDataSource(conf);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
          String sql = "select * from db_user";
           getInfoFromDB(sql);

           HashMap<User, Double> userScores = new HashMap<>();
           userScores.put(new User(4, "zhaozhang", 1), 70.5);
           userScores.put(new User(5, "zhanghua", 1), 80.6);
           userScores.put(new User(6, "wanggang", 0), 90.0);

           InsertUserScore(userScores);

           //Use Hikari
           getInfoFromDBUsingHikari(sql);
    }


    public static void getInfoFromDB(String sql){
        Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                int sex = rs.getInt("sex");
                System.out.print(id + " " + name + "\t");
                System.out.println(sex == 0 ? "男" : "女");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public static void InsertUserScore(HashMap<User, Double> scores) throws SQLException, ClassNotFoundException {
        String insertUserSql = "Insert into db_user values(?, ?, ?)";
        String insertScoreSql = "Insert into db_score values(?, ?)";
        Connection con = null;
        Statement stmt = null;

        Class.forName(JDBC_DRIVER);
        con = DriverManager.getConnection(DB_URL,USER,PASS);

        try (PreparedStatement insertUser = con.prepareStatement(insertUserSql);
             PreparedStatement insertScore = con.prepareStatement(insertScoreSql))

        {
            con.setAutoCommit(false);
            for (Map.Entry<User, Double> e : scores.entrySet()) {
                insertUser.setInt(1, e.getKey().getId());
                insertUser.setString(2, e.getKey().getName());
                insertUser.setInt(3, e.getKey().getSex());
                insertUser.executeUpdate();

                insertScore.setInt(1, e.getKey().getId());
                insertScore.setString(2, e.getValue().toString());
                insertScore.executeUpdate();
                con.commit();
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    System.err.println("Transaction is being rolled back");
                    con.rollback();
                } catch (SQLException excep) {
                    excep.printStackTrace();
                }
            }
        }
        finally {
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(con!=null) con.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public static void getInfoFromDBUsingHikari(String sql) throws SQLException {
        Connection connection = ds.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int sex = resultSet.getInt("sex");
            System.out.print(id + " " + name + "\t");
            System.out.println(sex == 0 ? "男" : "女");
        }
        connection.close();
    }
}
