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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import readingtips.Video;

/**
 *
 * @author tiitinha
 */
public class VideoDao extends CommonDao {

    public VideoDao() {
        this.table = "Video";
    }

    public void create(Video video) {

        try {
            String sql = "INSERT INTO Video(created, modified, title, author, description, url, length, position, positionComment) "
                    + "VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?, ?) ";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, video.getTitle());
            ps.setString(2, video.getAuthor());
            ps.setString(3, video.getDescription());
            ps.setString(4, video.getUrl());
            ps.setLong(5, video.getLength());
            ps.setLong(6, video.getPosition());
            ps.setString(7, video.getPositionComment());
            ps.executeUpdate();
            video.setId(getId(ps));
            addTags(video);
            addCourses(video);

            reload(video);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void reload(Video video) {
        try {

            String sql = "SELECT id, created, modified, title, author, description, url, length, position, positionComment FROM Video WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, video.getId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                getCommonFields(rs, video);
                String url = rs.getString("url");
                Long length = rs.getLong("length");
                Long position = rs.getLong("position");
                String positionComment = rs.getString("positionComment");
                video.setUrl(url);
                video.setLength(length);
                video.setPosition(position);
                video.setPositionComment(positionComment);
            }

        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

    public List<Video> list() {

        List<Video> returnValue = new ArrayList<Video>();

        try {

            String sql = "SELECT id, created, modified, title, author, description, url, length, position, positionComment FROM Video";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Video video = new Video();
                getCommonFields(rs, video);
                String url = rs.getString("url");
                Long length = rs.getLong("length");
                Long position = rs.getLong("position");
                String positionComment = rs.getString("positionComment");
                video.setUrl(url);
                video.setLength(length);
                video.setPosition(position);
                video.setPositionComment(positionComment);
                returnValue.add(video);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return returnValue;
    }

    public void update(Video video) {       
        Map<String, Object> videoKentat = new HashMap<String, Object>();
        videoKentat.put("url", video.getUrl());
        videoKentat.put("length", video.getLength());
        videoKentat.put("position", video.getPosition());
        videoKentat.put("positionComment", video.getPositionComment());
        update(video, videoKentat);
    }

}
