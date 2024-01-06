package telcommunity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserOrmawaChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //mahasiswa

    @ManyToOne
    @JoinColumn(name = "ormawa_channel_id", nullable = false)
    private OrmawaChannel ormawaChannel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrmawaChannel getOrmawaChannel() {
        return ormawaChannel;
    }

    public void setOrmawaChannel(OrmawaChannel ormawaChannel) {
        this.ormawaChannel = ormawaChannel;
    }

    

    
}
