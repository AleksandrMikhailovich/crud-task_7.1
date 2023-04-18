package app.service;

import app.model.User;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Repository
@Transactional(readOnly = true)
public class UserService implements UserRepository{
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
        entityManager.close();
    }

    @Override
    @Transactional
    public void update(int id, User updateUser) {
        entityManager.merge(updateUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    public User findOne(int id) {
        return entityManager.find(User.class, id);
    }
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    public User findOne(int id) {
//        Optional<User> foundPerson = userRepository.findById(id);
//        return foundPerson.orElse(null);
//    }
//
//    @Transactional
//    public void save(User user) {
//        userRepository.save(user);
//    }
//
//    @Transactional
//    public void update(int id, User updateUser) {
//        updateUser.setId(id);
//        userRepository.save(updateUser);
//    }
//
//    @Transactional
//    public void delete(int id) {
//        userRepository.deleteById(id);
//    }
}

