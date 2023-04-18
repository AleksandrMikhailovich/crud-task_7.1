package app.repository;

import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository /*extends JpaRepository<User, Integer>*/ {
    void save(User user);

    void update(int id, User updateUser);

    void delete(int id);

    List<User> findAll();

    User findOne(int id);
}
