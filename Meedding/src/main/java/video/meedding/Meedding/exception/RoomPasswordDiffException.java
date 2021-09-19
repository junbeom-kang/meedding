package video.meedding.Meedding.exception;

public class RoomPasswordDiffException extends RuntimeException {
    public RoomPasswordDiffException() {
        super();
    }

    public RoomPasswordDiffException(String message) {
        super(message);
    }
}
