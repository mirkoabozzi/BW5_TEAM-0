package BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Element with id: " + id + " NOT FOUND");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
