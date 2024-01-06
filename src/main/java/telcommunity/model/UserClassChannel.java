package telcommunity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserClassChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //mahasiswa

    @ManyToOne
    @JoinColumn(name = "class_channel_id", nullable = false)
    private ClassChannel classChannel;

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

    public ClassChannel getClassChannel() {
        return classChannel;
    }

    public void setClassChannel(ClassChannel classChannel) {
        this.classChannel = classChannel;
    }

    
}
