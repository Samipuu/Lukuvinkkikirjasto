/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips.database;

import java.util.ArrayList;
import java.util.List;
import readingtips.Podcast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import readingtips.BlogPost;
import readingtips.Book;

/**
 *
 * @author tiitinha
 */
public class PodcastDao extends CommonDao {

    public PodcastDao() {
        this.table = "Podcast";
    }

    public void create(Podcast podcast) {

        try {
            String sql = "INSERT INTO Podcast(created, modified, title, author, description, nimi) "
                    + "VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?, ?) ";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, podcast.getTitle());
            ps.setString(2, podcast.getAuthor());
            ps.setString(3, podcast.getDescription());
            ps.setString(4, podcast.getPodcastName());
            ps.executeUpdate();
            podcast.setId(getId(ps));
            addTags(podcast);
            addCourses(podcast);

            reload(podcast);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void reload(Podcast podcast) {
        try {

            String sql = "SELECT id, created, modified, title, author, description, nimi FROM Podcast WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, podcast.getId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                getCommonFields(rs, podcast);
                String nimi = rs.getString("nimi");
                podcast.setPodcastName(nimi);
            }

        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public List<Podcast> list() {

        List<Podcast> returnValue = new ArrayList<Podcast>();

        try {

            String sql = "SELECT id, created, modified, title, author, description, nimi FROM Podcast";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Podcast podcast = new Podcast();
                getCommonFields(rs, podcast);
                String nimi = rs.getString("nimi");
                podcast.setPodcastName(nimi);
                returnValue.add(podcast);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return returnValue;
    }

    public void update(Podcast podcast) {       
        update(podcast, "podcastName", podcast.getPodcastName());
    }
}
