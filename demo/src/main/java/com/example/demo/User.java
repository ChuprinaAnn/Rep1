package com.example.demo;

class User {
    private String username;
    private String password;
    private Integer age;
    public User ( String username,
            String password,
           Integer age) {
        this.age = age;
        this.username = username;
        this.password = password;
    }
    public boolean checkUsername() {
        char[] username = this.username.toCharArray();
        int a = 0;
        for (char c : username) { // проверка того, подходят ли символы username под условие
           if ((c >= 65 && c < 91) || (c>=97 && c<=122)) {
                a += 1;
            }
        }
        if (a==username.length) {
            return true;
        }
        return false;
    }
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }
    public User(String username, int age) {
        this.username = username;
        this.age = age;
        this.password = null;
    }
}
