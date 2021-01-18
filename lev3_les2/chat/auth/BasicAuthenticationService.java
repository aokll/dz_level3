package com.chat.auth;

import com.chat.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BasicAuthenticationService implements AuthenticationService {
    public User em(String email, String password){

        Connection connection = DataSource.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE email = ? AND password = ?"
            );
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("nickname"),
                        resultSet.getString("email"),
                        resultSet.getString("password")

                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            DataSource.close(connection);
        }


        return null;
    }


    @Override
    public Optional<User> doAuth(String login, String password) {


        return Optional.empty();
    }
}


