package application.security;

import application.exceptions.AuthenticationException;
import application.lib.Inject;
import application.lib.Service;
import application.model.User;
import application.service.UserService;
import application.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> user = userService.findByLogin(login);
        if (user.isEmpty() || !isValid(password, user.get())) {
            throw new AuthenticationException("Incorrect username or password");
        }
        return user.get();
    }

    private boolean isValid(String password, User user) {
        byte[] salt = user.getSalt();
        return HashUtil.hashPassword(password, salt).equals(HashUtil.hashPassword(password, salt));
    }
}
