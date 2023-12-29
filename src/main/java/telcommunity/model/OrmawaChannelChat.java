package telcommunity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class OrmawaChannelChat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "ormawa_channel_id")
    private OrmawaChannel ormawaChannel;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Lob
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrmawaChannel getOrmawaChannel() {
        return ormawaChannel;
    }

    public void setOrmawaChannel(OrmawaChannel ormawaChannel) {
        this.ormawaChannel = ormawaChannel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    

    
}
