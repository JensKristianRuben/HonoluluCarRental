import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
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
                    | 7. vis de mest populære biler   |
                    | 8. lave ændringer               |
                    |    (bil, customer, id)          |
                    | 9. Gemme og afslutte programmet |
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
                    printmostPopCars(contracts);
                    break;
                case 8:
                    System.out.println("""
                            1: Ændre bil
                            2: Ændre kunde
                            3: Ændre kontrakt
                            """);
                    int choice1 = scan.nextInt();
                    scan.nextLine();
                    switch (choice1) {
                        case 1:
                            changeCar(cars, scan);
                            break;
                        case 2:
                            changeCostumer(costumers, scan);
                            break;
                        case 3:
                            changeContracts(contracts, cars, costumers, scan);
                            break;
                        default:
                            System.out.println("Forkert indput, prøv med et heltal mellem 1-3");

                    }
                    break;
                case 9:
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
        int contractID = Contract.contractIDCounter + 1;

        contracts.add(new Contract(costumerFound, carFound, fromTimeAndDate, toTimeAndDate, maxKM, startingKM, contractID));
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
            int contractId = Integer.parseInt(parts[6]);


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
        Map<Integer, Integer> carCounts = new HashMap<>();

        for (Contract contract : contracts) {
            int carID = contract.car.getCarID();
            carCounts.put(carID, carCounts.getOrDefault(carID, 0) + 1);
        }

        int maxCount = 0;
        for (int count : carCounts.values()) {
            maxCount = Math.max(maxCount, count);
        }

        System.out.println("Mest populære biler:");
        for (Map.Entry<Integer, Integer> entry : carCounts.entrySet()) {
            if (entry.getValue() == maxCount) {
                System.out.println("Car ID: " + entry.getKey() + ", Count: " + entry.getValue());
            }
        }
    }

    public void changeCar(ArrayList<Car> cars, Scanner scan) {
        printCarsArraylist(cars);
        int id = getIntInput(scan, "Hvilket bil ville du ændre (id): ");


        for (Car car : cars) {
            if (id == car.getCarID()) {

                car.setModel(getStringInput(scan, "Hvilket model er bilen: "));
                car.setBrand(getStringInput(scan, " Hvilket brand er bilen: "));
                car.setFuelType(getStringInput(scan, "Hvilket fuel type er bilen: "));
                car.setRegNumber(getStringInput(scan, "Hvad er bilens regNumber: "));
                car.setRegNumber(getStringInput(scan, "Hvad er regYearMonth: "));
                car.setKmDriven(getIntInput(scan, "Hvor mange km kørt: "));
                car.setCcm(getIntInput(scan, "Hvar er bilens ccm:"));
                car.setAutomaticGear(getBooleanInput(scan, "Er bilen automatisk gear (ja/nej) "));
                car.setAircon(getBooleanInput(scan, "Har bilen aircon: (ja/nej)"));
                car.setCruiseControl(getBooleanInput(scan, "har bilen cruise control: (ja/nej)"));
                car.setLeatherSeats(getBooleanInput(scan, "Er der læder sæder: (ja/nej)"));
                car.setHp(getIntInput(scan, "hvor mange hp har bilen: "));
                car.setAmountOfSeats(getIntInput(scan, "hvor mange sætter har bilen: "));
                scan.nextLine();


            } else
                System.out.println("Forkert indtastning. evt. prøv igen");
        }
    }

    public void changeCostumer(ArrayList<Costumer> costumers, Scanner scan) {
        printCostumersArrayList(costumers);
        System.out.println("Hvilken kunde vil du gerne ændre? (brug kunde id)");
        int tempID = scan.nextInt();

        for (Costumer costumer : costumers) {
            if (costumer instanceof Private && tempID == costumer.getCostumerID()) {
                costumer.setName(getStringInput(scan, "Hvad vil du gerne ændre navnet til: "));
                costumer.setAddress(getStringInput(scan, "Hvad vil du gerne ændre addressen til: "));
                costumer.setPostNumber(getStringInput(scan, "Hvad vil du gerne ændre postnummeret til: "));
                costumer.setCity(getStringInput(scan, "Hvad vil du gerne ændre byen til: "));
                costumer.setPhoneNumber(getStringInput(scan, "Hvad vil du gerne ændre telefonnummeret til: "));
                costumer.setMobilePhoneNumber(getStringInput(scan, "Hvad vil du gerne ændre mobilnummeret til: "));
                costumer.setEmail(getStringInput(scan, "Hvad vil du gerne ændre emailen til: "));
                ((Private) costumer).setDriverLicenseNumber(getStringInput(scan, "Hvad vil du gerne ændre kørekort nummeret til: "));
                ((Private) costumer).setDriverSinceDate(getStringInput(scan, "Hvad vil du gerne ændre kørekort holderens kørekort dato til: "));
            }

            if (costumer instanceof Company && tempID == costumer.getCostumerID()) {
                costumer.setName(getStringInput(scan, "Hvad vil du gerne ændre navnet til: "));
                costumer.setAddress(getStringInput(scan, "Hvad vil du gerne ændre addressen til: "));
                costumer.setPostNumber(getStringInput(scan, "Hvad vil du gerne ændre postnummeret til: "));
                costumer.setCity(getStringInput(scan, "Hvad vil du gerne ændre byen til: "));
                costumer.setPhoneNumber(getStringInput(scan, "Hvad vil du gerne ændre telefonnummeret til: "));
                costumer.setMobilePhoneNumber(getStringInput(scan, "Hvad vil du gerne ændre mobilnummeret til: "));
                costumer.setEmail(getStringInput(scan, "Hvad vil du gerne ændre emailen til: "));
                ((Company) costumer).setCompanyName(getStringInput(scan, "Hvad skal erhvers navnet ændres til: "));
                ((Company) costumer).setCompanyAddress(getStringInput(scan, "Hvad skal erhvervs addressen ændres til: "));
                ((Company) costumer).setCompanyPhoneNumber(getStringInput(scan, "Hvad skal erhvervs telefonnummeret sættes til: "));
                ((Company) costumer).setCrn(getStringInput(scan, "Hvad skal erhvervets crn sættes til: "));
            } else {
                System.out.println("Forkert indtastning. evt. prøv igen");
            }
        }
    }

    public void changeContracts(ArrayList<Contract> contracts, ArrayList<Car> cars, ArrayList<Costumer> costumers, Scanner scan) {
        System.out.println("Hvilken kontrakt vil du gerne ændre? (brug ID)");
        int tempID = scan.nextInt();
        scan.nextLine();

        for (Contract contract : contracts) {
            if (tempID == contract.getContractID()) {

                contract.setFromTimeAndDate(getStringInput(scan, "Hvad vil du gerne ændre startdatoen til: (år-mm-dd) "));
                contract.setToTimeAndDate(getStringInput(scan, "Hvad vil du gerne ændre slutdatoen til: (år-mm-dd)"));
                contract.setStartingKm(getIntInput(scan, "Hvad skal start kilometer på bilen ændres til: "));
                contract.setMaxKm(getIntInput(scan, "Hvad skal kundens ønskede km antal ændres til: "));
                int carID = getIntInput(scan, "Hvad skal bilen ændres til: (brug ID)");
                int costumerID = getIntInput(scan, "Hvad skal kunden ændres til: (brug ID)");

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
                contract.setCar(selectedCar);
                contract.setCostumer(selectedCostumer);
            }
        }
    }
}
