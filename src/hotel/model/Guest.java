//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hotel.model;

public class Guest extends Person {
    private String phoneNumber;

    public Guest(int var1, String var2, String var3, String var4) {
        super(var1, var2, var3);
        this.phoneNumber = var4;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.phoneNumber = var1.trim();
        } else {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }
    }

    public String getRoleDescription() {
        return "Guest";
    }

    public String toString() {
        return String.format("ID: %d | %s | %s | %s | Role: %s", this.getId(), this.getName(), this.getEmail(), this.phoneNumber, this.getRoleDescription());
    }
}
