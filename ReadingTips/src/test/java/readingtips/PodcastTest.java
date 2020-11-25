package readingtips;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PodcastTest {

    @Test
    public void nameIsCorrect() {
        Podcast podcast = new Podcast("Title", "Author", null, null, null, "PodcastName");
        assertEquals("PodcastName", podcast.getPodcastName());
    }

    @Test
    public void podcastGetTitleNull() {
        Podcast podcast = new Podcast(null, "Author", "Description", null, null, null);
        assertEquals("", podcast.getPodcastName());
    }
    @Test
    public void podcastToString() {
        Podcast podcast = new Podcast("Title", "Author", "Description", null, null, "PodcastName");
        String returnString = "";
        returnString += "Type: " + "Podcast";
        returnString += "\nTitle: " + "Title";
        returnString += "\nAuthor: " + "Author";        
        returnString += "\nDescription: " + "Description";
        returnString += "\nPodcast: " + "PodcastName";
        assertEquals(returnString, podcast.toString());
    }

    @Test
    public void podcastToStringWithName() {
        List<String> tags = new ArrayList();
        List<String> courses = new ArrayList();
        tags.add("eka");
        courses.add("tira");       
              
        Podcast podcast = new Podcast("Name", "Author", "Description", tags, courses, null);
        String tagsString = podcast.getTags().toString();
        String coursesString = podcast.getCourses().toString();
        
        String returnString = "";
        returnString += "Type: " + "Podcast";
        returnString += "\nTitle: " + "Name";
        returnString += "\nAuthor: " + "Author";        
        returnString += "\nDescription: " + "Description";
        returnString += "\nCourses: " + tagsString.substring(1, tagsString.length()-1);
        returnString += "\nTags: " + coursesString.substring(1, coursesString.length()-1);
                        
        assertEquals(returnString, podcast.toString());
    }

}
