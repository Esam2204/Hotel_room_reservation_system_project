//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hotel.service;
import hotel.util.FileUtil;

import hotel.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private static final String FILE_PATH = "data/rooms.txt";
    private final List<Room> rooms = this.loadRooms();

    public RoomService() {
    }

    public void addRoom(Room var1) {
        this.rooms.add(var1);
        this.saveRooms();
    }

    public List<Room> getAllRooms() {
        return this.rooms;
    }

    public Room findById(int var1) {
        for(Room var3 : this.rooms) {
            if (var3.getId() == var1) {
                return var3;
            }
        }

        return null;
    }

    public boolean updateRoom(int var1, String var2, String var3, double var4, boolean var6) {
        Room var7 = this.findById(var1);
        if (var7 == null) {
            return false;
        } else {
            var7.setRoomNumber(var2);
            var7.setType(var3);
            var7.setPricePerNight(var4);
            var7.setAvailable(var6);
            this.saveRooms();
            return true;
        }
    }

    public boolean deleteRoom(int var1) {
        Room var2 = this.findById(var1);
        if (var2 == null) {
            return false;
        } else {
            this.rooms.remove(var2);
            this.saveRooms();
            return true;
        }
    }

    public int generateNextId() {
        int var1 = 0;

        for(Room var3 : this.rooms) {
            if (var3.getId() > var1) {
                var1 = var3.getId();
            }
        }

        return var1 + 1;
    }

    private List<Room> loadRooms() {
        ArrayList var1 = new ArrayList();

        for(String var4 : FileUtil.readLines("data/rooms.txt")) {
            if (!var4.trim().isEmpty()) {
                String[] var5 = var4.split("\\|");
                if (var5.length == 5) {
                    try {
                        int var6 = Integer.parseInt(var5[0]);
                        double var7 = Double.parseDouble(var5[3]);
                        boolean var9 = Boolean.parseBoolean(var5[4]);
                        var1.add(new Room(var6, var5[1], var5[2], var7, var9));
                    } catch (Exception var10) {
                        System.out.println("Skipped invalid room line: " + var4);
                    }
                }
            }
        }

        return var1;
    }

    public void saveRooms() {
        ArrayList var1 = new ArrayList();

        for(Room var3 : this.rooms) {
            int var10001 = var3.getId();
            var1.add(var10001 + "|" + var3.getRoomNumber() + "|" + var3.getType() + "|" + var3.getPricePerNight() + "|" + var3.isAvailable());
        }

        FileUtil.writeLines("data/rooms.txt", var1);
    }
}
