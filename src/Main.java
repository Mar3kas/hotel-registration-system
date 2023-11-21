import logic.HotelManagement;

import java.util.Scanner;

public class Main {
    private static final int OPTION_REGISTER_GUEST = 1;
    private static final int OPTION_CHECK_OUT_GUEST = 2;
    private static final int OPTION_ROOM_OCCUPANCY_REVIEW = 3;
    private static final int OPTION_ROOM_HISTORY_AND_STATUS = 4;
    private static final int OPTION_EXIT = 0;

    public static void main(String[] args) {
        HotelManagement hotelManagement = new HotelManagement();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("1. Register Guest");
            System.out.println("2. Check-out Guest");
            System.out.println("3. Room Occupancy Review");
            System.out.println("4. Room History and Status");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid choice. Please try again.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case OPTION_REGISTER_GUEST:
                    hotelManagement.registerGuest(scanner);
                    break;
                case OPTION_CHECK_OUT_GUEST:
                    hotelManagement.checkOutGuest(scanner);
                    break;
                case OPTION_ROOM_OCCUPANCY_REVIEW:
                    hotelManagement.roomOccupancyReview();
                    break;
                case OPTION_ROOM_HISTORY_AND_STATUS:
                    hotelManagement.roomHistoryAndStatus();
                    break;
                case OPTION_EXIT:
                    hotelManagement.saveData();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != OPTION_EXIT);

        scanner.close();
    }
}