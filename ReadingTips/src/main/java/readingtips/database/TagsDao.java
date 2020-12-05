/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
/**
 *
 * @author aatukallio
 */
public class TagsDao extends Dao{
    private HashSet<String> tags;
    
    public TagsDao() {
        this.tags = this.tagsFromDatabase();
    }
    
    public HashSet<String> tagsFromDatabase() {
        HashSet<String> returnHash = new HashSet<>();
        try {
            String sql = "SELECT teksti FROM Tag";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                returnHash.add(rs.getString("teksti"));
            }
            return returnHash;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public HashSet<String> getAllTags() {
        return tags;
    } 
}
