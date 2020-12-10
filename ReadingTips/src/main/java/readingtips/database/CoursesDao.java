/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import static readingtips.database.Dao.conn;

/**
 *
 * @author suonpaas
 */
public class CoursesDao extends Dao{
    private ArrayList<String> courses;
    
    public CoursesDao() {
        this.coursesFromDatabase();
    }
    
    private void coursesFromDatabase() {
        ArrayList<String> coursesList = new ArrayList<>();
        try {
            String sql = "SELECT nimi FROM Course";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coursesList.add(rs.getString("nimi"));
            }
            this.courses = coursesList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public ArrayList<String> getAllCourses() {
        return courses;
    } 
}
