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
import readingtips.Video;

/**
 *
 * @author tiitinha
 */
public class VideoDao extends Dao {

    public int create(Video video) {

        try {

            String sql = "INSERT INTO Video(title, author, description, url) VALUES(?, ?, ?, ?) ";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, video.getTitle());
            ps.setString(2, video.getAuthor());
            ps.setString(3, video.getDescription());
            ps.setString(4, video.getUrl());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            int id = 0;
            if (keys.next()) {
                id = keys.getInt(1);
            }

            addTags(id, video.getTags());
            addCourses(id, video.getCourses());

            return id; // TODO: mitä palautetaan?

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<Video> list() {

        try {

            String sql = "SELECT Video.id, title, author, description, url, GROUP_CONCAT(tag) as tags, GROUP_CONCAT(course) as courses FROM Video "
                    + "INNER JOIN Tag on Video.id = Tag.tipId "
                    + "INNER JOIN Course on Video.id = Course.tipId "
                    + "GROUP BY Video.id, title, author, description, url";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<Video> returnValue = new ArrayList<Video>();

            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString("title");
                String author = rs.getString("author");
                String description = rs.getString("description");
                String url = rs.getString("url");

                List<String> tags;
                List<String> courses;

                if (rs.getString("tags") != null && rs.getString("courses") != null) {
                    tags = Arrays.asList(rs.getString("tags").split(","));
                    courses = Arrays.asList(rs.getString("courses").split(","));
                } else {
                    tags = null;
                    courses = null;
                }

                Video video = new Video(title, author, description, tags, courses, url); // TODO: id, collections
                returnValue.add(video);
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

    public boolean delete(Video video) {
        return false;
    }

    public boolean edit(String id, Video video) {
        return false;
    }

}
