package application.service;

import application.model.User;

public interface UserService extends GenericService<User, Long> {
    User update(User user);
}
