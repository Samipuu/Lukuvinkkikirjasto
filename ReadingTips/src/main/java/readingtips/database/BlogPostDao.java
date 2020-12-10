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
import java.util.List;
import readingtips.entity.BlogPost;
import readingtips.entity.Book;

/**
 *
 * @author tiitinha
 */
public class BlogPostDao extends CommonDao {

    public BlogPostDao() {
        this.table = "BlogPost";
    }

    public void create(BlogPost blogpost) {

        try {
            String sql = "INSERT INTO BLOGPOST(created, modified, title, author, description, url) "
                    + "VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?, ?) ";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, blogpost.getTitle());
            ps.setString(2, blogpost.getAuthor());
            ps.setString(3, blogpost.getDescription());
            ps.setString(4, blogpost.getUrl());
            ps.executeUpdate();
            blogpost.setId(getId(ps));
            addTags(blogpost);
            addCourses(blogpost);

            reload(blogpost);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void reload(BlogPost blogpost) {
        try {

            String sql = "SELECT id, created, modified, title, author, description, url FROM BlogPost WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, blogpost.getId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                getCommonFields(rs, blogpost);
                String url = rs.getString("url");
                blogpost.setUrl(url);
            }

        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public List<BlogPost> list() {

        List<BlogPost> returnValue = new ArrayList<BlogPost>();

        try {

            String sql = "SELECT id, created, modified, title, author, description, url FROM BlogPost";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BlogPost blogpost = new BlogPost();
                getCommonFields(rs, blogpost);
                String url = rs.getString("url");
                blogpost.setUrl(url);
                returnValue.add(blogpost);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return returnValue;
    }

    public void update(BlogPost blogPost) {       
        update(blogPost, "url", blogPost.getUrl());
    }
}
