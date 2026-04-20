package hotel.service;

import hotel.model.Room;
import hotel.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private static final String FILE_PATH = "data/rooms.txt";
    private final List<Room> rooms = this.loadRooms();

    public RoomService() {
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
        this.saveRooms();
    }

    public List<Room> getAllRooms() {
        return this.rooms;
    }

    public Room findById(int id) {
        for (Room room : this.rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }

    public boolean updateRoom(int id, String roomNumber, String type, double pricePerNight, boolean available) {
        Room room = this.findById(id);

        if (room == null) {
            return false;
        }

        room.setRoomNumber(roomNumber);
        room.setType(type);
        room.setPricePerNight(pricePerNight);
        room.setAvailable(available);

        this.saveRooms();
        return true;
    }

    public boolean deleteRoom(int id) {
        Room room = this.findById(id);

        if (room == null) {
            return false;
        }

        this.rooms.remove(room);
        this.saveRooms();
        return true;
    }

    public void exportToCsv(String path) {
        List<String> lines = new ArrayList<>();
        lines.add("id,roomNumber,type,price,available");

        for (Room room : getAllRooms()) {
            lines.add(
                    room.getId() + "," +
                            room.getRoomNumber() + "," +
                            room.getType() + "," +
                            room.getPricePerNight() + "," +
                            room.isAvailable()
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

        List<Room> importedRooms = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");

            if (parts.length < 5) {
                continue;
            }

            try {
                int id = Integer.parseInt(parts[0].trim());
                String roomNumber = parts[1].trim();
                String type = parts[2].trim();
                double pricePerNight = Double.parseDouble(parts[3].trim());
                boolean available = Boolean.parseBoolean(parts[4].trim());

                importedRooms.add(new Room(id, roomNumber, type, pricePerNight, available));
            } catch (Exception e) {
                System.out.println("Skipping invalid room row: " + line);
            }
        }

        List<String> outputLines = new ArrayList<>();
        for (Room room : importedRooms) {
            outputLines.add(
                    room.getId() + "," +
                            room.getRoomNumber() + "," +
                            room.getType() + "," +
                            room.getPricePerNight() + "," +
                            room.isAvailable()
            );
        }

        FileUtil.writeLines(FILE_PATH, outputLines);
        System.out.println("Rooms imported successfully.");
    }

    public int generateNextId() {
        int maxId = 0;

        for (Room room : this.rooms) {
            if (room.getId() > maxId) {
                maxId = room.getId();
            }
        }

        return maxId + 1;
    }

    private List<Room> loadRooms() {
        List<Room> loadedRooms = new ArrayList<>();

        for (String line : FileUtil.readLines(FILE_PATH)) {
            if (!line.trim().isEmpty()) {
                String[] parts = line.split("\\|");

                if (parts.length == 5) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String roomNumber = parts[1];
                        String type = parts[2];
                        double pricePerNight = Double.parseDouble(parts[3]);
                        boolean available = Boolean.parseBoolean(parts[4]);

                        loadedRooms.add(
                                new Room(id, roomNumber, type, pricePerNight, available)
                        );
                    } catch (Exception e) {
                        System.out.println("Skipped invalid room line: " + line);
                    }
                }
            }
        }

        return loadedRooms;
    }

    public void saveRooms() {
        List<String> lines = new ArrayList<>();

        for (Room room : this.rooms) {
            lines.add(
                    room.getId() + "|" +
                            room.getRoomNumber() + "|" +
                            room.getType() + "|" +
                            room.getPricePerNight() + "|" +
                            room.isAvailable()
            );
        }

        FileUtil.writeLines(FILE_PATH, lines);
    }
}