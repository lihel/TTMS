package com.ttms.idao;


import com.ttms.dao.EmployeeDAO;
import com.ttms.dao.StudioDAO;
import com.ttms.dao.UserDAO;

public class DAOFactory
{
    public static IEmployee creatEmployeeDAO()
    {
        return new EmployeeDAO();
    }

    public static IUser creatUserDAO()
    {
        return new UserDAO();
    }

    public static IStudio createStudioDAO()
    {
        return new StudioDAO();
    }
}
