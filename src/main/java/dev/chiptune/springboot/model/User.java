package dev.chiptune.springboot.model;

import java.util.Objects;

public class User {
    private String userId;
    private String userName;
    private int userAge;
    private String userJoinDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserJoinDate() {
        return userJoinDate;
    }

    public void setUserJoinDate(String userJoinDate) {
        this.userJoinDate = userJoinDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userAge == user.userAge && Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(userJoinDate, user.userJoinDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userAge, userJoinDate);
    }
}
