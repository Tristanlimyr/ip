package wout.ui;

/**
 * Represents an exception that occurs in Wout.
 */
public class WoutException extends Exception {
    protected String description;

    public WoutException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
