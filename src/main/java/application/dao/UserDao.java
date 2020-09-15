package application.dao;

import application.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User, Long> {
    List<User> getAll();

    Optional<User> findByLogin(String login);
}
