package video.meedding.Meedding.exception;

public class NoMatchingRoomException extends RuntimeException{
    public NoMatchingRoomException() {
        super();
    }

    public NoMatchingRoomException(String message) {
        super(message);
    }

    public NoMatchingRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMatchingRoomException(Throwable cause) {
        super(cause);
    }

    protected NoMatchingRoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
