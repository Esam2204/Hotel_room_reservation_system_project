package hotel.model;

public class Reservation {
    private int id;
    private int guestId;
    private int roomId;
    private String checkInDate;
    private String checkOutDate;
    private String status;

    public Reservation(int id, int guestId, int roomId, String checkInDate, String checkOutDate, String status) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuestId() {
        return this.guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getRoomId() {
        return this.roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        if (checkInDate != null && !checkInDate.trim().isEmpty()) {
            this.checkInDate = checkInDate.trim();
        } else {
            throw new IllegalArgumentException("Check-in date cannot be empty.");
        }
    }

    public String getCheckOutDate() {
        return this.checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        if (checkOutDate != null && !checkOutDate.trim().isEmpty()) {
            this.checkOutDate = checkOutDate.trim();
        } else {
            throw new IllegalArgumentException("Check-out date cannot be empty.");
        }
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        if (status != null && !status.trim().isEmpty()) {
            this.status = status.trim();
        } else {
            throw new IllegalArgumentException("Status cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Guest ID: %d | Room ID: %d | Check-in: %s | Check-out: %s | Status: %s",
                this.id,
                this.guestId,
                this.roomId,
                this.checkInDate,
                this.checkOutDate,
                this.status
        );
    }
}