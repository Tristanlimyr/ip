package wout.ui;

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
