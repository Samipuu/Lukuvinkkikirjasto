package readingtips.entity;

import java.util.List;
import java.util.Objects;

public class Video extends Tip {

    private String url;
    private Long length;
    private Long position;
    private String positionComment;

    public Video() {
        this.setType("Video");
    }

    public Video(String title, String author, String description, List<String> tags, List<String> courses, String url) {
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
        if (this.length != null) {
            return this.length;
        }
        return (long) 0;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getPosition() {
        if (this.position != null) {
            return position;
        }
        return (long) 0;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public void setPositionComment(String comment) {
        this.positionComment = comment;
    }

    public String getPositionComment() {
        if (this.positionComment != null) {
            return positionComment;
        }
        return "";
    }

    @Override
    public String toString() {
        String returnString = super.toString();
        if (!this.getUrl().isEmpty()) {
            returnString += "\nURL: " + this.getUrl();
        }
        if (this.getLength() != 0) {
            returnString += "\nLength: ";
            returnString += super.stringTime(this.length);
        }
        if (this.getPosition() != 0) {
            returnString += "\nTimestamp: ";
            returnString += super.stringTime(this.getPosition());
        }
        if (!this.getPositionComment().isEmpty()) {
            returnString += "\nTimestamp information: " + this.getPositionComment();
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
        final Video other = (Video) obj;
        return !(!Objects.equals(this.url, other.url) || !Objects.equals(this.getAuthor(), other.getAuthor())
                || !Objects.equals(this.getTitle(), other.getTitle()) || !Objects.equals(this.getDescription(), other.getDescription()));
    }
}
