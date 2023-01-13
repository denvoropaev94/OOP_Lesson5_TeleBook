package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception {
        validateUser(user);
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }
        public List<User> readList(){
            List<User> result = repository.getAllUsers();
            return result;
        }

        public void updateUser(String idNumber, User user) throws Exception {
        idPresValidation(idNumber);
        user.setId(idNumber);
            validateUserId(user);
            repository.updateUser(user);
        }
        private void validateUser(User user) throws Exception {
        if(user.getFirstName().contains(" "))
            throw new Exception("В имени не может быть пробелов ");
        if(user.getLastName().contains(" "))
                throw new Exception("В фамилии не может быть пробелов ");
        }

        private void validateUserId(User user) throws Exception {
            if(user.getId().isEmpty())
                throw new Exception("У пользователя нет id");
            validateUser(user);
        }
        public void idPresValidation(String idNumber) throws Exception {
            List<User>users = repository.getAllUsers();
            for (User user:users) {
              if  (user.getId().equals(idNumber))
                        return;
            }
            throw new Exception("Нет пользователя с таким id");
        }
}
