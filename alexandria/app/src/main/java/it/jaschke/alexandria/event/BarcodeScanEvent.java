package it.jaschke.alexandria.event;

/**
 * Created by ivan on 2/8/16.
 */
public class BarcodeScanEvent {
    private String isbn;

    public BarcodeScanEvent(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public BarcodeScanEvent isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }
}
