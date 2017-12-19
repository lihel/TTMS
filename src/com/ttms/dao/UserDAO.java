package com.ttms.dao;

import com.ttms.idao.IUser;
import com.ttms.model.User;
import com.ttms.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDAO implements IUser
{

    public static final int PAGE_SIZE = 8; // 每页显示条数
    private int allCount; // 数据库中条数
    private int allPageCount; // 总页数
    private int currentPage; // 当前页

    public int getAllCount()
    {
        return allCount;
    }

    public int getAllPageCount()
    {
        return allPageCount;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public String findName(String emp_no)
    {
        String name = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = ConnectionManager.getInstance().getConnection();
        try
        {
            String sql = "select employee.emp_name from employee,user where user.emp_no=? and employee.emp_no=user.emp_no";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp_no);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                name = rs.getString("employee.emp_name");
                System.out.println("findname---->" + name);
            }
        }
        catch(SQLException e)
        {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return name;
    }

    @SuppressWarnings("finally")
    public ArrayList<User> findUserByPage(int cPage, String emp_no)
    {
        currentPage = cPage;
        ArrayList<User> list = new ArrayList<User>();

        // 若未指定查找某人，则默认全查
        if(null == emp_no || emp_no.equals("null"))
        {
            emp_no = "";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取记录总数
            String sql1 = "select count(emp_no) as AllRecord from user where emp_no like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + emp_no + "%");
            rs = pstmt.executeQuery();
            if(rs.next())
                allCount = rs.getInt("AllRecord");

            rs.close();
            pstmt.close();

            // 记算总页数
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;

            // 如果当前页数大于总页数，则赋值为总页数
            if(allPageCount > 0 && currentPage > allPageCount)
                currentPage = allPageCount;

            // 获取第currentPage页数据
            String sql2 = "select * from user where emp_no like ? limit ?,?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + emp_no + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            rs = pstmt.executeQuery();
            User user = null;
            while(rs.next())
            {
                user = new User();
                user.setEmp_no(rs.getString("emp_no"));
                user.setType(rs.getInt("type"));
                // 将该用户信息插入列表
                list.add(user);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }

    @SuppressWarnings("finally")
    public boolean isExist(String emp_no, String emp_pass)
    {
        boolean result = false;
        Connection con = ConnectionManager.getInstance().getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String password = null;

        try
        {
            String sql = "select emp_pass from user where emp_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, emp_no);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                password = rs.getString("emp_pass");
            }
            if(password != null && password.equals(emp_pass))
                result = true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return result;
        }
    }

    @Override
    public int updatePass(String userName, String userPass)
    {

        int result = 0;
        Connection con = ConnectionManager.getInstance().getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String password = null;

        try
        {
            String sql = "update user set emp_pass=? where emp_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, userPass);
            result = pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return result;
        }
    }

    @Override
    public int updateType(String emp_no, int type)
    {
        int result = 0;
        Connection con = ConnectionManager.getInstance().getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String password = null;

        try
        {
            String sql = "update user set type=? where emp_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, type);
            pstmt.setString(2, emp_no);
            result = pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return result;
        }
    }

    public int delete(String emp_no)
    {
        // TODO 自动生成的方法存根
        return 0;
    }

    public int updateHead(User user)
    {
        int result = 0;
        Connection con = ConnectionManager.getInstance().getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String password = null;

        try
        {
            String sql = "update user set head_path=? where emp_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getPath());
            pstmt.setString(2, user.getEmp_no());
            result = pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return result;
        }
    }
}
