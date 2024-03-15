package com.rv.service;

import com.rv.dao.UserDao;
import com.rv.entity.User;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

class UserServiceTest {

    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService;


    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(userDao);
    }

    @Test
    @DisplayName("Test case for validating login success")
    public void validateLoginCredentials_Success(){
        Assertions.assertNotNull(userDao);
        //setting behaviour of mock method
        Mockito.when(userDao.getLoginStatus(anyString(),anyString()))
                .thenReturn("Login Success");
        //calling the original method
        String loginStatus = userService.validateLogin("Ravi", "Ravi");
        Assertions.assertNotNull(loginStatus);
        Assertions.assertEquals("Login Success",loginStatus);
    }

    @Test
    @DisplayName("Test case for validating login failure")
    public void validatingLoginCredentials_Failure(){
        Assertions.assertNotNull(userDao);
        //setting the mock behaviour
        Mockito.when(userDao.getLoginStatus(anyString(),anyString()))
                .thenReturn("Login Failure");
        //calling the original method
        String loginStatus = userService.validateLogin("Ravi", "Ravi1234");
        Assertions.assertNotNull(loginStatus);
        Assertions.assertEquals("Login Failure",loginStatus);
    }


    @Test
    @DisplayName("validating login credential exception")
    public void validatingLoginCredential_Exception(){
        Assertions.assertNotNull(userDao);
        Mockito.when(userDao.getLoginStatus(null,null))
                .thenThrow(new RuntimeException("Provide input correctly..."));
        Assertions.assertThrows(RuntimeException.class,
                ()->userService.validateLogin(null,null));
    }

    @Test@DisplayName("Test for validating emails")
    public void validatingSendEmail(){
        Assertions.assertNotNull(userDao);
        Mockito.doNothing().when(userDao).sendEmailNotification(10);
        userService.sendEmail();
        //Behavioral verification
        Mockito.verify(userDao,
                Mockito.times(1))
                .sendEmailNotification(ArgumentMatchers.anyInt());
    }

    @Test
    @DisplayName("Test case for demonstrating stubbing...")
    public void test_getAll_users(){
        List<String> userIds = new ArrayList<>();
        userIds.add("1234");
        userIds.add("1235");
        User user1 = new User("1234","John","Wick","Male");
        User user2 = new User("1235","Mahesh","Kumar","Male");
        Mockito.when(userDao.getUserById("1234")).thenReturn(user1);
        Mockito.when(userDao.getUserById("1235")).thenReturn(user2);
        List<User> allUser = userService.getAllUser(userIds);
        Assertions.assertEquals(2,allUser.size());
    }

    @Test
    public void testUpdateUser(){
        //create a real object
        UserDao userDao1 = new UserDao();
        //create a spy on the real object
        UserDao spyUserDao = Mockito.spy(userDao1);
        //create a userService using spy
        UserService userService1 = new UserService(spyUserDao);
        //create a user to test
        User user = new User("1234","Ravi","Prakash","Male");
        //call the update method
        User updateUser = userService1.updateUser(user);
        Mockito.verify(spyUserDao,Mockito.times(1)).saveUser(user);
    }

}