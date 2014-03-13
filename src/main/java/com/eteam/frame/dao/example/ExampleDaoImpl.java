package com.eteam.frame.dao.example;

import com.eteam.commons.SqlBuilder;
import com.eteam.frame.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * 测试JDBC方式事务管理
 * @author liufm
 *
 */
@Repository
public class ExampleDaoImpl extends BaseDao {

    /**
     * 测试普通JDBC事务回滚
     * @param id
     * @return
     */
    public int doInsert(int id) {
        int iRtn = 0;
        SqlBuilder sql = new SqlBuilder();
        sql.append("insert into test_table (id, i_key, i_value) values (?, ?, ?)");
        sql.addArg(1);
        sql.addArg("123");
        sql.addArg("345");

        iRtn = jdbcTemplate.update(sql.getSQL(), sql.getSQLArgs());

        return iRtn;
    }
}
