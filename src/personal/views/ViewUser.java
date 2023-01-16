package personal.views;

import personal.controllers.UserController;
import personal.model.*;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public ViewUser(){}

    public void run() {
        Commands com = Commands.NONE;
        String id;
        String command;
        FileOperation fileOperation;
        Repository repository;

        String mode = prompt("Введите режим сохранения файла (1: текстовый(,) 2: текстовый(;) 3:json -> ");
        while (!mode.equals("1") && (!mode.equals("2")) && (!mode.equals("3"))){
            mode = prompt("Введите режим сохранения файла (1: текстовый (,) 2: текстовый(;) 3:json -> ");
        }
        switch (mode){
            case  "1":
                System.out.println("Вы выбрали режим сохранения в текстовый файл (через запятую)");
                fileOperation = new FileOperationImpl("users.txt");
                repository = new RepositoryFile(fileOperation);
                this.userController = new UserController(repository);
                break;
            case  "2":
                System.out.println("Вы выбрали режим сохранения в текстовый файл (через точку с запятой и строку разделитель)");
                fileOperation = new FileOperationImpl("users2.txt");
                repository = new FileOperationNew(fileOperation);
                this.userController = new UserController(repository);
                break;

            case  "3":
                System.out.println("Вы выбрали режим сохранения в формате JSON");
                fileOperation = new FileOperationImpl("users.json");
                repository = new JsonRepositoryFile(fileOperation);
                this.userController = new UserController(repository);
                break;
        }

        while (true) {
            command = prompt("Введите команду: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            try {
                switch (com) {
                    case CREATE:
                        String firstName = prompt("Имя: ");
                        String lastName = prompt("Фамилия: ");
                        String phone = prompt("Номер телефона: ");
                        userController.saveUser(new User(firstName, lastName, phone));
                        break;
                    case READ:
                        id = prompt("Идентификатор пользователя: ");
                        User user = userController.readUser(id);
                        System.out.println(user);
                        break;
                    case LIST:
                        List<User> lst = userController.readList();
                        lst.forEach(i -> System.out.println(i + "\n"));
                        break;
                    case UPDATE:
                        String newId = prompt("Введите id пользователя , которого хотите обновить: ");
                        userController.idPresValidation(newId);
                        userController.updateUser(newId, createNewUser());
                        break;
                    case DELETE:
                        String delId = prompt("Какой ID удалить?");
                        userController.deleteUser(delId);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка!\n " + e.getMessage());
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createNewUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        User newUser = new User(firstName, lastName, phone);
        return newUser;
    }
}
