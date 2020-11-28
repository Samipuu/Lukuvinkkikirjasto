package readingtips;

import java.util.List;

public class Video extends Tip{
    private String url;
    private Long length;
    private Long position;
    
    public Video() {};

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

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getPosition() {
        return position;
    }
    
    public void setPosition(Long position) {
        this.position = position;
    }

    @Override
    public String toString() {
        String returnString = super.toString();
        if (!this.getUrl().isEmpty()) returnString += "\nURL: " + this.getUrl();
        return returnString;
    }
    
}
