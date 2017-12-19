package com.ttms.dao;

import com.ttms.utils.DataSourceUtils;
import com.ttms.vo.Employee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lmy on 17-12-18.
 */
public class EmployeeD {
    public List<Employee> findAllEmp() throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from employee";
        List<Employee> list = qr.query(sql, new BeanListHandler<>(Employee.class));
        return list;
    }
}
