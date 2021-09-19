package video.meedding.Meedding.exception;

public class NoFriendRelationException extends RuntimeException {
    public NoFriendRelationException() {
        super();
    }

    public NoFriendRelationException(String message) {
        super(message);
    }

    public NoFriendRelationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFriendRelationException(Throwable cause) {
        super(cause);
    }

    protected NoFriendRelationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
