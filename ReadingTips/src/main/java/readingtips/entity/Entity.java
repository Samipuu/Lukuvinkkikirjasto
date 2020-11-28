
package readingtips.entity;

import java.time.LocalDateTime;

public class Entity {

    protected Integer id;
    private LocalDateTime created;
    private LocalDateTime modified;

    public void updateEntity(LocalDateTime created, LocalDateTime modified) {
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModifiedDate(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreatedD(LocalDateTime created) {
        this.created = created;
    }

    public void checkIsInitialized() {
        if ( this.id == null ) {
            throw new RuntimeException("Entity not initialized.");
        }
    }
    
}
