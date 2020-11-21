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
public class Podcast extends Tip{
    private String podcastName;


    public Podcast(String title, String author, String description, List<String> tags, List<String> courses,String podcastName) {
        super("Podcast", title, author, description, tags, courses);
        this.podcastName = podcastName;
    }
    
    public String getPodcastName() {
        if (this.podcastName != null) {
            return this.podcastName;
        }
        return "";
    }
    
    @Override
    public String toString() {
        String returnString = super.toString();
        if (!this.getPodcastName().isEmpty()) returnString += "\nPodcast's name" + this.getPodcastName();
        return returnString;
    }
    
    
}
