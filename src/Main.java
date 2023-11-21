import management.HotelManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HotelManagement hotelManagement = new HotelManagement();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Register Guest");
            System.out.println("2. Check-out Guest");
            System.out.println("3. Room Occupancy Review");
            System.out.println("4. Room History and Status");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    hotelManagement.registerGuest(scanner);
                    break;
                case 2:
                    hotelManagement.checkOutGuest(scanner);
                    break;
                case 3:
                    hotelManagement.roomOccupancyReview();
                    break;
                case 4:
                    hotelManagement.roomHistoryAndStatus();
                    break;
                case 0:
                    hotelManagement.saveData();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}