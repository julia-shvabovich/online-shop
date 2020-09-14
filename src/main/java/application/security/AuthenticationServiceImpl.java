package application.security;

import application.exceptions.AuthenticationException;
import application.lib.Inject;
import application.lib.Service;
import application.model.User;
import application.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User user = userService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect username or password"));
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new AuthenticationException("Incorrect username or password");
    }
}
