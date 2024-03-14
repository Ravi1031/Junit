package com.rv.dao;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class UserDaoTest {

    private UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDao();
    }

    @AfterEach
    void tearDown() {
        userDao = null;
    }

    @Test
    @DisplayName("Login status success")
    void getLoginStatus_success() {
        //Arrange
        Assertions.assertNotNull(userDao);
        //Assert
        String loginStatus = userDao.getLoginStatus("Ravi", "Ravi");
        //Act
        Assertions.assertEquals("Login Success", loginStatus);
    }

    @Test
    @DisplayName("Login status failure")
    void getLoginStatus_failure() {
        Assertions.assertNotNull(userDao);
        String loginStatus = userDao.getLoginStatus("Ravi", "ravi1234");
        Assertions.assertEquals("Login Failure", loginStatus);
    }

    @Test
    @DisplayName("Login status exception thrown")
    void getLoginStatus_exception() {
        Assertions.assertNotNull(userDao);
        Assertions.assertThrows(RuntimeException.class,
                () -> userDao.getLoginStatus(null, null));
    }


    @Test
    @Disabled
    void sendEmailNotification() {
        Assertions.assertNotNull(userDao);
        UserDao userDao1 = Mockito.mock(UserDao.class);
        userDao.sendEmailNotification(10);
        Mockito.verify(userDao1,Mockito.times(1)).sendEmailNotification(10);
    }
}