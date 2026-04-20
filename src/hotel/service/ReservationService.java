package hotel.service;

import hotel.model.Reservation;
import hotel.model.Room;
import hotel.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private static final String FILE_PATH = "data/reservations.txt";
    private final List<Reservation> reservations;
    private final RoomService roomService;

    public ReservationService(RoomService roomService) {
        this.roomService = roomService;
        this.reservations = this.loadReservations();
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);

        Room room = this.roomService.findById(reservation.getRoomId());
        if (room != null) {
            room.setAvailable(false);
            this.roomService.saveRooms();
        }

        this.saveReservations();
    }

    public List<Reservation> getAllReservations() {
        return this.reservations;
    }

    public Reservation findById(int id) {
        for (Reservation reservation : this.reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    public boolean updateReservation(int id, int guestId, int roomId,
                                     String checkInDate, String checkOutDate, String status) {

        Reservation reservation = this.findById(id);

        if (reservation == null) {
            return false;
        }

        // если сменили комнату
        if (reservation.getRoomId() != roomId) {
            Room oldRoom = this.roomService.findById(reservation.getRoomId());
            if (oldRoom != null) {
                oldRoom.setAvailable(true);
            }

            Room newRoom = this.roomService.findById(roomId);
            if (newRoom != null) {
                newRoom.setAvailable(false);
            }

            this.roomService.saveRooms();
        }

        reservation.setGuestId(guestId);
        reservation.setRoomId(roomId);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);
        reservation.setStatus(status);

        this.saveReservations();
        return true;
    }

    public boolean deleteReservation(int id) {
        Reservation reservation = this.findById(id);

        if (reservation == null) {
            return false;
        }

        Room room = this.roomService.findById(reservation.getRoomId());
        if (room != null) {
            room.setAvailable(true);
            this.roomService.saveRooms();
        }

        this.reservations.remove(reservation);
        this.saveReservations();

        return true;
    }

    public void exportToCsv(String path) {
        List<String> lines = new ArrayList<>();
        lines.add("id,guestId,roomId,checkInDate,checkOutDate,status");

        for (Reservation reservation : getAllReservations()) {
            lines.add(
                    reservation.getId() + "," +
                            reservation.getGuestId() + "," +
                            reservation.getRoomId() + "," +
                            reservation.getCheckInDate() + "," +
                            reservation.getCheckOutDate() + "," +
                            reservation.getStatus()
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

        List<Reservation> importedReservations = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");

            if (parts.length < 6) {
                continue;
            }

            try {
                int id = Integer.parseInt(parts[0].trim());
                int guestId = Integer.parseInt(parts[1].trim());
                int roomId = Integer.parseInt(parts[2].trim());
                String checkInDate = parts[3].trim();
                String checkOutDate = parts[4].trim();
                String status = parts[5].trim();

                importedReservations.add(
                        new Reservation(id, guestId, roomId, checkInDate, checkOutDate, status)
                );
            } catch (Exception e) {
                System.out.println("Skipping invalid reservation row: " + line);
            }
        }

        List<String> outputLines = new ArrayList<>();
        for (Reservation reservation : importedReservations) {
            outputLines.add(
                    reservation.getId() + "," +
                            reservation.getGuestId() + "," +
                            reservation.getRoomId() + "," +
                            reservation.getCheckInDate() + "," +
                            reservation.getCheckOutDate() + "," +
                            reservation.getStatus()
            );
        }

        FileUtil.writeLines(FILE_PATH, outputLines);
        System.out.println("Reservations imported successfully.");
    }

    public int generateNextId() {
        int maxId = 0;

        for (Reservation reservation : this.reservations) {
            if (reservation.getId() > maxId) {
                maxId = reservation.getId();
            }
        }

        return maxId + 1;
    }

    private List<Reservation> loadReservations() {
        List<Reservation> loadedReservations = new ArrayList<>();

        for (String line : FileUtil.readLines(FILE_PATH)) {
            if (!line.trim().isEmpty()) {
                String[] parts = line.split("\\|");

                if (parts.length == 6) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        int guestId = Integer.parseInt(parts[1]);
                        int roomId = Integer.parseInt(parts[2]);
                        String checkInDate = parts[3];
                        String checkOutDate = parts[4];
                        String status = parts[5];

                        loadedReservations.add(
                                new Reservation(id, guestId, roomId, checkInDate, checkOutDate, status)
                        );
                    } catch (Exception e) {
                        System.out.println("Skipped invalid reservation line: " + line);
                    }
                }
            }
        }

        return loadedReservations;
    }

    private void saveReservations() {
        List<String> lines = new ArrayList<>();

        for (Reservation reservation : this.reservations) {
            lines.add(
                    reservation.getId() + "|" +
                            reservation.getGuestId() + "|" +
                            reservation.getRoomId() + "|" +
                            reservation.getCheckInDate() + "|" +
                            reservation.getCheckOutDate() + "|" +
                            reservation.getStatus()
            );
        }

        FileUtil.writeLines(FILE_PATH, lines);
    }
}