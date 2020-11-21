/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadingTips;

import java.util.List;

/**
 *
 * @author aatukallio
 */
public class Video extends Tip{
    private String url;
    
    public Video(String title, String author, String description, List<String> tags, List<String> courses,String url) {
        super("Video", title, author, description, tags, courses);
        this.url = url;
    }
    
    public String getUrl() {
        if (this.url != null) {
            return this.url;
        }
        return "";
    }
    
    @Override
    public String toString() {
        String returnString = super.toString();
        if (!this.getUrl().isEmpty()) returnString += "\nURL: " + this.getUrl();
        return returnString;
    }
    
}
