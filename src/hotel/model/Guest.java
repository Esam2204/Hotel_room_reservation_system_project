package hotel.model;

public class Guest extends Person {
    private String phoneNumber;

    public Guest(int id, String name, String email, String phoneNumber) {
        super(id, name, email);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            this.phoneNumber = phoneNumber.trim();
        } else {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }
    }

    @Override
    public String getRoleDescription() {
        return "Guest";
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | %s | %s | %s | Role: %s",
                this.getId(),
                this.getName(),
                this.getEmail(),
                this.phoneNumber,
                this.getRoleDescription()
        );
    }
}