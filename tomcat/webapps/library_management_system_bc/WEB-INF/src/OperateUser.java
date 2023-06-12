package library_management_class;

import java.util.ArrayList;
import library_management_class.User;
import library_management_class.ConnectionManager;
import library_management_class.UserDao;
import java.sql.*;
public class OperateUser {
    private Connection connection;
    public OperateUser(Connection connection) {
        super();
        this.connection = connection;
    }

    public ArrayList<User> searchAllUsers() {
        UserDao userDao = new UserDao(connection);
        ArrayList<User> userList = userDao.selectAll();
        return userList;
    }

    public boolean registerUser(User user) {
        UserDao userDao = new UserDao(connection);
        int result = userDao.insert(user);
        if (result == 1){
            return true;
        }else{
            return false;
        }
    }

    // public boolean login(User user) {
    //     // TODO: Implement this method
    //     return false;
    // }

    // public boolean logout(User user) {
    //     // TODO: Implement this method
    //     return false;
    // }

    public boolean updateUser(User user) {
        UserDao userDao = new UserDao(connection);
        boolean result = userDao.update(user);
        return result;
    }
    
}
