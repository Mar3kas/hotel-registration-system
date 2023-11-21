package logic;

import model.Guest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

public class HotelManagement {
    private static final int NUM_ROOMS = 5;
    private static final int ROOM_NUMBER_OFFSET = 1;
    private static final String FILE_NAME = "hotel_data.txt";
    private final Guest[] guests = new Guest[NUM_ROOMS];
    private final Map<Integer, ArrayList<Guest>> roomHistory = new HashMap<>(NUM_ROOMS);

    public void registerGuest(Scanner scanner) {
        OptionalInt availableRoom = IntStream.range(0, NUM_ROOMS)
                .filter(i -> guests[i] == null)
                .findFirst();

        if (availableRoom.isPresent()) {
            int roomNum = availableRoom.getAsInt() + ROOM_NUMBER_OFFSET;

            System.out.println("Enter guest name:");
            String name = scanner.nextLine().trim();

            System.out.println("Enter guest surname:");
            String surname = scanner.nextLine().trim();

            if (!name.isEmpty() && !surname.isEmpty()) {
                guests[availableRoom.getAsInt()] = new Guest(name, surname);

                System.out.printf("Registered as guest! Room number %d%n", roomNum);
            } else {
                System.out.println("Invalid input. Guest name and surname cannot be empty.");
            }
        } else {
            System.out.println("All rooms are occupied! No more registrations allowed.");
        }
    }

    public void checkOutGuest(Scanner scanner) {
        System.out.println("Enter room number to check-out: ");
        int roomNumber = scanner.nextInt();

        if (roomNumber >= ROOM_NUMBER_OFFSET && roomNumber <= NUM_ROOMS) {
            if (guests[roomNumber - ROOM_NUMBER_OFFSET] != null) {
                System.out.printf("Checking out guest from room %d%n", roomNumber);

                roomHistory.computeIfAbsent(roomNumber, k -> new ArrayList<>());
                roomHistory.get(roomNumber).add(guests[roomNumber - ROOM_NUMBER_OFFSET]);

                guests[roomNumber - 1] = null;
            } else {
                System.out.printf("Room %d is already empty.%n", roomNumber);
            }
        } else {
            System.out.println("Invalid room number. Please enter a valid room number.");
        }
    }

    private String getRoomOccupancyStatus(int roomIndex) {
        int roomNum = roomIndex + ROOM_NUMBER_OFFSET;

        return (guests[roomIndex] != null) ?
                String.format("Room %d is occupied by %s", roomNum, guests[roomIndex].toString())
                : String.format("Room %d is available", roomNum);
    }

    private String getRoomHistoryStatus(int roomIndex) {
        int roomNum = roomIndex + ROOM_NUMBER_OFFSET;

        return roomHistory.containsKey(roomNum) ?
                String.format("Room %d was occupied by %s", roomNum, roomHistory.get(roomNum))
                : String.format("Room %d was never occupied", roomNum);
    }

    public void roomOccupancyReview() {
        System.out.println("Room Occupancy Review:");

        IntStream.range(0, NUM_ROOMS)
                .mapToObj(this::getRoomOccupancyStatus)
                .forEach(System.out::println);
    }

    public void roomHistoryAndStatus() {
        System.out.println("Room History and Status:");

        IntStream.range(0, NUM_ROOMS)
                .mapToObj(i -> getRoomOccupancyStatus(i) + " | " + getRoomHistoryStatus(i))
                .forEach(System.out::println);
    }

    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writeCurrentGuests(writer);
            writeRoomHistory(writer);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void writeCurrentGuests(BufferedWriter writer) throws IOException {
        writer.write("Current Guests:\n");
        for (int roomNum = ROOM_NUMBER_OFFSET; roomNum <= NUM_ROOMS; roomNum++) {
            Guest guest = guests[roomNum - ROOM_NUMBER_OFFSET];
            if (guest != null) {
                writer.write(String.format("Room %d: %s%n", roomNum, guest));
            }
        }
    }

    private void writeRoomHistory(BufferedWriter writer) throws IOException {
        writer.write("Room History:\n");
        for (Map.Entry<Integer, ArrayList<Guest>> entry : roomHistory.entrySet()) {
            int roomNum = entry.getKey();
            ArrayList<Guest> guestsInRoom = entry.getValue();
            writer.write(String.format("Room %d: ", roomNum));
            for (Guest guest : guestsInRoom) {
                writer.write(guest + " | ");
            }
            writer.write("\n");
        }
    }
}