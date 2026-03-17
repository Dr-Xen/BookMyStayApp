abstract class Room {

    // Encapsulated attributes
    protected int beds;
    protected int size;
    protected double pricePerNight;

    // Constructor
    public Room(int beds, int size, double pricePerNight) {
        this.beds = beds;
        this.size = size;
        this.pricePerNight = pricePerNight;
    }

    // Common method to display details
    public void displayDetails(String roomType, int availability) {
        System.out.println(roomType + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + pricePerNight);
        System.out.println("Available: " + availability);
        System.out.println();
    }
}

// ---------- Single Room ----------
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

// ---------- Double Room ----------
class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

// ---------- Suite Room ----------
class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

// ---------- Application Entry ----------
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization\n");

        // Static availability variables
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        // Polymorphism (Room reference)
        Room single = new SingleRoom();
        Room doubleroom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display details
        single.displayDetails("Single Room", singleAvailability);
        doubleroom.displayDetails("Double Room", doubleAvailability);
        suite.displayDetails("Suite Room", suiteAvailability);
    }
}