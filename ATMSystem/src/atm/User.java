package atm;

public class User {
    private String username;
    private String pin;
    private double balance;
    private String accountNumber;
    private String email;
    private String accountType;
    private String ifscCode;
    private String branch;
    private String createdDate;
    private String lastLogin;

    // Constructor
    public User(String username, String pin, double balance, String accountNumber,
                String email, String accountType, String ifscCode, String branch,
                String createdDate, String lastLogin)
    {
        this.username = username;
        this.pin = pin;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.email = email;
        this.accountType = accountType;
        this.ifscCode = ifscCode;
        this.branch = branch;
        this.createdDate = createdDate;
        this.lastLogin = lastLogin;
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}
