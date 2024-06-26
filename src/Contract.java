import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Contract implements Comparable<Contract> {
    Costumer costumer;
    Car car;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int maxKm;
    private int startingKm;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private int contractID;
    static int contractIDCounter;


    public Contract(Costumer costumer, Car car, String fromTimeAndDate, String toTimeAndDate, int maxKm, int startingKm, int contractID) {
        this.costumer = costumer;
        this.car = car;
        this.fromDate = LocalDate.parse(fromTimeAndDate, formatter);
        this.toDate = LocalDate.parse(toTimeAndDate, formatter);
        this.maxKm = maxKm;
        this.startingKm = startingKm;
        contractIDCounter++;
        this.contractID = contractIDCounter;
    }


    public LocalDate getFromTimeAndDate() {
        return fromDate;
    }

    public void setFromTimeAndDate(String fromTimeAndDate) {
        this.fromDate = LocalDate.parse(fromTimeAndDate, formatter);
    }

    public String getToTimeAndDate() {
        return toDate.format(formatter);
    }

    public void setToTimeAndDate(String toTimeAndDate) {
        this.toDate = LocalDate.parse(toTimeAndDate, formatter);
    }

    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }

    public int getStartingKm() {
        return startingKm;
    }

    public void setStartingKm(int startingKm) {
        this.startingKm = startingKm;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public String toString() {
        return costumer.getName() + " / " + costumer.getMobilePhoneNumber() + "\nBrand: " + car.getBrand() + "\nModel: " + car.getModel() + "\nI perioden: " + fromDate + " / " + toDate + "\nBilen starter på: " + startingKm + " KM" + "\nOg kunden kan kører: " + maxKm + "\n" + contractID + "\n--------------------------------";
    }

    public String getStringToSave() {
        return car.getCarID() + "," + costumer.getCostumerID() + "," + fromDate + "," + toDate + "," + maxKm + "," + startingKm + "," + contractID;
    }

    public int compareTo(Contract other) {
        return this.fromDate.compareTo(other.fromDate);
    }
}