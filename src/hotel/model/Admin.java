package hotel.model;

public class Admin extends Person {
    private String department;

    public Admin(int var1, String var2, String var3, String var4) {
        super(var1, var2, var3);
        this.department = var4;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.department = var1.trim();
        } else {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
    }

    public String getRoleDescription() {
        return "Admin Staff";
    }
}
