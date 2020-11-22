package readingtips.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import readingtips.Book;

public class BookDao extends Dao {

    // TODO: read, update, delete, list, muuta?

    public int create(Book book) {

        try {

            String sql = "INSERT INTO Book(title, author, description, isbn) VALUES(?, ?, ?, ?) ";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getDescription());
            ps.setString(4, book.getIsbn());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            int id = 0;
            if (keys.next()) {
                id = keys.getInt(1);
            }

            // TODO: tags, courses

            return id; // TODO: mitä palautetaan?

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<Book> list() {

        try {

            String sql = "SELECT title, author, description, isbn FROM Book"; // TODO: id, collections

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<Book> returnValue = new ArrayList<Book>();

            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String description = rs.getString("description");
                String isbn = rs.getString("isbn");
                Book book = new Book(title, description, author, null, null, isbn); // TODO: id, collections
                returnValue.add(book);
            }

            return returnValue;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
