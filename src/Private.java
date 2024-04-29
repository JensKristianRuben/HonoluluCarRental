public class Private extends Costumer {
    private String driverLicenseNumber;
    private String driverSinceDate;


    public Private (String name, String address, String postNumber, String city, String phoneNumber, String mobilePhoneNumber, String email, String driverLicenseNumber, String driverSinceDate, int costumerID){
        super(name, address, postNumber, city, phoneNumber, mobilePhoneNumber, email, costumerID);
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverSinceDate = driverSinceDate;
    }

    public Private(String name, String address, String postNumber, String city, String phoneNumber, String mobilePhoneNumber, String email, String driverLicenseNumber, String driverSinceDate) {
        super(name, address, postNumber, city, phoneNumber, mobilePhoneNumber, email);
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverSinceDate = driverSinceDate;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getDriverSinceDate() {
        return driverSinceDate;
    }

    public void setDriverSinceDate(String driverSinceDate) {
        this.driverSinceDate = driverSinceDate;
    }
    public String getStringToSave(){
        return getCostumerID() + "," + getName() + "," + getAddress() + "," + getPostNumber() + "," + getCity() + "," + getPhoneNumber() + "," + getMobilePhoneNumber() + "," + getEmail() + "," + driverLicenseNumber + "," + driverSinceDate;
    }
}
