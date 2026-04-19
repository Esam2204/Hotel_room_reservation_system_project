package hotel.service;
import hotel.util.FileUtil;

import hotel.model.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestService {
    private static final String FILE_PATH = "data/guests.txt";
    private final List<Guest> guests = this.loadGuests();

    public GuestService() {
    }

    public void addGuest(Guest var1) {
        this.guests.add(var1);
        this.saveGuests();
    }

    public void exportToCsv(String path) {
        List<String> lines = new ArrayList<>();
        lines.add("id,name,email,phone");

        for (Guest guest : getAllGuests()) {
            lines.add(
                    guest.getId() + "," +
                            guest.getName() + "," +
                            guest.getEmail() + "," +
                            guest.getPhoneNumber()
            );
        }

        FileUtil.writeLines(path, lines);
    }

    public void importFromCsv(String path) {
        List<String> lines = FileUtil.readLines(path);

        if (lines.isEmpty()) {
            System.out.println("CSV file is empty or not found.");
            return;
        }

        List<Guest> importedGuests = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");

            if (parts.length < 4) {
                continue;
            }

            try {
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String email = parts[2].trim();
                String phone = parts[3].trim();

                importedGuests.add(new Guest(id, name, email, phone));
            } catch (Exception e) {
                System.out.println("Skipping invalid guest row: " + line);
            }
        }

        List<String> outputLines = new ArrayList<>();
        for (Guest guest : importedGuests) {
            outputLines.add(
                    guest.getId() + "," +
                            guest.getName() + "," +
                            guest.getEmail() + "," +
                            guest.getPhoneNumber()
            );
        }

        FileUtil.writeLines("data/guests.txt", outputLines);
        System.out.println("Guests imported successfully.");
    }


    public List<Guest> getAllGuests() {
        return this.guests;
    }

    public Guest findById(int var1) {
        for(Guest var3 : this.guests) {
            if (var3.getId() == var1) {
                return var3;
            }
        }

        return null;
    }

    public boolean updateGuest(int var1, String var2, String var3, String var4) {
        Guest var5 = this.findById(var1);
        if (var5 == null) {
            return false;
        } else {
            var5.setName(var2);
            var5.setEmail(var3);
            var5.setPhoneNumber(var4);
            this.saveGuests();
            return true;
        }
    }

    public boolean deleteGuest(int var1) {
        Guest var2 = this.findById(var1);
        if (var2 == null) {
            return false;
        } else {
            this.guests.remove(var2);
            this.saveGuests();
            return true;
        }
    }

    public int generateNextId() {
        int var1 = 0;

        for(Guest var3 : this.guests) {
            if (var3.getId() > var1) {
                var1 = var3.getId();
            }
        }

        return var1 + 1;
    }

    private List<Guest> loadGuests() {
        ArrayList var1 = new ArrayList();

        for(String var4 : FileUtil.readLines("data/guests.txt")) {
            if (!var4.trim().isEmpty()) {
                String[] var5 = var4.split("\\|");
                if (var5.length == 4) {
                    try {
                        int var6 = Integer.parseInt(var5[0]);
                        var1.add(new Guest(var6, var5[1], var5[2], var5[3]));
                    } catch (Exception var7) {
                        System.out.println("Skipped invalid guest line: " + var4);
                    }
                }
            }
        }

        return var1;
    }

    private void saveGuests() {
        ArrayList var1 = new ArrayList();

        for(Guest var3 : this.guests) {
            int var10001 = var3.getId();
            var1.add(var10001 + "|" + var3.getName() + "|" + var3.getEmail() + "|" + var3.getPhoneNumber());
        }

        FileUtil.writeLines("data/guests.txt", var1);
    }
}
