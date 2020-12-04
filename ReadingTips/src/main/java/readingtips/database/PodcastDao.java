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

public class PodcastDao extends CommonDao {

    public PodcastDao() {
        this.table = "Podcast";
    }

    public void create(Podcast podcast) {

        try {
            String sql = "INSERT INTO Podcast(created, modified, title, author, description, nimi, length, position, positionComment) "
                    + "VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?, ?) ";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, podcast.getTitle());
            ps.setString(2, podcast.getAuthor());
            ps.setString(3, podcast.getDescription());
            ps.setString(4, podcast.getPodcastName());
            ps.setLong(5, podcast.getLength());
            ps.setLong(6, podcast.getPosition());
            ps.setString(7, podcast.getPositionComment());
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

            String sql = "SELECT id, created, modified, title, author, description, nimi, length, position, positionComment FROM Podcast WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, podcast.getId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                getCommonFields(rs, podcast);
                String nimi = rs.getString("nimi");
                Long length = rs.getLong("length");
                Long position = rs.getLong("position");
                String positionComment = rs.getString("positionComment");
                
                podcast.setPodcastName(nimi);
                podcast.setLength(length);
                podcast.setPosition(position);
                podcast.setPositionComment(positionComment);
            }

        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public List<Podcast> list() {

        List<Podcast> returnValue = new ArrayList<Podcast>();

        try {

            String sql = "SELECT id, created, modified, title, author, description, nimi, length, position, positionComment FROM Podcast";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Podcast podcast = new Podcast();
                getCommonFields(rs, podcast);
                String nimi = rs.getString("nimi");
                Long length = rs.getLong("length");
                Long position = rs.getLong("position");
                String positionComment = rs.getString("positionComment");
                
                podcast.setPodcastName(nimi);
                podcast.setLength(length);
                podcast.setPosition(position);
                podcast.setPositionComment(positionComment);
                returnValue.add(podcast);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return returnValue;
    }

    public void update(Podcast podcast) {       
        update(podcast, "nimi", podcast.getPodcastName());
    }
}
