package readingtips.entity;

import readingtips.entity.Tip;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Podcast extends Tip {

    private String podcastName;
    private Long length;
    private Long position;
    private String positionComment;
    private String url;

    public Podcast() {
        this.setType("Podcast");
    }

    public Podcast(String title, String author, String description, List<String> tags, List<String> courses, String podcastName) {
        super("Podcast", title, author, description, tags, courses);
        this.podcastName = podcastName;
    }

    // NOT WORKING YET
    // public void update(LocalDateTime created, LocalDateTime modified, String title, String author, String description, List<String> tags, List<String> courses, String podcastName) {
    //     this.podcastName = podcastName;
    //     updateCommon(created, modified, title, author, description, tags, courses);
    // }    
    public String getPodcastName() {
        if (this.podcastName != null) {
            return this.podcastName;
        }
        return "";
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
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
        if (!this.getPodcastName().isEmpty()) {
            returnString += "\nPodcast: " + this.getPodcastName();
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
        final Podcast other = (Podcast) obj;
        return !(!Objects.equals(this.podcastName, other.podcastName) || !Objects.equals(this.getAuthor(), other.getAuthor())
                || !Objects.equals(this.getTitle(), other.getTitle()) || !Objects.equals(this.getDescription(), other.getDescription()));
    }
}
