package library_management_class;

public class User {

    private int userId;
    private String password;
    private String userName;
    private String address;

    // getters
    public int getUserId() {
        return this.userId;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getAddress() {
        return this.address;
    }

    // setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
