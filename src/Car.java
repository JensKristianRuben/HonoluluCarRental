public class Car {

    private String brand;
    private String model;
    private String fuelType;
    private String regNumber;
    private String regYearAndMonth;
    private int kmDriven;
    private int ccm;
    private boolean automaticGear;
    private boolean aircon;
    private boolean cruiseControl;
    private boolean leatherSeats;
    private int hp;
    private int amountOfSeats;
    private int carID;
    static int carIDCounter;

    public Car(String brand, String model, String fuelType, String regNumber, String regYearAndMonth, int kmDriven, int ccm, boolean automaticGear, boolean aircon, boolean cruiseControl, boolean leatherSeats, int hp, int amountOfSeats, int carID) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.regNumber = regNumber;
        this.regYearAndMonth = regYearAndMonth;
        this.kmDriven = kmDriven;
        this.ccm = ccm;
        this.automaticGear = automaticGear;
        this.aircon = aircon;
        this.cruiseControl = cruiseControl;
        this.leatherSeats = leatherSeats;
        this.hp = hp;
        this.amountOfSeats = amountOfSeats;
        carIDCounter++;
        this.carID = carIDCounter;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegYearAndMonth() {
        return regYearAndMonth;
    }

    public void setRegYearAndMonth(String regYearAndMonth) {
        this.regYearAndMonth = regYearAndMonth;
    }

    public int getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(int kmDriven) {
        this.kmDriven = kmDriven;
    }

    public int getCcm() {
        return ccm;
    }

    public void setCcm(int ccm) {
        this.ccm = ccm;
    }

    public boolean isAutomaticGear() {
        return automaticGear;
    }

    public void setAutomaticGear(boolean automaticGear) {
        this.automaticGear = automaticGear;
    }

    public boolean isAircon() {
        return aircon;
    }

    public void setAircon(boolean aircon) {
        this.aircon = aircon;
    }

    public boolean isCruiseControl() {
        return cruiseControl;
    }

    public void setCruiseControl(boolean cruiseControl) {
        this.cruiseControl = cruiseControl;
    }

    public boolean isLeatherSeats() {
        return leatherSeats;
    }

    public void setLeatherSeats(boolean leatherSeats) {
        this.leatherSeats = leatherSeats;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }
    public boolean isLuxury(){
        return ccm > 3000 && automaticGear && aircon && leatherSeats && cruiseControl;
    }
    public boolean isFamily(){
        return !automaticGear && aircon && amountOfSeats >= 7;
    }
    public boolean isSport(){
        return !automaticGear && hp > 200;
    }

    public String toString() {
        String carType = "Almen";
        if (isLuxury()){
            carType = "Luxury";
        } else if (isFamily()) {
            carType = "Family";
        } else if (isSport()) {
            carType = "Sport";
        }
        return "Brand: " + brand + "\nModel: " + model + "\nID: " + carID + "\nBilType: " + carType + "\n --------------------------";
    }
    public String getStringToSave() {
        return carID + "," + brand + "," + model + "," + fuelType + "," + regNumber + "," + regYearAndMonth + "," + kmDriven + "," + ccm + "," + automaticGear + "," + aircon + "," + cruiseControl + "," + leatherSeats + "," + hp + "," + amountOfSeats;
    }
}