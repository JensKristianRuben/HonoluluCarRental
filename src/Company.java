public class Company extends Costumer {
    private String companyName;
    private String companyAddress;
    private String companyPhoneNumber;
    private String crn;

    public Company(String name, String address, String postNumber, String city, String phoneNumber, String mobilePhoneNumber, String email, String companyName, String companyAddress, String companyPhoneNumber, String crn, int costumerID) {
        super(name, address, postNumber, city, phoneNumber, mobilePhoneNumber, email, costumerID);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPhoneNumber = companyPhoneNumber;
        this.crn = crn;
    }

    public Company(String name, String address, String postNumber, String city, String phoneNumber, String mobilePhoneNumber, String email, String companyName, String companyAddress, String companyPhoneNumber, String crn) {
        super(name, address, postNumber, city, phoneNumber, mobilePhoneNumber, email);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPhoneNumber = companyPhoneNumber;
        this.crn = crn;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }
    public String getStringToSave(){
        return getCostumerID() + "," + getName() + "," + getAddress() + "," + getPostNumber() + "," + getCity() + "," + getPhoneNumber() + "," + getMobilePhoneNumber() + "," + getEmail() + "," + companyName + "," + companyAddress + "," + companyPhoneNumber + "," + crn;
    }

    public String toString(){
        return "Erhvers Navn: " + companyName + "\nErhvers mobil nummer: " + companyPhoneNumber + "\nEmail: " + getEmail() + "\n--------------------------";
    }
}

