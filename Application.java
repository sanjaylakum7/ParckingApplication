import java.util.Scanner;

public class Application {
    private static CarPark carPark;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCarPark();
        int choice;
        do {
            displayMenu();
            choice = getValidIntInput("Enter your choice: ");
            handleMenuSelection(choice);
        } while (choice != 8);
    }

    private static void initializeCarPark() {
        System.out.println("Welcome to the Parking Spot System!");
        int staffSlots = getValidIntInput("Enter the number of staff parking slots: ");
        int visitorSlots = getValidIntInput("Enter the number of visitor parking slots: ");
        carPark = new CarPark(staffSlots, visitorSlots);
        System.out.println("Car park initialized.");
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add a parking slot");
        System.out.println("2. Delete a parking slot");
        System.out.println("3. List all slots");
        System.out.println("4. Delete all unoccupied parking slots");
        System.out.println("5. Park a car into a slot");
        System.out.println("6. Find a car by registration number");
        System.out.println("7. Remove a car by registration number");
        System.out.println("8. Exit");
    }

    private static void handleMenuSelection(int choice) {
        switch (choice) {
            case 1:
                addParkingSlot();
                break;
            case 2:
                deleteParkingSlot();
                break;
            case 3:
                carPark.listAllSlots();
                break;
            case 4:
                carPark.deleteUnoccupiedSlots();
                break;
            case 5:
                parkCar();
                break;
            case 6:
                findCar();
                break;
            case 7:
                removeCar();
                break;
            case 8:
                exitProgram();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addParkingSlot() {
        String slotID = getValidSlotID();
        System.out.print("Is this slot for staff? (yes/no): ");
        boolean isForStaff = scanner.nextLine().trim().equalsIgnoreCase("yes");
        carPark.addSlot(slotID, isForStaff);
    }

    private static void deleteParkingSlot() {
        String slotID = getValidSlotID();
        carPark.deleteSlot(slotID);
    }

    private static void parkCar() {
        String slotID = getValidSlotID();
        String regNumber = getValidCarRegNumber();
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine().trim();
        System.out.print("Is the owner a staff member? (yes/no): ");
        boolean isStaffMember = scanner.nextLine().trim().equalsIgnoreCase("yes");
        Car car = new Car(regNumber, ownerName, isStaffMember);
        carPark.parkCar(slotID, car);
    }

    private static void findCar() {
        String regNumber = getValidCarRegNumber();
        carPark.findCar(regNumber);
    }

    private static void removeCar() {
        String regNumber = getValidCarRegNumber();
        carPark.removeCar(regNumber);
    }

    private static void exitProgram() {
        System.out.println("Program end!");
    }

    private static String getValidSlotID() {
        String slotID;
        do {
            System.out.print("Enter slot ID (e.g., D01): ");
            slotID = scanner.nextLine().trim().toUpperCase();
        } while (!slotID.matches("^[A-Z]\\d{2}$"));
        return slotID;
    }

    private static String getValidCarRegNumber() {
        String regNumber;
        do {
            System.out.print("Enter car registration number (e.g., T2345): ");
            regNumber = scanner.nextLine().trim().toUpperCase();
        } while (!regNumber.matches("^[A-Z]\\d{4}$"));
        return regNumber;
    }

    private static int getValidIntInput(String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}