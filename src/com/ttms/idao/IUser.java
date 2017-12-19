package com.ttms.idao;

/**
 * 定义对登陆用户表的增删改查接口
 * @author 张荣
 */
public interface IUser
{
    // 判断用户名、密码是否正确
    public boolean isExist(String userName, String userPass);

    // 登录用户修改自己的密码
    public int updatePass(String userName, String userPass);

    // 管理员修改用户类型
    public int updateType(String emp_no, int type);
}
