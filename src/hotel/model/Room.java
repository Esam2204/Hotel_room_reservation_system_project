package hotel.model;

public class Room {
    private int id;
    private String roomNumber;
    private String type;
    private double pricePerNight;
    private boolean available;

    public Room(int id, String roomNumber, String type, double pricePerNight, boolean available) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.available = available;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        if (roomNumber != null && !roomNumber.trim().isEmpty()) {
            this.roomNumber = roomNumber.trim();
        } else {
            throw new IllegalArgumentException("Room number cannot be empty.");
        }
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        if (type != null && !type.trim().isEmpty()) {
            this.type = type.trim();
        } else {
            throw new IllegalArgumentException("Room type cannot be empty.");
        }
    }

    public double getPricePerNight() {
        return this.pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        } else {
            this.pricePerNight = pricePerNight;
        }
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Room: %s | Type: %s | Price: %.2f | Available: %s",
                this.id,
                this.roomNumber,
                this.type,
                this.pricePerNight,
                this.available ? "Yes" : "No"
        );
    }
}