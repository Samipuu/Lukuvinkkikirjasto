package readingtips.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
                ps.setInt(key, 1);
                ps.executeUpdate();
            }
            // e.g. BookCourse-table
            {
                PreparedStatement ps = conn
                        .prepareStatement("DELETE FROM " + table + "Course where " + table + "_id = ?");
                ps.setInt(key, 1);
                ps.executeUpdate();
            }
            // e.g. Book-table
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + table + " where id = ?");
            ps.setInt(key, 1);
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

            tip.updateCommon(created, modified, title, author, description, tags, courses);
        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public void addTags(Tip tip) {

        List<String> tags = tip.getTags();

        String tagSql = "INSERT INTO Tag(created, modified, teksti) "
                + "VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";

        for (String tag : tags) {
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(tagSql);
                ps.setString(1, tag);
                ps.executeUpdate();
            } catch (SQLException e) {
                // this will throw exception when same tag alread exists.
                // TODO: should check this and not throw exception
                System.out.println("!! INSERTING TAG " + tag + " FAILED.");
            }
        }

        addManyToManyTags(tags, tip.getId());
    }

    public void addCourses(Tip tip) {

        List<String> courses = tip.getCourses();

        String courseSql = "INSERT INTO Course(created, modified, nimi) "
                + "VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";

        for (String course : courses) {

            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(courseSql);
                ps.setString(1, course);
                ps.executeUpdate();
            } catch (SQLException e) {
                // this will throw exception when same tag alread exists.
                // TODO: should check this and not throw exception
                System.out.println("!! INSERTING COURSE " + course + " FAILED.");
            }
        }

        addManyToManyCourses(courses, tip.getId());
    }

}
