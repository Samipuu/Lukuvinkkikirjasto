package readingtips;
import java.util.List;
import java.util.Objects;

public class Book extends Tip {

    private String isbn;

    public Book(String title, String author, String description, List<String> tags, List<String> courses, String isbn) {
        super("Book", title, author, description, tags, courses);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return this.isbn;
    }

    @Override
    public String toString() {
        String palautus = super.toString();
        if (this.getIsbn() != null & !this.getIsbn().isEmpty()) {
            palautus += "\nISBN: " + this.isbn;
        }
        return palautus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        return !(!Objects.equals(this.isbn, other.isbn) || !Objects.equals(this.getAuthor(), other.getAuthor())
                || !Objects.equals(this.getTitle(), other.getTitle()) || !Objects.equals(this.getDescription(), other.getDescription()));
    }

}
