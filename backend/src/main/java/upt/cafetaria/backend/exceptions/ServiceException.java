package upt.cafetaria.backend.exceptions;

/**
 * Exception thrown when a service operation fails.
 * Contains the action that failed and the reason for the failure.
 * Used to provide a more detailed error message to the user.
 * @see ServiceException
 * @author Rainier Bastiaans
 */
public class ServiceException extends RuntimeException {

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ServiceException(String action, String message) {
        super(message);
        this.action = action;
    }
}
