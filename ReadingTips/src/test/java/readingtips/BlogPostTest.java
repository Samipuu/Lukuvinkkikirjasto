package readingtips;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BlogPostTest {

    @Test
    public void urlIsCorrect() {
        BlogPost blogPost = new BlogPost("Title", "Author", null, null, null, "URL");
        assertEquals("URL", blogPost.getUrl());
    }

    @Test
    public void blogPostGetTitleNull() {
        BlogPost blogPost  = new BlogPost(null, "Author", "Description", null, null, null);
        assertEquals("", blogPost.getTitle());
    }

    @Test
    public void blogPostToString() {
        BlogPost blogPost  = new BlogPost("Title", "Author", "Description", null, null, "url");
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
              
        BlogPost blogPost  = new BlogPost("Name", "Author", "Description", tags, courses, null);
        String tagsString = blogPost.getTags().toString();
        String coursesString = blogPost.getCourses().toString();
        
        String returnString = "";
        returnString += "Type: " + "BlogPost";
        returnString += "\nTitle: " + "Name";
        returnString += "\nAuthor: " + "Author";        
        returnString += "\nDescription: " + "Description";
        returnString += "\nCourses: " + tagsString.substring(1, tagsString.length()-1);
        returnString += "\nTags: " + coursesString.substring(1, coursesString.length()-1);
                        
        assertEquals(returnString, blogPost.toString());
    }

}
