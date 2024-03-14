package com.rv.service;

import com.rv.dao.UserDao;
import org.junit.jupiter.api.*;
import org.mockito.*;

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
        userService.sendEmail();
        Mockito.verify(userDao,
                Mockito.times(1))
                .sendEmailNotification(ArgumentMatchers.anyInt());
    }

}