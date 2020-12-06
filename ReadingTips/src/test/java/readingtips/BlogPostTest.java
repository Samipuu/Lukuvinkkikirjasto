package readingtips;

import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BlogPostTest {

    @Test
    public void urlIsCorrect() {
        BlogPost blogPost = new BlogPost("Title", "Author", null, null, null, "URL");
        assertEquals("URL", blogPost.getUrl());
    }

    @Test
    public void blogPostGetTitleNull() {
        BlogPost blogPost = new BlogPost(null, "Author", "Description", null, null, null);
        assertEquals("", blogPost.getTitle());
    }

    @Test
    public void blogPostToString() {
        BlogPost blogPost = new BlogPost("Title", "Author", "Description", null, null, "url");
        String returnString = "";
        returnString += "Type: " + "BlogPost";
        returnString += "\nTitle: " + "Title";
        returnString += "\nAuthor: " + "Author";
        returnString += "\nDescription: " + "Description";
        returnString += "\nURL: " + "url";
        assertEquals(returnString, blogPost.toString());
    }

    @Test
    public void blogPostToStringWithName() {
        List<String> tags = new ArrayList();
        List<String> courses = new ArrayList();
        tags.add("eka");
        courses.add("tira");

        BlogPost blogPost = new BlogPost("Name", "Author", "Description", tags, courses, null);
        String tagsString = blogPost.getTags().toString();
        String coursesString = blogPost.getCourses().toString();

        String returnString = "";
        returnString += "Type: " + "BlogPost";
        returnString += "\nTitle: " + "Name";
        returnString += "\nAuthor: " + "Author";
        returnString += "\nDescription: " + "Description";
        returnString += "\nTags: " + tagsString.substring(1, tagsString.length() - 1);
        returnString += "\nCourses: " + coursesString.substring(1, coursesString.length() - 1);

        assertEquals(returnString, blogPost.toString());
    }

    @Test
    public void equalsHuomaaNullin() {
        BlogPost blogPost = new BlogPost("Title", "Author", "Description", null, null, "isbn");

        assertEquals(false, blogPost.equals(null));
    }

    @Test
    public void equalsHuomaaVaaranLuokan() {
        BlogPost blogPost = new BlogPost("Title", "Author", "Description", null, null, "isbn");
        Podcast podcast = new Podcast(null, "Author", "Description", null, null, null);

        assertEquals(false, blogPost.equals(podcast));
    }

    @Test
    public void equalsTarkistaaJaFeilaa() {
        BlogPost blogPost = new BlogPost("Title", "Author", "Description", null, null, "isbn");
        BlogPost blogPost2 = new BlogPost("Title", "Author2", "Description", null, null, "isbn");

        assertEquals(false, blogPost.equals(blogPost2));
    }

    @Test
    public void equalsTarkistaaJaFeilaa2() {
        BlogPost blogPost = new BlogPost("Title", "Author", "Description", null, null, "isbn");
        BlogPost blogPost2 = new BlogPost("Title", "Author", "Description2", null, null, "isbn");

        assertEquals(false, blogPost.equals(blogPost2));
    }

    @Test
    public void equalsTarkistaaJaFeilaa23() {
        BlogPost blogPost = new BlogPost("Title", "Author", "Description", null, null, "isbn");
        BlogPost blogPost2 = new BlogPost("Title2", "Author", "Description", null, null, "isbn");

        assertEquals(false, blogPost.equals(blogPost2));
    }

}
