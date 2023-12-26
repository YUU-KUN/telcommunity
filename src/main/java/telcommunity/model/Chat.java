package telcommunity.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "sender_id", nullable = false)
    private String sender_id;

    @Column(name = "receiver_id", nullable = false)
    private String receiver_id;

}
