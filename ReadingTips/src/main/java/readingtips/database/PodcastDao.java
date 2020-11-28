/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import readingtips.Podcast;

/**
 *
 * @author tiitinha
 */
public class PodcastDao extends Dao {

    public int create(Podcast podcast) {

        try {

            String sql = "INSERT INTO Podcast(title, author, description, podcastName) VALUES(?, ?, ?, ?) ";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, podcast.getTitle());
            ps.setString(2, podcast.getAuthor());
            ps.setString(3, podcast.getDescription());
            ps.setString(4, podcast.getPodcastName());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            int id = 0;
            if (keys.next()) {
                id = keys.getInt(1);
            }

            addTags(id, podcast.getTags());
            addCourses(id, podcast.getCourses());

            return id; // TODO: mitä palautetaan?

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<Podcast> list() {

        try {

            String sql = "SELECT Podcast.id, title, author, description, podcastName, GROUP_CONCAT(tag) as tags, GROUP_CONCAT(course) as courses FROM Podcast "
                    + "INNER JOIN Tag on Podcast.id = Tag.tipId "
                    + "INNER JOIN Course on Podcast.id = Course.tipId "
                    + "GROUP BY Podcast.id, title, author, description, podcastName";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<Podcast> returnValue = new ArrayList<Podcast>();

            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString("title");
                String author = rs.getString("author");
                String description = rs.getString("description");
                String podcastName = rs.getString("podcastName");

                List<String> tags;
                List<String> courses;

                if (rs.getString("tags") != null && rs.getString("courses") != null) {
                    tags = Arrays.asList(rs.getString("tags").split(","));
                    courses = Arrays.asList(rs.getString("courses").split(","));
                } else {
                    tags = null;
                    courses = null;
                }

                Podcast podcast = new Podcast(title, author, description, tags, courses, podcastName); // TODO: id, collections
                returnValue.add(podcast);
            }

            return returnValue;

        } catch (Exception ex) {
            return null;
        }
    }

    private void addTags(int id, List<String> tags) {
        DatabaseService.addTags(conn, id, tags);
    }

    private void addCourses(int id, List<String> courses) {
        DatabaseService.addCourses(conn, id, courses);
    }

    public boolean delete(Podcast podcast) {
        return false;
    }

    public boolean edit(String id, Podcast podcast) {
        return false;
    }
}
