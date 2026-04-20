package hotel.model;

public abstract class Person {
    private int id;
    private String name;
    private String email;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        } else {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (email != null && !email.trim().isEmpty()) {
            this.email = email.trim();
        } else {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
    }

    public abstract String getRoleDescription();
}