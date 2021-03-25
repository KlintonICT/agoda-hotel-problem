public class Room {
    private int roomID;
    private String name;

    public Room(int roomID, String name) {
        this.roomID = roomID;
        this.name = name;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getName() {
        return name;
    }
}
