import java.util.HashMap;
import java.util.Map;

/*
 * BookMyStayApp
 * Use Case 3: Centralized Room Inventory Management
 */

public class BookMyStayApp {

    /* =========================
       ROOM DOMAIN MODEL
       ========================= */

    static class Room {
        private String type;
        private int beds;
        private int size;
        private double pricePerNight;

        public Room(String type, int beds, int size, double pricePerNight) {
            this.type = type;
            this.beds = beds;
            this.size = size;
            this.pricePerNight = pricePerNight;
        }

        public String getType() {
            return type;
        }

        public int getBeds() {
            return beds;
        }

        public int getSize() {
            return size;
        }

        public double getPricePerNight() {
            return pricePerNight;
        }
    }

    /* =========================
       CENTRALIZED INVENTORY
       ========================= */

    static class RoomInventory {

        // Single Source of Truth
        private HashMap<String, Integer> availability = new HashMap<>();

        // Register room type
        public void registerRoom(String roomType, int count) {
            availability.put(roomType, count);
        }

        // Get availability
        public int getAvailableRooms(String roomType) {
            return availability.getOrDefault(roomType, 0);
        }

        // Book room (controlled update)
        public boolean bookRoom(String roomType) {
            int available = getAvailableRooms(roomType);

            if (available > 0) {
                availability.put(roomType, available - 1);
                return true;
            }
            return false;
        }

        // Release room
        public void releaseRoom(String roomType) {
            availability.put(roomType,
                    getAvailableRooms(roomType) + 1);
        }

        // Display inventory
        public void displayInventory(Map<String, Room> rooms) {

            System.out.println("Hotel Room Inventory Status\n");

            for (String type : rooms.keySet()) {

                Room room = rooms.get(type);

                System.out.println(type + ":");
                System.out.println("Beds: " + room.getBeds());
                System.out.println("Size: " + room.getSize() + " sqft");
                System.out.println("Price per night: " + room.getPricePerNight());
                System.out.println("Available Rooms: "
                        + getAvailableRooms(type));
                System.out.println();
            }
        }
    }

    /* =========================
       APPLICATION ENTRY POINT
       ========================= */

    public static void main(String[] args) {

        // Room Definitions (Domain)
        Map<String, Room> rooms = new HashMap<>();

        rooms.put("Single Room",
                new Room("Single Room", 1, 250, 1500.0));

        rooms.put("Double Room",
                new Room("Double Room", 2, 400, 2500.0));

        rooms.put("Suite Room",
                new Room("Suite Room", 3, 750, 5000.0));

        // Inventory Initialization
        RoomInventory inventory = new RoomInventory();

        inventory.registerRoom("Single Room", 5);
        inventory.registerRoom("Double Room", 3);
        inventory.registerRoom("Suite Room", 2);

        // Display Initial Inventory
        inventory.displayInventory(rooms);

        // Example operation
        System.out.println("Booking one Single Room...\n");

        if (inventory.bookRoom("Single Room")) {
            System.out.println("Booking Successful!\n");
        } else {
            System.out.println("No rooms available!\n");
        }

        // Display Updated Inventory
        inventory.displayInventory(rooms);
    }
}