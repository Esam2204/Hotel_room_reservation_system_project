package hotel.model;

public class Admin extends Person {
    private String department;

    public Admin(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        if (department != null && !department.trim().isEmpty()) {
            this.department = department.trim();
        } else {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
    }

    @Override
    public String getRoleDescription() {
        return "Admin Staff";
    }
}