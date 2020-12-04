package readingtips;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import readingtips.entity.Entity;

public abstract class Tip extends Entity {
    private String type;
    private String title;
    private String author;
    private String description;
    private List<String> tags;
    private List<String> courses;
    
    public Tip() {}
 
    public Tip(String type, String title, String author, String description, List<String> tags,List<String> courses) {
        this.author = author;
        this.description = description;
        this.type = type;
        this.title = title;
        if(tags == null) tags = new ArrayList<String>();
        this.tags = tags;
        if(courses == null) courses = new ArrayList<String>();
        this.courses = courses;
    }

    public void updateCommon(int id, LocalDateTime created, LocalDateTime modified, String title, String author, String description, List<String> tags,List<String> courses) {
        this.author = author;
        this.description = description;
        this.title = title;
        this.tags = tags;
        this.courses = courses;
        this.id = id;
        updateEntity(created, modified);
    }    

    public void updateCommon(int id, LocalDateTime created, LocalDateTime modified, String title, String author, String description) {
        this.author = author;
        this.description = description;
        this.title = title;
        this.id = id;
        // this.tags = tags;
        // this.courses = courses;
        updateEntity(created, modified);
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
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setType(String type) {
        this.type = type;
    }
        
    public String getAuthor() {
        if (this.author != null) {
            return this.author;
        }
        return "";
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
        
    public String getDescription() {
        if (this.description != null) {
            return this.description;
        }
        return "";
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
        
    public List<String> getTags() {
        if (this.tags != null) {
            Collections.sort(tags);
            return this.tags;
        }
        return new ArrayList<>();
    }
    
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
        
    public List<String> getCourses() {
        if (this.courses != null) {
            Collections.sort(this.courses);
            return this.courses;
        }
        return new ArrayList<>();
    }
    
    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
    
    protected String stringTime(Long time) {
        String ret = "";
        Long hours = time/3600;
        if (hours>0) ret += hours + " h ";
        Long minutes = time % 3600 /60;
        if (minutes>0) ret += minutes + " min ";
        Long seconds = time % 60;
        ret += seconds + " s";
        return ret;
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
        if (tagsString.length() != 2) returnString += "\nTags: " + tagsString.substring(1, tagsString.length()-1);
        if (coursesString.length() != 2) returnString += "\nCourses: " + coursesString.substring(1, coursesString.length()-1);
        if (this.id != null) returnString += "\nID: " + this.id;
        return returnString;
    }
}
