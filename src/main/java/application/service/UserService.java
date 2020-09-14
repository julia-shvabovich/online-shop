package application.service;

import application.model.User;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {
    User update(User user);

    Optional<User> findByLogin(String login);
}
