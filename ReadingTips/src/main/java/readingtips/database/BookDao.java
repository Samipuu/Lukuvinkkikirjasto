package readingtips.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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

            //TODO: tags, courses
            for (String tag : book.getTags()) {
                String tagSql = "INSERT INTO Tag(tag, tipId) VALUES(?, ?)";
                ps = conn.prepareStatement(tagSql);
                ps.setString(1, tag);
                ps.setInt(2, id);
                ps.executeUpdate();
            }

            for (String course : book.getCourses()) {
                String courseSql = "INSERT INTO Course(course, tipId) VALUES(?, ?)";
                ps = conn.prepareStatement(courseSql);
                ps.setString(1, course);
                ps.setInt(2, id);
                ps.executeUpdate();
            }

            return id; // TODO: mitä palautetaan?

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<Book> list() {

        try {

            String sql = "SELECT title, author, description, isbn, GROUP_CONCAT(tag) as tags, GROUP_CONCAT(course) as courses FROM Book "
                    + "INNER JOIN Tag on Book.id = Tag.tipId "
                    + "INNER JOIN Course on Book.id = Course.tipId "
                    + "GROUP BY title, author, description, isbn";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<Book> returnValue = new ArrayList<Book>();

            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String description = rs.getString("description");
                String isbn = rs.getString("isbn");
                
                List<String> tags;
                List<String> courses;
                
                if (rs.getString("tags") != null && rs.getString("courses") != null) {
                    tags = Arrays.asList(rs.getString("tags").split(","));
                    courses = Arrays.asList(rs.getString("courses").split(","));
                } else {
                    tags = null;
                    courses = null;
                }
                
                Book book = new Book(title, author, description, tags, courses, isbn); // TODO: id, collections
                returnValue.add(book);
            }

            return returnValue;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
