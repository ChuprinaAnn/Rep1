package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ApiController {
    ArrayList <User> users= new ArrayList <>();
    //создать пользователя
    // curl -X POST http://localhost:8080/user  -H 'Content-Type: application/json' -d '{"username": "Max", "password": "abc", "age": "20")
    @PostMapping("user")
    public void addUser(@RequestBody User user, String repeatPassword) {
        users.add(user);
        if (!repeatPassword.equals(user.getPassword())) {
            throw new BadRequestException();
        }
        for (User oneOfUsers: users) {
             if (oneOfUsers.equals(user)) {
               throw new ConflictException();
             }
             if (!oneOfUsers.checkUsername()) {
                 throw new BadRequestException();
             }
        }
    }
    //выдать пользователя по username
    //curl -X GET  http://localhost:8080/user?username=Max 'Content-Type: text/plain'
    @GetMapping("user")
    public User getUser(@RequestParam ("username") String username) {
        User a = null;
        for (User oneOfUsers : users) {
            if (oneOfUsers.getUsername().equals(username)) {
                a = oneOfUsers;
            }
        }
        if (a == null) {
            throw new NotFoundException();
        }
        int age = a.getAge();
        String usern = a.getUsername();
        User b = new User(usern,age);
        return b;
    }
    //выдать список пользователей c параметром возраста
    //curl -X GET  http://localhost:8080/users?age=20
    @GetMapping("users")
    public List<String> getUsers(@RequestParam ("age") Integer age) {
        List<String> usersNames = new ArrayList<>();
        for (User u : users) {
            if (u.getAge()<=age+5 && u.getAge()>=age-5) {
                usersNames.add(u.getUsername());
            }
        }
        if  (usersNames.size() ==0) {
            usersNames = null;
        }

        return usersNames;
    }
    //удалить пользователя по username
    // curl -X DELETE  http://localhost:8080/user?username=Max 'Content-Type: text/plain'
    @DeleteMapping("user")
    public void deleteUser(@RequestParam ("username") String username) {
        User a = null;
        for (User oneOfUsers : users) {
            if (oneOfUsers.getUsername().equals(username)) {
                a = oneOfUsers;
            }
        }
        if (a == null) {
            throw new NotFoundException();
        } else {
            int k = 0;
            char[] aChar = a.getUsername().toCharArray();
            char[] admin = new char[5];
            admin[0] = aChar[0];
            admin[1] = aChar[1];
            admin[2] = aChar[2];
            admin[3] = aChar[3];
            admin[4] = aChar[4];
            String myStr = new String(admin);
            if (!myStr.equals("admin")) {
                throw new ForbiddenException();
            }
            users.remove(a);
        }
    }
        //обновить пользователя по username
        //curl -X PUT  http://localhost:8080/user?username=Max  -H 'Content-Type: application/json' -d '{"username": "Max", "password": "abc", "age": "20")
        @PutMapping("user")
        public void updateUser(@RequestParam ("username") String username,
                               @RequestBody User user, String repeatPassword) {
            User a = null;
            int i =0;
            for (User oneOfUsers : users) {
                if (oneOfUsers.getUsername().equals(username)) {
                    a = oneOfUsers;
                    break;
                }
                i++;
            }
            if (a == null) {
                throw new NotFoundException();
            }
            else {
                int k = 0;
                char[] aChar = a.getUsername().toCharArray();
                char[] admin = new char[6];
                admin[0] = aChar[0];
                admin[1] = aChar[1];
                admin[2] = aChar[2];
                admin[3] = aChar[3];
                admin[4] = aChar[4];
                admin[5] = aChar[5];
                String myStr = new String(admin);
                if (!myStr.equals("update")) {
                    throw new ForbiddenException();
                }
                for (char chars: aChar) {
                  if (chars<=64 || (chars<97 && chars>90) || chars>122) {
                      throw new BadRequestException();
                }
                if (!repeatPassword.equals(user.getPassword())) {
                    throw new BadRequestException();
                }
                users.remove(a);
                users.add(i, user);
            }

        }
    }

}
