package video.meedding.Meedding.exception;

public class NoSuchRoomException extends RuntimeException {
    public NoSuchRoomException() {
        super();
    }

    public NoSuchRoomException(String message) {
        super(message);
    }
}
