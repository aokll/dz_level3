package Server;

import Auth.AuthenticationService;

public interface Server {
    void broadcastMessage(String message); //трансляция сообщени
    void personalMessage(String message);  // пересональное сообщение
    boolean isLoggedIn(String nickname);  // авторизован
    void subscribe(ClientHandler client);  //обработчик клиента
    void unsubscribe(ClientHandler client);  // тказаться от подписки
    AuthenticationService getAuthenticationService(); //служба аутентификации
}

