package application.dao;

import application.model.User;
import java.util.List;

public interface UserDao extends GenericDao<User, Long> {
    List<User> getAll();
}
