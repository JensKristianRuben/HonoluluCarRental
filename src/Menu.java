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
                    Collections.sort(contracts);
                    printContractsArrayList(contracts);
                    break;
                case 7:
                    System.out.println("Afslutter programmet!");
                    saveCarsToFile(cars);
                    saveCostumersToFile(costumers);
                    saveContractsToFile(contracts);
                    return;
                default:
                    System.out.println("Ugyldigt input. Prøv med et heltal mellem 1-7.");
            }
        }
    }

    public void createNewCar(Scanner scan, ArrayList<Car> cars) {
        String model = getStringInput(scan, "Hvilket model er bilen: ");
        String brand = getStringInput(scan, " Hvilket brand er bilen: ");
        String fuelType = getStringInput(scan, "Hvilket fuel type er bilen: ");
        String regNumber = getStringInput(scan, "Hvad er bilens regNumber: ");
        String regYearAndMonth = getStringInput(scan, "Hvad er regYearMonth: ");
        int kmDriven = getIntInput(scan, "Hvor mange km kørt: ");
        int ccm = getIntInput(scan, "Hvar er bilens ccm:");
        boolean automaticGear = getBooleanInput(scan, "Er bilen automatisk gear (ja/nej) ");
        boolean aircon = getBooleanInput(scan, "Har bilen aircon: (ja/nej)");
        boolean cruiseControl = getBooleanInput(scan, "har bilen cruise control: (ja/nej)");
        boolean leatherSeats = getBooleanInput(scan, "Er der læder sæder: (ja/nej)");
        int hp = getIntInput(scan, "hvor mange hp har bilen: ");
        int amountOfSeats = getIntInput(scan, "hvor mange sætter har bilen: ");
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
            String name = getStringInput(scan, "Hvad er kundens navn: ");
            String address = getStringInput(scan, "Hvad er kunden addresse: ");
            String postNumber = getStringInput(scan, "Hvad er postnummeret på kunden: ");
            String city = getStringInput(scan, "Hvilken by bor kunden i: ");
            String phoneNumber = getStringInput(scan, "Hvad er kundens telefonnummer: ");
            String mobilePhoneNumber = getStringInput(scan, "Hvad er kundens mobil telefonnummer: ");
            String email = getStringInput(scan, "Hvad er kunden email: ");
            String licenseId = getStringInput(scan, "Hvad er førens kørekort ID: ");
            String licenseSincetime = getStringInput(scan, "Hvor længe har føren haft kørekort: format (år-mm-dd)");

            costumers.add(new Private(name, address, postNumber, city, phoneNumber, mobilePhoneNumber, email, licenseId, licenseSincetime));

        } else if (costumerType == 2) {
            String name = getStringInput(scan, "Hvad er kundens navn: ");
            String address = getStringInput(scan, "Hvad er kunden addresse: ");
            String postNumber = getStringInput(scan, "Hvad er postnummeret på kunden: ");
            String city = getStringInput(scan, "Hvilken by bor kunden i: ");
            String phoneNumber = getStringInput(scan, "Hvad er kundens telefonnummer: ");
            String mobilePhoneNumber = getStringInput(scan, "Hvad er kundens mobil telefonnummer: ");
            String email = getStringInput(scan, "Hvad er kunden email: ");
            String companyName = getStringInput(scan, "Hvad er erhvervets navn: ");
            String companyAddress = getStringInput(scan, "Hvad er erhvervets addresse: ");
            String companyPhoneNumber = getStringInput(scan, "Hvad er erhvervets telefon nummer: ");
            String crn = getStringInput(scan, "hver er erhvervets CRN: ");

            costumers.add(new Company(name, address, postNumber, city, phoneNumber, mobilePhoneNumber, email, companyName, companyAddress, companyPhoneNumber, crn));
        }
    }

    public void createNewRentalContract(Scanner scan, ArrayList<Contract> contracts) {
        printCostumersArrayList(costumers);
        String mobileNumber = getStringInput(scan, "Hvem er kunden: (brug mobilnummer)");
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
        int carID = getIntInput(scan, "Hvilken bil vil kunden gerne leje: (brug ID) ");
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
        String fromTimeAndDate = getStringInput(scan, "Hvornår vil kunden gerne leje bilen fra: ");
        String toTimeAndDate = getStringInput(scan, "Hvornår vil kunden gerne leje bilen til: ");
        int maxKM = getIntInput(scan, "Hvor mange kilometer vil kunden gerne køre: ");
        int startingKM = getIntInput(scan, "Hvor meget har bilen kørt på nuværende tidspunkt: ");

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

    public static String getStringInput(Scanner scan, String prompt) {
        System.out.println(prompt);
        return scan.nextLine();
    }

    public static int getIntInput(Scanner scan, String prompt) {
        int input = 0;
        boolean isValid = false;

        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scan.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("indtast venligst et heltal.");
                System.out.println();
            }
        } while (!isValid);
        return input;
    }

    public static boolean getBooleanInput(Scanner scan, String prompt) {
        boolean input = false;
        boolean isValid = false;
        do {
            System.out.println(prompt);
            String userInput = scan.nextLine().toLowerCase();
            if (userInput.equals("ja")) {
                input = true;
                isValid = true;
            } else if (userInput.equals("nej")) {
                input = false;
                isValid = true;
            } else {
                System.out.println("Forkert indtastning. prøv med ja/nej");
                System.out.println();
            }
        } while (!isValid);
        return input;
    }

    public void printmostPopCars(ArrayList<Contract> contracts) {
        for (Contract contract : contracts) {
        }
    }
}
