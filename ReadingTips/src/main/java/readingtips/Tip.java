/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author aatukallio
 */
public abstract class Tip {
    private String type;
    private String title;
    private String author;
    private String description;
    private List<String> tags;
    private List<String> courses;
    
 
    public Tip(String type, String title, String author, String description, List<String> tags,List<String> courses) {
        this.author = author;
        this.description = description;
        this.type = type;
        this.title = title;
        this.tags = tags;
        this.courses = courses;
    }
    
    
    public String getType() {
        return this.type;
       
    }
    
    
    public String getTitle() {
        if (this.title != null) {
            return this.title;
        }
        return "";
    }
    
    
    public String getAuthor() {
        if (this.author != null) {
            return this.author;
        }
        return "";
    }
    
    
    public String getDescription() {
        if (this.description != null) {
            return this.description;
        }
        return "";
    }
    
    
    public List<String> getTags() {
        if (this.tags != null) {
            Collections.sort(tags);
            return this.tags;
        }
        return new ArrayList<>();
    }
    
    
    public List<String> getCourses() {
        if (this.courses != null) {
            Collections.sort(this.courses);
            return this.courses;
        }
        return new ArrayList<>();
    }
    
    
    @Override
    public String toString() {
        String returnString = "";
        
        String tagsString = this.getTags().toString();
        String coursesString = this.getCourses().toString();
        
        returnString += "Type: " + this.getType();
        if (!this.getTitle().isEmpty()) returnString += "\nTitle: " + this.getTitle();
        if (!this.getAuthor().isEmpty()) returnString += "\nAuthor: " + this.getAuthor();
        if (!this.getDescription().isEmpty()) returnString += "\nDescription: " + this.getDescription();
        if (tagsString.length() != 2) returnString += "\nCourses: " + tagsString.substring(1, tagsString.length()-1);
        if (coursesString.length() != 2) returnString += "\nTags: " + coursesString.substring(1, coursesString.length()-1);
        return returnString;
    }
}
