package Server;

import Auth.AuthenticationService;
import Auth.BasicAuthenticationService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer implements Server {
    private Set<ClientHandler> clients;
    private AuthenticationService authenticationService;

    public ChatServer() {
        try {
            System.out.println("Сервер запускается...");
            ServerSocket serverSocket = new ServerSocket(3345);
            clients = new HashSet<>();
            authenticationService = new BasicAuthenticationService();
            System.out.println("Сервер запущен...");

            while (true) {
                System.out.println("Сервер прослушивает клиентов...");
                Socket socket = serverSocket.accept();
                System.out.println("Клиент принят: " + socket);
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    @Override //трансляция сообщени
    public synchronized void broadcastMessage(String message) {
        clients.forEach(client -> client.sendMessage(message));
    }

    @Override // пересональное сообщение
    public synchronized void personalMessage(String message) {
        clients.forEach(client -> client.sendMessage(message));
    }

    @Override // авторизован
    public synchronized boolean isLoggedIn(String nickname) {
        return clients.stream()
                .filter(clientHandler -> clientHandler.getName().equals(nickname))
                .findFirst()
                .isPresent();
    }

    @Override
    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }// подписка

    @Override
    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }// отказаться от подписки

    @Override
    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }//служба аутентификации

}