package com.rv.service;

import com.rv.dao.UserDao;
import com.rv.entity.User;

import java.util.ArrayList;
import java.util.List;
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    //service method to validate the login credentials
    public String validateLogin(String userName,String password){
        return userDao.getLoginStatus(userName,password);
    }

    public void sendEmail(){
        userDao.sendEmailNotification(5);
    }

    public List<User> getAllUser(List<String> userIds){
        List<User> userList = new ArrayList<>();
        for (String userId:userIds) {
            User userById = userDao.getUserById(userId);
            userList.add(userById);
        }
        return userList;
    }

    public User updateUser(User user){
        if (user == null || user.firstName() == null)
            throw new IllegalArgumentException("Invalid user data");
        return userDao.saveUser(user);
    }
}
