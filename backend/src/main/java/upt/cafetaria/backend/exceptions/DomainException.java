package upt.cafetaria.backend.exceptions;

public class DomainException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String action;

    public DomainException() {
        super();
    }

    public DomainException(String message, Throwable exception) {
        super(message, exception);
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(Throwable exception) {
        super(exception);
    }

    public DomainException(String action, String message) {
        super(message);
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}