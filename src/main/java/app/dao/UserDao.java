package app.dao;

import app.model.User;

import java.util.List;


public interface UserDao {
    void save(User user);

    void update(User updateUser);

    void remove(int id);

    List<User> findAll();

    User findOne(int id);
}
