package readingtips;
import java.util.List;

public class BlogPost extends Tip {
    private String url;
    
    public BlogPost(String title, String author, String description, List<String> tags, List<String> courses,String url) {
        super("BlogPost", title, author, description, tags, courses);
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

    

