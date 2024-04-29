public class Costumer {
    private String name;
    private String address;
    private String postNumber;
    private String city;
    private String phoneNumber;
    private String mobilePhoneNumber;
    private String email;
    private int costumerID;
    private static int customerIdCounter;

    public Costumer(String name, String address, String postNumber, String city, String phoneNumber, String mobilePhoneNumber, String email) {
        this(name, address, postNumber, city, phoneNumber, mobilePhoneNumber, email, customerIdCounter);
    }

    public Costumer(String name, String address, String postNumber, String city, String phoneNumber, String mobilePhoneNumber, String email, int costumerID) {
        this.name = name;
        this.address = address;
        this.postNumber = postNumber;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email = email;
        this.costumerID = costumerID;
        customerIdCounter = Math.max(customerIdCounter, costumerID);
        customerIdCounter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCostumerID() {
        return costumerID;
    }

    public void setCostumerID(int costumerID) {
        this.costumerID = costumerID;
    }

    public String getStringToSave() {
        return costumerID + "," + name + "," + address + "," + postNumber + "," + city + "," + phoneNumber + "," + mobilePhoneNumber + "," + email;
    }

    public String toString() {
        return "Navn: " + name + "\nMobile nummer: " + mobilePhoneNumber + "\nEmail: " + email + "\n--------------------------";
    }

}
