package video.meedding.Meedding.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
