/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author tiitinha
 */
public abstract class DatabaseService {
    
    
    public static void addListValuesToDatabase(Connection conn, int id, String sqlQuery, List<String> listOfValues) {
        try {
            PreparedStatement ps;
            for (String value : listOfValues) {
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, value);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
        }
    }

    public static void addTags(Connection conn, int id, List<String> listOfValues) {
        String tagSql = "INSERT INTO Tag(tag, tipId) VALUES(?, ?)";
        addListValuesToDatabase(conn, id, tagSql, listOfValues);
    }

    public static void addCourses(Connection conn, int id, List<String> listOfValues) {
        String coursesSql = "INSERT INTO Course(course, tipId) VALUES(?, ?)";
        addListValuesToDatabase(conn, id, coursesSql, listOfValues);
    }
    
}
