package telcommunity.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

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

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFormattedTime() {
        // from 2024-01-02 01:32:40.921111 to 01:32 AM
        return getCreatedAt().getHour() + ":" + getCreatedAt().getMinute() + " "
                + (getCreatedAt().getHour() >= 12 ? "PM" : "AM");
    }

}
