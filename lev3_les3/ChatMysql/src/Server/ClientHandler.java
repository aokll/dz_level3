package Server;

import Auth.DataSource;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;

    private String textArea;
    private String login;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            doListen();
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    public String getName() {
        return name;
    }

    private void doListen() {
        new Thread(() -> {
            try {
                doAuth();
                receiveMessage();
            } catch (Exception e) {
                throw new RuntimeException("SWW", e);
            } finally {
                server.unsubscribe(this);
            }
        }).start();
    }

    private void doAuth() {
        try {
            while (true) {
                String credentials = in.readUTF();
                /**
                 * Input credentials sample
                 * "-auth n1@mail.com 1"
                 */
                if (credentials.startsWith("-auth")) {
                    /**
                     * After splitting sample
                     * array of ["-auth", "n1@mail.com", "1"]
                     */
                    String[] credentialValues = credentials.split("\\s");
                    login = name.getText().trim();
                    server.getAuthenticationService()
                            .doAuth(credentialValues[1], credentialValues[2])
                            .ifPresentOrElse(
                                    em -> {
                                        if (!server.isLoggedIn(em.getNickname())) {
                                            sendMessage("cmd auth: статус ОК");

                                            System.out.print(textArea.appendText(History.getLast100LinesOfHistory(login)));
                                            History.start(login);

                                            name = em.getNickname();
                                            server.broadcastMessage(name + " входит в систему.");
                                            server.subscribe(this);
                                        } else {
                                            sendMessage("Текущий пользователь уже вошел в систему.");
                                        }
                                    },
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            sendMessage("Нет такого Пользователя по электронной почте и паролю.");
                                            History.stop();
                                        }
                                    }
                            );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    /**
     * Receives input data from {@link ClientHandler#in} and then broadcast via {@link Server#broadcastMessage(String)}
     */
    private void receiveMessage() {
        try {
            while (true) {
                String message = in.readUTF();
                if (message.equals("-exit")) {
                    try {
                        History.stop();
                        DataSource.getConnection().close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return;
                }else{
                    if(message.equals("/w")){
                        return;
                    }}
                server.broadcastMessage(message);
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientHandler that = (ClientHandler) o;
        return Objects.equals(server, that.server) &&
                Objects.equals(socket, that.socket) &&
                Objects.equals(in, that.in) &&
                Objects.equals(out, that.out) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(server, socket, in, out, name); }
}

