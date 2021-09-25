package video.meedding.Meedding.exception;

public class RoomPasswordWrongException extends RuntimeException {
    public RoomPasswordWrongException() {
        super();
    }

    public RoomPasswordWrongException(String message) {
        super(message);
    }
}
