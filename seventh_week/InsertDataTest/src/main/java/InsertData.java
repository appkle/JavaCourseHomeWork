import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;

/**
 * 总结：1 使用PreparedStatement导入100万条数据花费249.475s;
 * 2 使用批处理（add batch）的方法导入100万条数据花费37.051s;
 * 3 使用Load data的方法导入100万条数据花费15.546s;
 */
public class InsertData {
    private static final HikariDataSource ds;
    static {
        HikariConfig conf = new HikariConfig();
        conf.setUsername("root");
        conf.setPassword("123456");
        conf.setJdbcUrl("jdbc:mysql://localhost:3306/db_e-commerce?rewriteBatchedStatements=true");
        ds = new HikariDataSource(conf);
    }

    public static void main(String[] args) throws SQLException {
        //insertDataUsingHikari();
        //insertDataUsingHikari1();
        String filePath = "D:\\order_info.txt";
        insertDataUsingHikari2(filePath);
    }

   public static void insertDataUsingHikari() throws SQLException {
        String sql = "insert into tb_order_info values (?, ?, ?, ?, ?)";
        Connection connection = ds.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        long start =  System.currentTimeMillis();
        connection.setAutoCommit(false);
        for (int i = 0; i< 1000000; i++){
            stmt.setString(1, String.valueOf(i));
            stmt.setInt(2, i);
            stmt.setDate(3, new Date(System.currentTimeMillis()));
            stmt.setInt(4, 0);
            stmt.setInt(5, i);
            stmt.executeUpdate();
        }
        connection.commit();
        System.out.println("cost time: " + (System.currentTimeMillis() -start));
        connection.close();
   }

   public static void insertDataUsingHikari1() throws SQLException {
       String sql = "insert into tb_order_info values (?, ?, ?, ?, ?)";
       Connection connection = ds.getConnection();
       PreparedStatement stmt = connection.prepareStatement(sql);
       long start =  System.currentTimeMillis();
       connection.setAutoCommit(false);
       for(int i=0; i<1000000; i++){
           stmt.setString(1, String.valueOf(i));
           stmt.setInt(2, i);
           stmt.setDate(3, new Date(System.currentTimeMillis()));
           stmt.setInt(4, 0);
           stmt.setInt(5, i);
           stmt.addBatch();
       }
       stmt.executeBatch();
       connection.commit();
       System.out.println("cost time: " + (System.currentTimeMillis() -start));
       connection.close();
   }

   public static void insertDataUsingHikari2(String file_path) throws SQLException {
        String sql = "load data infile " + "'" +  file_path + "'" + " into table tb_order_info FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n'";
        Connection connection = ds.getConnection();
        Statement statement = connection.createStatement();
        long start = System.currentTimeMillis();
        statement.execute(sql);
        System.out.println("cost time: " + (System.currentTimeMillis() - start));
        connection.close();
   }
}
