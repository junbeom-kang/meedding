package video.meedding.Meedding.exception;

public class NoRoomSessionException extends RuntimeException {
    public NoRoomSessionException() {
        super();
    }

    public NoRoomSessionException(String message) {
        super(message);
    }
}
