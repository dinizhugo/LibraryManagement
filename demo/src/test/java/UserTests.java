import static org.junit.jupiter.api.Assertions.*;

import com.library.controller.UserController;
import com.library.exceptions.UserNotFoundException;
import com.library.model.entities.User;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class UserTests {
    private UserController controller = new UserController();

    @Test
    void createUser() {
        String name = "Rogrigues Silva Santos";
        String email = "rodrigues@gmail.com";
        String phone = "(83)40028922";
        String address = "Esperança-PB";
        assertDoesNotThrow(() ->
                controller.createNewUser(name, email, phone, address)
        );
        Integer id = (controller.getUsers().stream().filter(u -> Objects.equals(u.getName(), name)).findFirst().get().getId());
        User user = assertDoesNotThrow(() -> controller.getUser(id));
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(phone, user.getPhone());
        assertEquals(address, user.getAddress());
    }

    @Test
    void updateUser() {
        String oldName = "Rogrigues Silva Santos";
        Integer id = (controller.getUsers().stream().filter(u -> Objects.equals(u.getName(), oldName)).findFirst().get().getId());
        User user = assertDoesNotThrow(() -> controller.getUser(id));

        String newName = "Bruno mars";
        String newEmail = "bruninho_ferreira@gmail.com";
        String phone = "(83)40028922";
        String newAddress = "EUA";

        controller.updateUser(user, newName, newEmail, phone, newAddress);
        assertEquals(id, user.getId());
        assertEquals(newName, user.getName());
        assertEquals(newEmail, user.getEmail());
        assertEquals(phone, user.getPhone());
        assertEquals(newAddress, user.getAddress());
    }

    @Test
    void deleteUser() {
        String oldName = "Bruno mars";
        Integer id = (controller.getUsers().stream().filter(u -> Objects.equals(u.getName(), oldName)).findFirst().get().getId());
        User user = assertDoesNotThrow(() -> controller.getUser(id));

        controller.deleteUser(user.getId());
        assertThrows(UserNotFoundException.class, () -> controller.getUser(id));
    }
}
