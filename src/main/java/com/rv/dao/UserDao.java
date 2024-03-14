package com.rv.dao;

import static java.lang.System.*;
public class UserDao {

    public String getLoginStatus(String userName,String password){
        if (userName !=null && password != null){
            if ("Ravi".equalsIgnoreCase(userName)&&"Ravi".equals(password))
                return "Login Success";
            else
                return "Login Failure";
        }else {
            throw new RuntimeException("Provide input correctly...");
        }
    }

    public void sendEmailNotification(int count){
        out.println("Email sent for today count:::: "+count);

    }
}
