import static org.junit.jupiter.api.Assertions.*;

import com.library.controller.UserController;
import com.library.exceptions.UserAlreadyExistsException;
import com.library.exceptions.UserNotFoundException;
import com.library.model.entities.User;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class UserTests {
    private final UserController controller = new UserController();

    @Test
    void createUser() {
        String name = "Rogrigues Silva Santos";
        String email = "rodrigues@gmail.com";
        String phone = "(83)40028922";
        String address = "Esperança-PB";
        if (!controller.isThereAUser(email)) {
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
        } else {
            assertThrows(UserAlreadyExistsException.class, () -> controller.createNewUser(name, email, phone, address));
        }
    }

    @Test
    void updateUser() {
        createUser();

        String oldName = "Rogrigues Silva Santos";
        Integer id = (controller.getUsers().stream().filter(u -> Objects.equals(u.getName(), oldName)).findFirst().get().getId());
        User user = assertDoesNotThrow(() -> controller.getUser(id));

        String newName = "Bruno mars";
        String newEmail = "bruninho_ferreira@gmail.com";
        String phone = "(83)40028922";
        String newAddress = "EUA";

        if (controller.isThereAUser(newEmail)) {
            assertThrows(UserAlreadyExistsException.class, () -> controller.updateUser(user, newName, newEmail, phone, newAddress));
        } else {
            assertDoesNotThrow(() -> controller.updateUser(user, newName, newEmail, phone, newAddress));
            assertEquals(id, user.getId());
            assertEquals(newName, user.getName());
            assertEquals(newEmail, user.getEmail());
            assertEquals(phone, user.getPhone());
            assertEquals(newAddress, user.getAddress());
        }
    }

    @Test
    void deleteUser() {
        createUser();
        String name = "Ronaldo Santos de Oliveira";
        Integer id = (controller.getUsers().stream().filter(u -> Objects.equals(u.getName(), name)).findFirst().get().getId());
        User user = assertDoesNotThrow(() -> controller.getUser(id));

        controller.deleteUser(user.getId());
        assertThrows(UserNotFoundException.class, () -> controller.getUser(id));
    }
}
