//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hotel.service;
import hotel.util.FileUtil;
import hotel.model.Reservation;
import hotel.model.Room;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private static final String FILE_PATH = "data/reservations.txt";
    private final List<Reservation> reservations;
    private final RoomService roomService;

    public ReservationService(RoomService var1) {
        this.roomService = var1;
        this.reservations = this.loadReservations();
    }

    public void addReservation(Reservation var1) {
        this.reservations.add(var1);
        Room var2 = this.roomService.findById(var1.getRoomId());
        if (var2 != null) {
            var2.setAvailable(false);
            this.roomService.saveRooms();
        }

        this.saveReservations();
    }

    public List<Reservation> getAllReservations() {
        return this.reservations;
    }

    public Reservation findById(int var1) {
        for(Reservation var3 : this.reservations) {
            if (var3.getId() == var1) {
                return var3;
            }
        }

        return null;
    }

    public boolean updateReservation(int var1, int var2, int var3, String var4, String var5, String var6) {
        Reservation var7 = this.findById(var1);
        if (var7 == null) {
            return false;
        } else {
            if (var7.getRoomId() != var3) {
                Room var8 = this.roomService.findById(var7.getRoomId());
                if (var8 != null) {
                    var8.setAvailable(true);
                }

                Room var9 = this.roomService.findById(var3);
                if (var9 != null) {
                    var9.setAvailable(false);
                }

                this.roomService.saveRooms();
            }

            var7.setGuestId(var2);
            var7.setRoomId(var3);
            var7.setCheckInDate(var4);
            var7.setCheckOutDate(var5);
            var7.setStatus(var6);
            this.saveReservations();
            return true;
        }
    }

    public boolean deleteReservation(int var1) {
        Reservation var2 = this.findById(var1);
        if (var2 == null) {
            return false;
        } else {
            Room var3 = this.roomService.findById(var2.getRoomId());
            if (var3 != null) {
                var3.setAvailable(true);
                this.roomService.saveRooms();
            }

            this.reservations.remove(var2);
            this.saveReservations();
            return true;
        }
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

        FileUtil.writeLines("data/reservations.txt", outputLines);
        System.out.println("Reservations imported successfully.");
    }

    public int generateNextId() {
        int var1 = 0;

        for(Reservation var3 : this.reservations) {
            if (var3.getId() > var1) {
                var1 = var3.getId();
            }
        }

        return var1 + 1;
    }

    private List<Reservation> loadReservations() {
        ArrayList var1 = new ArrayList();

        for(String var4 : FileUtil.readLines("data/reservations.txt")) {
            if (!var4.trim().isEmpty()) {
                String[] var5 = var4.split("\\|");
                if (var5.length == 6) {
                    try {
                        int var6 = Integer.parseInt(var5[0]);
                        int var7 = Integer.parseInt(var5[1]);
                        int var8 = Integer.parseInt(var5[2]);
                        var1.add(new Reservation(var6, var7, var8, var5[3], var5[4], var5[5]));
                    } catch (Exception var9) {
                        System.out.println("Skipped invalid reservation line: " + var4);
                    }
                }
            }
        }

        return var1;
    }

    private void saveReservations() {
        ArrayList var1 = new ArrayList();

        for(Reservation var3 : this.reservations) {
            int var10001 = var3.getId();
            var1.add(var10001 + "|" + var3.getGuestId() + "|" + var3.getRoomId() + "|" + var3.getCheckInDate() + "|" + var3.getCheckOutDate() + "|" + var3.getStatus());
        }

        FileUtil.writeLines("data/reservations.txt", var1);
    }
}
