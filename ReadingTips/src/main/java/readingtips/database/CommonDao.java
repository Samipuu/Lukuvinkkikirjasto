package readingtips.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import readingtips.Book;

import readingtips.Tip;

public abstract class CommonDao extends Dao {

    protected String table;
    
    public int getId(PreparedStatement ps) {
        try {
            ResultSet keys = ps.getGeneratedKeys();
            int id = 0;
            if (keys.next()) {
                id = keys.getInt(1);
            }
            return id;
        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public void delete(Tip tip) {
        delete(tip.getId());
    }

    public void delete(int key) {
        try {
            // e.g. BookTag-table
            {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM " + table + "Tag where " + table + "_id = ?");
                ps.setInt(1, key);
                ps.executeUpdate();
            }
            // e.g. BookCourse-table
            {
                PreparedStatement ps = conn
                        .prepareStatement("DELETE FROM " + table + "Course where " + table + "_id = ?");
                ps.setInt(1, key);
                ps.executeUpdate();
            }
            // e.g. Book-table
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + table + " where id = ?");
            ps.setInt(1, key);
            ps.executeUpdate();
        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public void addManyToManyTags(List<String> tagTekstit, int id) {
        try {
            for (String tag : tagTekstit) {

                String tagSql = "INSERT INTO " + table + "Tag(" + table
                        + "_id, tag_id) VALUES(?, (SELECT id FROM Tag WHERE teksti = ?))";

                PreparedStatement ps = conn.prepareStatement(tagSql);
                ps.setInt(1, id);
                ps.setString(2, tag);
                ps.executeUpdate();
            }
        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public void addManyToManyCourses(List<String> courseNimet, int id) {
        try {
            for (String tag : courseNimet) {

                String courseSql = "INSERT INTO " + table + "Course(" + table
                        + "_id, course_id) VALUES(?,(SELECT id FROM Course WHERE nimi = ?))";

                PreparedStatement ps = conn.prepareStatement(courseSql);
                ps.setInt(1, id);
                ps.setString(2, tag);
                ps.executeUpdate();
            }
        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public List<String> getCourses(int id) {

        List<String> returnValues = new ArrayList<String>();

        try {

            String sql = "SELECT nimi FROM Course where id IN ( SELECT course_id from " + table + "Course where "
                    + table + "_id = " + id + ")";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                returnValues.add(rs.getString("nimi"));
            }
            return returnValues;

        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public List<String> getTags(int id) {

        try {

            List<String> returnValues = new ArrayList<String>();

            String sql = "SELECT teksti FROM Tag where id IN ( SELECT tag_id from " + table + "Tag where " + table
                    + "_id = " + id + ")";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                returnValues.add(rs.getString("teksti"));
            }
            return returnValues;

        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public void getCommonFields(ResultSet rs, Tip tip) {
        try {
            // Entity
            Integer id = rs.getInt("id");
            LocalDateTime created = rs.getTimestamp("created").toLocalDateTime();
            LocalDateTime modified = rs.getTimestamp("modified").toLocalDateTime();
            // Tip
            String title = rs.getString("title");
            String author = rs.getString("author");
            String description = rs.getString("description");
            List<String> tags = getTags(id);
            List<String> courses = getCourses(id);

            tip.updateCommon(id, created, modified, title, author, description, tags, courses);

        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public void addTags(Tip tip) {

        try {

            List<String> tags = tip.getTags();

            String existsSql = "SELECT teksti FROM Tag WHERE teksti = ?";

            String tagSql = "INSERT INTO Tag(created, modified, teksti) "
                    + "VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";

            for (String tag : tags) {
                PreparedStatement ps;
                ps = conn.prepareStatement(existsSql);
                ps.setString(1, tag);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    ps = conn.prepareStatement(tagSql);
                    ps.setString(1, tag);
                    ps.executeUpdate();
                }
            }

            addManyToManyTags(tags, tip.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCourses(Tip tip) {

        try {

            List<String> courses = tip.getCourses();

            String existsSql = "SELECT nimi FROM Course WHERE nimi = ?";

            String courseSql = "INSERT INTO Course(created, modified, nimi) "
                    + "VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";

            for (String course : courses) {
                PreparedStatement ps;
                ps = conn.prepareStatement(existsSql);
                ps.setString(1, course);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    ps = conn.prepareStatement(courseSql);
                    ps.setString(1, course);
                    ps.executeUpdate();
                }
            }

            addManyToManyCourses(courses, tip.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void update(Tip tip, String uniqueField, String uniqueFieldValue) {
        System.out.println(tip);
        System.out.println(table);
        try {

            String sql = "UPDATE " + table + " SET modified = CURRENT_TIMESTAMP, "
                    + "title = ?, author = ?, description = ?, " + uniqueField + " = ? WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tip.getTitle());
            ps.setString(2, tip.getAuthor());
            ps.setString(3, tip.getDescription());
            ps.setString(4, uniqueFieldValue);
            ps.setString(5, String.valueOf(tip.getId()));
            ps.executeUpdate();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
