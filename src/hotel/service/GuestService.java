package hotel.service;

import hotel.model.Guest;
import hotel.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class GuestService {
    private static final String FILE_PATH = "data/guests.txt";
    private final List<Guest> guests = this.loadGuests();

    public GuestService() {
    }

    public void addGuest(Guest guest) {
        this.guests.add(guest);
        this.saveGuests();
    }

    public void exportToCsv(String path) {
        List<String> lines = new ArrayList<>();
        lines.add("id,name,email,phone");

        for (Guest guest : getAllGuests()) {
            lines.add( // превращает объекты в строки
                    guest.getId() + "," +
                            guest.getName() + "," +
                            guest.getEmail() + "," +
                            guest.getPhoneNumber()
            );
        }

        FileUtil.writeLines(path, lines); //записывается в файл
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
                String phoneNumber = parts[3].trim();

                importedGuests.add(new Guest(id, name, email, phoneNumber));
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

        FileUtil.writeLines(FILE_PATH, outputLines);
        System.out.println("Guests imported successfully.");
    }

    public List<Guest> getAllGuests() {
        return this.guests;
    }

    public Guest findById(int id) {
        for (Guest guest : this.guests) {
            if (guest.getId() == id) {
                return guest;
            }
        }

        return null;
    }

    public boolean updateGuest(int id, String name, String email, String phoneNumber) {
        Guest guest = this.findById(id);

        if (guest == null) {
            return false;
        } else {
            guest.setName(name);
            guest.setEmail(email);
            guest.setPhoneNumber(phoneNumber);
            this.saveGuests();
            return true;
        }
    }

    public boolean deleteGuest(int id) {
        Guest guest = this.findById(id);

        if (guest == null) {
            return false;
        } else {
            this.guests.remove(guest);
            this.saveGuests();
            return true;
        }
    }

    public int generateNextId() {
        int maxId = 0;

        for (Guest guest : this.guests) {
            if (guest.getId() > maxId) {
                maxId = guest.getId();
            }
        }

        return maxId + 1;
    }

    private List<Guest> loadGuests() {
        List<Guest> loadedGuests = new ArrayList<>();

        for (String line : FileUtil.readLines(FILE_PATH)) {
            if (!line.trim().isEmpty()) {
                String[] parts = line.split("\\|");

                if (parts.length == 4) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        String email = parts[2];
                        String phoneNumber = parts[3];

                        loadedGuests.add(new Guest(id, name, email, phoneNumber));
                    } catch (Exception e) {
                        System.out.println("Skipped invalid guest line: " + line);
                    }
                }
            }
        }

        return loadedGuests;
    }

    private void saveGuests() {
        List<String> lines = new ArrayList<>();

        for (Guest guest : this.guests) {
            lines.add(
                    guest.getId() + "|" +
                            guest.getName() + "|" +
                            guest.getEmail() + "|" +
                            guest.getPhoneNumber()
            );
        }

        FileUtil.writeLines(FILE_PATH, lines);
    }
}