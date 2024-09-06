package Roles;

import Features.Features;

import java.util.ArrayList;

public class User {
    private final String user_data = "src/Database/User.txt";
    ArrayList<User> users = new ArrayList<>();

    private String id;
    private String fullname;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private String userType;
    private String status;
    private String membership;
    private String username;
    private String password;
    private long totalSpending;

    public User(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String membership,String username, String password, long totalSpending) {
        this.id = id;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userType = userType;
        this.status = status;
        this.membership = membership;
        this.username = username;
        this.password = password;
        this.totalSpending = totalSpending;
    }

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(long totalSpending) {
        this.totalSpending = totalSpending;
    }

    // read data from database and add to arraylist
    public void readData() {
        users.clear();
        int countLine = Features.countLine(user_data);
        String[] id = Features.ReadCol(0, user_data, ",");
        String[] fullname = Features.ReadCol(1, user_data, ",");
        String[] datesOfBirth = Features.ReadCol(2, user_data, ",");
        String[] address = Features.ReadCol(3, user_data, ",");
        String[] phoneNumber = Features.ReadCol(4, user_data, ",");
        String[] email = Features.ReadCol(5, user_data, ",");
        String[] userType = Features.ReadCol(6, user_data, ",");
        String[] status = Features.ReadCol(7, user_data, ",");
        String[] membership = Features.ReadCol(8, user_data, ",");
        String[] username = Features.ReadCol(9, user_data, ",");
        String[] password = Features.ReadCol(10, user_data, ",");
        String[] totalSpending = Features.ReadCol(11, user_data, ",");

        // Check if arrays have the same length
        for (int i = 0; i < countLine - 1; i++) {
            users.add(new User(
                    id[i], fullname[i], datesOfBirth[i], address[i], phoneNumber[i], email[i], userType[i], status[i], membership[i],username[i], password[i], Long.parseLong(totalSpending[i])
            ));
        }
    }

    public boolean authenticate(String username, String password, String userType) {
        return this.username.equals(username) && this.password.equals(password) && this.userType.equals(userType);
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername() != null && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    //update membership everytime their spending changes.
    public void updateMembership(String username){
        readData();

        User user = getUser(username);
        String oldContent = user.getId() + "," + user.getFullname() + "," + user.getDateOfBirth() + "," + user.getAddress() + "," + user.getPhoneNumber() + "," + user.getEmail() + "," + user.getUserType() + "," + user.getStatus() + "," + user.getMembership() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getTotalSpending();

        String[] updateCustomerInfo = oldContent.split(",");
        String updatedContent = "";

        String newMembership = "";

        if(user.getTotalSpending() < 30000000){
            newMembership = "Regular";
        }
        else if(user.getTotalSpending() < 100000000){
            newMembership = "Silver";
        }
        else if(user.getTotalSpending() < 250000000){
            newMembership = "Gold";
        }
        else{
            newMembership = "Platinum";
        }
        updateCustomerInfo[8] = String.valueOf(newMembership);
        updatedContent = Features.arrayToCSVString(updateCustomerInfo);
        Features.modifyFile(user_data, oldContent, updatedContent);
    }

    public void addSpending(String username, long orderPrice){
        readData();
        User user = getUser(username);
        String oldContent = user.getId() + "," + user.getFullname() + "," + user.getDateOfBirth() + "," + user.getAddress() + "," + user.getPhoneNumber() + "," + user.getEmail() + "," + user.getUserType() + "," + user.getStatus() + "," + user.getMembership() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getTotalSpending();

        String[] updateClientInfo = oldContent.split(",");
        String updatedContent = "";

        long newSpending = getTotalSpending()+orderPrice;

        updateClientInfo[11] = String.valueOf(newSpending);
        updatedContent = Features.arrayToCSVString(updateClientInfo);
        Features.modifyFile(user_data, oldContent, updatedContent);
    }
}
