package video.meedding.Meedding.exception;

public class ExistedRoomNameException extends RuntimeException {
    public ExistedRoomNameException() {
        super();
    }

    public ExistedRoomNameException(String message) {
        super(message);
    }
}
