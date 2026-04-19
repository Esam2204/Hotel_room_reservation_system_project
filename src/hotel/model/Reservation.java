package hotel.model;

public class Reservation {
    private int id;
    private int guestId;
    private int roomId;
    private String checkInDate;
    private String checkOutDate;
    private String status;

    public Reservation(int var1, int var2, int var3, String var4, String var5, String var6) {
        this.id = var1;
        this.guestId = var2;
        this.roomId = var3;
        this.checkInDate = var4;
        this.checkOutDate = var5;
        this.status = var6;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int var1) {
        this.id = var1;
    }

    public int getGuestId() {
        return this.guestId;
    }

    public void setGuestId(int var1) {
        this.guestId = var1;
    }

    public int getRoomId() {
        return this.roomId;
    }

    public void setRoomId(int var1) {
        this.roomId = var1;
    }

    public String getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.checkInDate = var1.trim();
        } else {
            throw new IllegalArgumentException("Check-in date cannot be empty.");
        }
    }

    public String getCheckOutDate() {
        return this.checkOutDate;
    }

    public void setCheckOutDate(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.checkOutDate = var1.trim();
        } else {
            throw new IllegalArgumentException("Check-out date cannot be empty.");
        }
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.status = var1.trim();
        } else {
            throw new IllegalArgumentException("Status cannot be empty.");
        }
    }

    public String toString() {
        return String.format("ID: %d | Guest ID: %d | Room ID: %d | Check-in: %s | Check-out: %s | Status: %s", this.id, this.guestId, this.roomId, this.checkInDate, this.checkOutDate, this.status);
    }
}
