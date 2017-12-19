package com.ttms.service;

import com.ttms.dao.UserDao;
import com.ttms.vo.User;

import java.sql.SQLException;

/**
 * Created by lmy on 17-12-18.
 */
public class UserService {
    public User getUser(String emp_no, String emp_pass) throws SQLException {
        return new UserDao().getUser(emp_no,emp_pass);
    }
}
