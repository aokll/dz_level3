package Auth;

import User.User;

import java.util.Optional;

public interface AuthenticationService { //служба аутентификации
    Optional<User> doAuth(String login, String password);
}
