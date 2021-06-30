package com.example.shardingspherexa;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Execute XA with exception.
     *
     * @param count insert record count
     */
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void insertFailed(final int count) {
        jdbcTemplate.execute("insert into t_order(userid, createtime, state, receivername, region, address, phone) values (?, ?, ?, ?, ?, ?, ?)",
                (PreparedStatementCallback<TransactionType>) preparedStatement -> {
            doInsert(count, preparedStatement);
            throw new SQLException("mock transaction failed");
        });
    }

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void insert(final int count){
        jdbcTemplate.execute("insert into t_order(userid, createtime, state, receivername, region, address, phone) values (?, ?, ?, ?, ?, ?, ?)",
                (PreparedStatementCallback<TransactionType>) preparedStatement -> {
                    doInsert(count, preparedStatement);
                    return TransactionTypeHolder.get();
        });
    }

    private void doInsert(final int count, final PreparedStatement preparedStatement) throws SQLException {
        for (int i = 0; i < count; i++) {
            preparedStatement.setObject(1, (long) (i));
            preparedStatement.setObject(2, new Date());
            preparedStatement.setObject(3, 0);
            preparedStatement.setObject(4, "zhanghua");
            preparedStatement.setObject(5, "aa");
            preparedStatement.setObject(6, "aa.bb");
            preparedStatement.setObject(7, "18710368490");
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Select all.
     *
     * @return record count
     */
    int selectAll() {
        System.out.println(jdbcTemplate);
        return jdbcTemplate.queryForObject("select count(*) FROM t_order", Integer.class);
    }
}
