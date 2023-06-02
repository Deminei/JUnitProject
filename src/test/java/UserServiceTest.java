import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserService userService;
    @InjectMocks
    UserService service;
    User user;

    @BeforeEach
    public void setUp() {
        service = new UserService();
        user = new User("john", "password", "john@example.com");
        service.registerUser(user);
    }

    @AfterEach
    public void tearDown() {
        service = null;
        user = null;
    }

    @Test
    public void testRegisterUser_Positive() {
        User newUser = new User("jane", "password", "jane@example.com");

        assertTrue(service.registerUser(newUser));
    }

    @Test
    public void testRegisterUser_Negative_UserAlreadyExists() {
        assertFalse(service.registerUser(user));
    }

    @Test
    public void testRegisterUserNegativeNullUser() {
        service.equals(null);
    }

    @Test
    public void testLoginUser_Positive() {
        User loggedInUser = service.loginUser("john", "password");

        assertNotNull(loggedInUser);
        assertEquals(user, loggedInUser);
    }

    @Test
    public void testLoginUser_Negative_UserNotFound() {
        assertNull(service.loginUser("nonexistent", "password"));
    }

    @Test
    public void testLoginUser_Negative_WrongPassword() {
        assertNull(service.loginUser("john", "wrongpassword"));
    }
}
