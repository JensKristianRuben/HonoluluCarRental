import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.*;

import static java.util.Comparator.*;

public class Menu {
    Scanner scan;
    private ArrayList<Car> cars;
    private ArrayList<Costumer> costumers;
    private ArrayList<Contract> contracts;
    final private String pathToCarsTxt = "src/dataFiles/cars.txt";
    final private String pathToContractsTxt = "src/dataFiles/contracts.txt";
    final private String pathToCostumersTxt = "src/dataFiles/costumers.txt";

    public Menu() {
        System.out.println();
        System.out.println("Helloworld!");
        this.scan = new Scanner(System.in);
        this.cars = new ArrayList<>();
        this.costumers = new ArrayList<>();
        this.contracts = new ArrayList<>();
    }


    public void menuOverview() throws FileNotFoundException {
        loadCarsFromFile(cars);
        loadCostumersFromFile(costumers);
        loadContractsFromFile(contracts, cars, costumers);
        while (true) {
            System.out.println("""
                    *---------------------------------*
                    | 1. Tilføj ny bil                |
                    | 2. Tilføj ny kunde              |
                    | 3. Opret udlejningskontrakt     |
                    | 4. Vis bil kartotek             |
                    | 5. Vis kunde kartotek           |
                    | 6. Vis kontrakt kartotek        |
                    | 7. Gemme og afslutte programmet |
                    *---------------------------------*
                    """);
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    createNewCar(scan, cars);
                    break;
                case 2:
                    createNewCostumer();
                    break;
                case 3:
                    createNewRentalContract(scan, contracts);
                    break;
                case 4:
                    printCarsArraylist(cars);
                    break;
                case 5:
                    printCostumersArrayList(costumers);
                    break;
                case 6:
                    printContractsArrayList(contracts);
                    break;
                case 7:
                    System.out.println("Afslutter programmet!");
                    saveCarsToFile(cars);
                    saveCostumersToFile(costumers);
                    saveContractsToFile(contracts);
                    return;
                default:
                    System.out.println("Ugyldigt input, prøv med et heltal");
            }
        }
    }

    public void createNewCar(Scanner scan, ArrayList<Car> cars) {
        System.out.println("Hvilket model er bilen: ");
        String model = scan.nextLine();
        System.out.println("Hvilket brand er bilen: ");
        String brand = scan.nextLine();
        System.out.println("Hvilket fuel type er bilen: ");
        String fuelType = scan.nextLine();
        System.out.println("Hvad er bilens regNumber: ");
        String regNumber = scan.nextLine();
        System.out.println("Hvad er regYearMonth: ");
        String regYearAndMonth = scan.nextLine();
        System.out.println("Hvor mange km kørt: ");
        int kmDriven = scan.nextInt();
        System.out.println("Hvar er bilens ccm:");
        int ccm = scan.nextInt();
        System.out.println("Er bilen automatisk gear (true/false): ");
        boolean automaticGear = scan.nextBoolean();
        System.out.println("Har bilen aircon: (true/false) ");
        boolean aircon = scan.nextBoolean();
        System.out.println("har bilen cruise control: (true/false) ");
        boolean cruiseControl = scan.nextBoolean();
        System.out.println("Er der læder sæder: (true/false) ");
        boolean leatherSeats = scan.nextBoolean();
        System.out.println("hvor mange hp har bilen: ");
        int hp = scan.nextInt();
        System.out.println("hvor mange sætter har bilen: ");
        int amountOfSeats = scan.nextInt();
        scan.nextLine();
        int carID = Car.carIDCounter + 1;

        Car newCar = new Car(brand, model, fuelType, regNumber, regYearAndMonth, kmDriven, ccm, automaticGear, aircon, cruiseControl, leatherSeats, hp, amountOfSeats, carID);
        cars.add(newCar);


    }

    public void createNewCostumer() {
        System.out.println("""
                Er kundetypen:
                1: Privat
                2: Erhverv
                """);
        int costumerType = scan.nextInt();
        scan.nextLine();
        if (costumerType == 1) {
            System.out.println("Hvad er kundens navn: ");
            String name = scan.nextLine();
            System.out.println("Hvad er kunden addresse: ");
            String address = scan.nextLine();
            System.out.println("Hvad er postnummeret på kunden: ");
            String postNumber = scan.nextLine();
            System.out.println("Hvilken by bor kunden i: ");
            String city = scan.nextLine();
            System.out.println("Hvad er kundens telefonnummer: ");
            String phoneNumber = scan.nextLine();
            System.out.println("Hvad er kundens mobil telefonnummer: ");
            String mobilePhoneNumber = scan.nextLine();
            System.out.println("Hvad er kunden email: ");
            String email = scan.nextLine();
            System.out.println("Hvad er førens kørekort ID: ");
            String licenseId = scan.nextLine();
            System.out.println("Hvor længe har føren haft kørekort: format (dd-mm-år)");
            String licenseSincetime = scan.nextLine();

            costumers.add(new Private(name, address, postNumber, city, phoneNumber, mobilePhoneNumber, email, licenseId, licenseSincetime));

        } else if (costumerType == 2) {
            System.out.println("Hvad er kundens navn: ");
            String name = scan.nextLine();
            System.out.println("Hvad er kunden addresse: ");
            String address = scan.nextLine();
            System.out.println("Hvad er postnummeret på kunden: ");
            String postNumber = scan.nextLine();
            System.out.println("Hvilken by bor kunden i: ");
            String city = scan.nextLine();
            System.out.println("Hvad er kundens telefonnummer: ");
            String phoneNumber = scan.nextLine();
            System.out.println("Hvad er kundens mobil telefonnummer: ");
            String mobilePhoneNumber = scan.nextLine();
            System.out.println("Hvad er kunden email: ");
            String email = scan.nextLine();
            System.out.println("Hvad er erhvervets navn: ");
            String companyName = scan.nextLine();
            System.out.println("Hvad er erhvervets addresse: ");
            String companyAddress = scan.nextLine();
            System.out.println("Hvad er erhvervets telefon nummer: ");
            String companyPhoneNumber = scan.nextLine();
            System.out.println("hver er erhvervets CRN: ");
            String crn = scan.nextLine();

            costumers.add(new Company(name, address, postNumber, city, phoneNumber, mobilePhoneNumber, email, companyName, companyAddress, companyPhoneNumber, crn));
        }
    }

    public void createNewRentalContract(Scanner scan, ArrayList<Contract> contracts) {
        printCostumersArrayList(costumers);
        System.out.println("Hvem er kunden: (brug mobilnummer)");
        String mobileNumber = scan.nextLine();
        Costumer costumerFound = null;
        for (Costumer costumer : costumers) {
            if (costumer.getMobilePhoneNumber().equals(mobileNumber)) {
                costumerFound = costumer;
                break;
            }
        }
        if (costumerFound == null) {
            System.out.println("Kunden findes ikke");
            return;
        }
        printCarsArraylist(cars);
        System.out.println("Hvilken bil vil kunden gerne leje: (brug ID) ");
        int carID = scan.nextInt();
        Car carFound = null;
        for (Car car : cars) {
            if (car.getCarID() == carID) {
                carFound = car;
                break;
            }
        }
        if (carFound == null) {
            System.out.println("Bilen findes ikke");
            return;
        }
        scan.nextLine();
        System.out.println("Hvornår vil kunden gerne leje bilen fra: ");
        String fromTimeAndDate = scan.nextLine();
        System.out.println("Hvornår vil kunden gerne leje bilen til: ");
        String toTimeAndDate = scan.nextLine();

        System.out.println("Hvor mange kilometer vil kunden gerne køre: ");
        int maxKM = scan.nextInt();
        System.out.println("Hvor meget har bilen kørt på nuværende tidspunkt: ");
        int startingKM = scan.nextInt();

        contracts.add(new Contract(costumerFound, carFound, fromTimeAndDate, toTimeAndDate, maxKM, startingKM));
    }

    public void saveCarsToFile(ArrayList<Car> cars) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new File(pathToCarsTxt));
        for (Car car : cars) {
            ps.println(car.getStringToSave());
        }
    }

    public void loadCarsFromFile(ArrayList<Car> cars) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(pathToCarsTxt));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split(",");
            int carID = Integer.parseInt(parts[0]);
            String brand = parts[1];
            String model = parts[2];
            String fuelType = parts[3];
            String regNumber = parts[4];
            String regYearAndMonth = parts[5];
            int kmDriven = Integer.parseInt(parts[6]);
            int cmm = Integer.parseInt(parts[7]);
            boolean automaticGear = Boolean.parseBoolean(parts[8]);
            boolean aircon = Boolean.parseBoolean(parts[9]);
            boolean cruiseControl = Boolean.parseBoolean(parts[10]);
            boolean leatherSeats = Boolean.parseBoolean(parts[11]);
            int hp = Integer.parseInt(parts[12]);
            int amountOfSeats = Integer.parseInt(parts[13]);

            cars.add(new Car(brand, model, fuelType, regNumber, regYearAndMonth, kmDriven, cmm, automaticGear, aircon, cruiseControl, leatherSeats, hp, amountOfSeats, carID));
        }
    }

    public void saveCostumersToFile(ArrayList<Costumer> costumers) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new File(pathToCostumersTxt));
        for (Costumer costumer : costumers) {
            if (costumer instanceof Private) {
                ps.println(costumer.getStringToSave());
            } else if (costumer instanceof Company) {
                ps.println(costumer.getStringToSave());
            }
        }
    }

    public void loadCostumersFromFile(ArrayList<Costumer> costumers) throws FileNotFoundException {

        Scanner scan = new Scanner(new File(pathToCostumersTxt));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split(",");

            if (parts.length == 10) {
                int costumerID = Integer.parseInt(parts[0]);
                String name = parts[1];
                String address = parts[2];
                String postNumber = parts[3];
                String city = parts[4];
                String phoneNumber = parts[5];
                String mobilePhoneNumber = parts[6];
                String email = parts[7];
                String driverLicenseNumber = parts[8];
                String driverSinceDate = parts[9];
                costumers.add(new Private(name, address, postNumber, city, phoneNumber, mobilePhoneNumber, email, driverLicenseNumber, driverSinceDate, costumerID));
            } else if (parts.length == 12) {
                int costumerID = Integer.parseInt(parts[0]);
                String name = parts[1];
                String address = parts[2];
                String postNumber = parts[3];
                String city = parts[4];
                String phoneNumber = parts[5];
                String mobilePhoneNumber = parts[6];
                String email = parts[7];
                String companyName = parts[8];
                String companyAddress = parts[9];
                String companyPhoneNumber = parts[10];
                String crn = parts[11];
                costumers.add(new Company(name, address, postNumber, city, phoneNumber, mobilePhoneNumber, email, companyName, companyAddress, companyPhoneNumber, crn, costumerID));

            }
        }
    }

    public void saveContractsToFile(ArrayList<Contract> contracts) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new File(pathToContractsTxt));
        for (Contract contract : contracts) {
            ps.println(contract.getStringToSave());
        }
    }

    public void loadContractsFromFile(ArrayList<Contract> contracts, ArrayList<Car> cars, ArrayList<Costumer> costumers) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(pathToContractsTxt));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split(",");
            int carID = Integer.parseInt(parts[0]);
            int costumerID = Integer.parseInt(parts[1]);
            String fromTimeAndDate = parts[2];
            String toTimeAndDate = parts[3];
            int maxKM = Integer.parseInt(parts[4]);
            int startingKm = Integer.parseInt(parts[5]);

            Car selectedCar = null;
            Costumer selectedCostumer = null;

            for (Car car : cars) {
                if (carID == car.getCarID()) {
                    selectedCar = car;
                    break;
                }
            }
            for (Costumer costumer : costumers) {
                if (costumerID == costumer.getCostumerID()) {
                    selectedCostumer = costumer;
                    break;
                }
            }
            contracts.add(new Contract(selectedCostumer, selectedCar, fromTimeAndDate, toTimeAndDate, maxKM, startingKm));
        }
    }


    public void printCarsArraylist(ArrayList<Car> cars) {
        System.out.println("<<<BILER KARTOTEK>>>");
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    public void printCostumersArrayList(ArrayList<Costumer> costumers) {
        System.out.println("<<<KUNDER KARTOTEK>>>");
        for (Costumer costumer : costumers) {
            System.out.println(costumer);
        }
    }

    public void printContractsArrayList(ArrayList<Contract> contracts) {
        System.out.println("<<<KONTRAKT KARTOTEK>>>");
        for (Contract contract : contracts) {
            System.out.println(contract);
        }
    }
}
