package readingtips;
import java.util.List;

public class Podcast extends Tip{
    private String podcastName;

    public Podcast() {}

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
    
    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    @Override
    public String toString() {
        String returnString = super.toString();
        if (!this.getPodcastName().isEmpty()) returnString += "\nPodcast: " + this.getPodcastName();
        return returnString;
    }
    
}
