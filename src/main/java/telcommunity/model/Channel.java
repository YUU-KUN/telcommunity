package telcommunity.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "channel_name", nullable = false)
    private String channel_name;

    // @OneToMany(mappedBy = "channel")
    // private Set<Chat> chats = new HashSet<>();

    @ManyToMany(mappedBy = "channels")
    private Set<Mahasiswa> mahasiswas = new HashSet<>();

    // @OneToOne
    // private Ormawa ormawa;
}
