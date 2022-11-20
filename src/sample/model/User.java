package sample.model;

public class User {
    private String userName;
    private int userId;
    private String password;


    public User(String userName, int userId, String password) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
    }
    /**
     * @return userName
     * */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     * */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return userId
     * */
    public int getUserId() {
        return userId;
    }
    /**
     * @param userId
     * */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * @return password
     * */
    public String getPassword() {
        return password;
    }
    /**
     * @param password
     * */
    public void setPassword(String password) {
        this.password = password;
    }




}
