package video.meedding.Meedding.dto;

public class KakaoCommunicationFailureException extends RuntimeException{
    public KakaoCommunicationFailureException() {
        super();
    }

    public KakaoCommunicationFailureException(String message) {
        super(message);
    }

    public KakaoCommunicationFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public KakaoCommunicationFailureException(Throwable cause) {
        super(cause);
    }

    protected KakaoCommunicationFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
