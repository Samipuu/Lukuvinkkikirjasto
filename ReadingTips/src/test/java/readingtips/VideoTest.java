package readingtips;

import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VideoTest {

    @Test
    public void urlIsCorrect() {
        Video video = new Video("Title", "Author", null, null, null, "URL");
        assertEquals("URL", video.getUrl());
    }

    @Test
    public void videoGetTitleNull() {
        Video video = new Video(null, "Author", "Description", null, null, null);
        assertEquals("", video.getTitle());
    }

    @Test
    public void videoToString() {
        Video video = new Video("Title", "Author", "Description", null, null, "url");
        String returnString = "";
        returnString += "Type: " + "Video";
        returnString += "\nTitle: " + "Title";
        returnString += "\nAuthor: " + "Author";
        returnString += "\nDescription: " + "Description";
        returnString += "\nURL: " + "url";
        assertEquals(returnString, video.toString());
    }

    @Test
    public void videoToStringWithName() {
        List<String> tags = new ArrayList();
        List<String> courses = new ArrayList();
        tags.add("eka");
        courses.add("tira");

        Video video = new Video("Name", "Author", "Description", tags, courses, null);
        String tagsString = video.getTags().toString();
        String coursesString = video.getCourses().toString();

        String returnString = "";
        returnString += "Type: " + "Video";
        returnString += "\nTitle: " + "Name";
        returnString += "\nAuthor: " + "Author";
        returnString += "\nDescription: " + "Description";
        returnString += "\nTags: " + tagsString.substring(1, tagsString.length() - 1);
        returnString += "\nCourses: " + coursesString.substring(1, coursesString.length() - 1);

        assertEquals(returnString, video.toString());
    }

    @Test
    public void equalsHuomaaNullin() {
        Video video = new Video("Title", "Author", "Description", null, null, "isbn");
        Video video2 = new Video("Title", "Author", "Description", null, null, "isbn");

        assertEquals(false, video.equals(null));
    }

    @Test
    public void equalsHuomaaVaaranLuokan() {
        Video video = new Video("Title", "Author", "Description", null, null, "isbn");
        Podcast podcast = new Podcast(null, "Author", "Description", null, null, null);

        assertEquals(false, video.equals(podcast));
    }

    @Test
    public void equalsTarkistaaJaFeilaa() {
        Video video = new Video("Title", "Author", "Description", null, null, "isbn");
        Video video2 = new Video("Title", "Author2", "Description", null, null, "isbn");

        assertEquals(false, video.equals(video2));
    }

    @Test
    public void equalsTarkistaaJaFeilaa2() {
        Video video = new Video("Title", "Author", "Description", null, null, "isbn");
        Video video2 = new Video("Title", "Author", "Description2", null, null, "isbn");

        assertEquals(false, video.equals(video2));
    }

    @Test
    public void equalsTarkistaaJaFeilaa23() {
        Video video = new Video("Title", "Author", "Description", null, null, "isbn");
        Video video2 = new Video("Title2", "Author", "Description", null, null, "isbn");

        assertEquals(false, video.equals(video2));
    }

}
