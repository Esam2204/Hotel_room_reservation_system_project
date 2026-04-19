//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hotel.model;

public class Room {
    private int id;
    private String roomNumber;
    private String type;
    private double pricePerNight;
    private boolean available;

    public Room(int var1, String var2, String var3, double var4, boolean var6) {
        this.id = var1;
        this.roomNumber = var2;
        this.type = var3;
        this.pricePerNight = var4;
        this.available = var6;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int var1) {
        this.id = var1;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.roomNumber = var1.trim();
        } else {
            throw new IllegalArgumentException("Room number cannot be empty.");
        }
    }

    public String getType() {
        return this.type;
    }

    public void setType(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.type = var1.trim();
        } else {
            throw new IllegalArgumentException("Room type cannot be empty.");
        }
    }

    public double getPricePerNight() {
        return this.pricePerNight;
    }

    public void setPricePerNight(double var1) {
        if (var1 < (double)0.0F) {
            throw new IllegalArgumentException("Price cannot be negative.");
        } else {
            this.pricePerNight = var1;
        }
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean var1) {
        this.available = var1;
    }

    public String toString() {
        return String.format("ID: %d | Room: %s | Type: %s | Price: %.2f | Available: %s", this.id, this.roomNumber, this.type, this.pricePerNight, this.available ? "Yes" : "No");
    }
}
