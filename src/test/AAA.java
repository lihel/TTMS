package test;

import com.ttms.utils.DataSourceUtils;
import com.ttms.vo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lmy on 17-11-9.
 */
public class AAA {
    public static void main(String[] args) throws SQLException {
//        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user";
        List<User> list = qr.query(sql, new BeanListHandler<>(User.class));//一次取多条数据
//        User user = qr.query(sql, new BeanHandler<User>(User.class));
//        System.out.println(user.getEmp_no()+":"+user.getEmp_pass());
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            System.out.println(user.getEmp_no()+":"+user.getEmp_pass());
        }

    }
}
