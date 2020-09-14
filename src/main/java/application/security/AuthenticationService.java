package application.security;

import application.exceptions.AuthenticationException;
import application.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
