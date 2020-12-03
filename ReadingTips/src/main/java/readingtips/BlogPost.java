package readingtips;

import java.util.List;
import java.util.Objects;

public class BlogPost extends Tip {

    private String url;

    public BlogPost() {
        this.setType("BlogPost");
    }

    public BlogPost(String title, String author, String description, List<String> tags, List<String> courses, String url) {
        super("BlogPost", title, author, description, tags, courses);
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

    @Override
    public String toString() {
        String returnString = super.toString();
        if (!this.getUrl().isEmpty()) {
            returnString += "\nURL: " + this.getUrl();
        }
        return returnString;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BlogPost other = (BlogPost) obj;
        return !(!Objects.equals(this.url, other.url) || !Objects.equals(this.getAuthor(), other.getAuthor())
                || !Objects.equals(this.getTitle(), other.getTitle()) || !Objects.equals(this.getDescription(), other.getDescription()));
    }

}
