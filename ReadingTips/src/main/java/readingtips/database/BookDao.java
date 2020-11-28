package readingtips.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import readingtips.Book;

public class BookDao extends CommonDao {

    public BookDao() {
        this.table = "Book";
    }

    public void create(Book book) {

        try {
            String sql = "INSERT INTO BOOK(created, modified, title, author, description, isbn) "
                    + "VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?, ?) ";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getDescription());
            ps.setString(4, book.getIsbn());
            ps.executeUpdate();
            book.setId(getId(ps));
            addTags(book);
            addCourses(book);
        
            reload(book);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void reload(Book book) {
        try {

            String sql = "SELECT id, created, modified, title, author, description, isbn FROM BOOK WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, book.getId());

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                getCommonFields(rs, book);
                String isbn = rs.getString("isbn");
                book.setIsbn(isbn);
            }

        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public List<Book> list() {

        List<Book> returnValue = new ArrayList<Book>();

        try {

            String sql = "SELECT id, created, modified, title, author, description, isbn FROM Book";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Book book = new Book();
                getCommonFields(rs, book);
                String isbn = rs.getString("isbn");
                book.setIsbn(isbn);
                returnValue.add(book);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return returnValue;
    }





}
