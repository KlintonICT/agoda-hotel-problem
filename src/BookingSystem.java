import java.util.*;

import static java.lang.Integer.parseInt;

public class BookingSystem {
    public static void main(String[] args) {
        Scanner line = new Scanner(System.in);
        String[] commands = {"create room", "book", "cancel"};

        try {
            int lineNum = line.nextInt();

            Map<Integer, Booking> bookings = new HashMap<>(); // key = bookingID, value = Booking
            Map<Integer, Room> rooms = new HashMap<>(); // key = roomID, value = room
            int bookingNumber = 1;
            int roomNumber = 1;

            for (int i = 0; i < lineNum; i++) {
                Scanner command = new Scanner(System.in);
                String textLine = command.nextLine();

                if (textLine.contains(commands[0])) {
                    // create room
                    String[] items = textLine.split(commands[0]);
                    rooms.put(roomNumber, new Room(roomNumber, items[1]));
                    roomNumber += 1;
                } else if (textLine.contains(commands[1])) {
                    // booking room
                    String[] items = textLine.split(" ");
                    int tempRoomID = parseInt(items[1]);
                    int checkIn = parseInt(items[2]);
                    int checkOut = parseInt(items[3]);
                    boolean isAllowBooking = true;

                    for (Map.Entry<Integer, Booking> booking : bookings.entrySet()) {
                        if (booking.getValue().getRoom().getRoomID() == tempRoomID && checkIn >= booking.getValue().getCheckInDate() && checkIn <= booking.getValue().getCheckOutDate()) {
                            isAllowBooking = false;
                            break;
                        }
                    }

                    if (isAllowBooking) {
                        bookings.put(bookingNumber, new Booking(rooms.get(tempRoomID), bookingNumber, checkIn, checkOut));
                        bookingNumber += 1;
                    }
                } else if (textLine.contains(commands[2])) {
                    // cancel room
                    String[] items = textLine.split(" ");
                    int cancelBookingNum = parseInt(items[1]);
                    bookings.remove(cancelBookingNum);
                } else {
                    System.out.println("Input Command incorrect");
                }
            }

            for (Map.Entry<Integer, Room> room : rooms.entrySet()) {
                String roomName = room.getValue().getName();
                System.out.println("Room: " + roomName);
                for (Map.Entry<Integer, Booking> booking : bookings.entrySet()) {
                    if (booking.getValue().getRoom().getRoomID() == room.getKey()) {
                        System.out.println("Booking Id " + booking.getValue().getBookingID() + ": " + booking.getValue().getCheckInDate() + " -> " + booking.getValue().getCheckOutDate());
                    }
                }
                System.out.println();
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
