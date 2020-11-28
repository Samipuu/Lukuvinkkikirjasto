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
import readingtips.BlogPost;

/**
 *
 * @author tiitinha
 */
public class BlogPostDao extends Dao {

    public int create(BlogPost blogPost) {

        try {

            String sql = "INSERT INTO BlogPost(title, author, description, url) VALUES(?, ?, ?, ?) ";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, blogPost.getTitle());
            ps.setString(2, blogPost.getAuthor());
            ps.setString(3, blogPost.getDescription());
            ps.setString(4, blogPost.getUrl());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            int id = 0;
            if (keys.next()) {
                id = keys.getInt(1);
            }

            addTags(id, blogPost.getTags());
            addCourses(id, blogPost.getCourses());

            return id; // TODO: mitä palautetaan?

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<BlogPost> list() {

        try {

            String sql = "SELECT BlogPost.id, title, author, description, url, GROUP_CONCAT(tag) as tags, GROUP_CONCAT(course) as courses FROM BlogPost "
                    + "INNER JOIN Tag on BlogPost.id = Tag.tipId "
                    + "INNER JOIN Course on BlogPost.id = Course.tipId "
                    + "GROUP BY BlogPost.id, title, author, description, url";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<BlogPost> returnValue = new ArrayList<BlogPost>();

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

                BlogPost blogPost = new BlogPost(title, author, description, tags, courses, url); // TODO: id, collections
                returnValue.add(blogPost);
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

    public boolean delete(BlogPost blogPost) {
        return false;
    }
    
    public boolean edit(String id, BlogPost blogPost) {
        return false;
    }
}
