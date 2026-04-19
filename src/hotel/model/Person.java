package hotel.model;

public abstract class Person {
    private int id;
    private String name;
    private String email;

    public Person(int var1, String var2, String var3) {
        this.id = var1;
        this.name = var2;
        this.email = var3;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int var1) {
        this.id = var1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.name = var1.trim();
        } else {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.email = var1.trim();
        } else {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
    }

    public abstract String getRoleDescription();
}
