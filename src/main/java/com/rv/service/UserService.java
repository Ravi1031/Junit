package com.rv.service;

import com.rv.dao.UserDao;

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
}
