package telcommunity.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class OrmawaChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ormawa_id", nullable = false)
    private Ormawa ormawa;

    @Column(name = "channel_name", nullable = true)
    private String channel_name;

    // OrmawaChannelChat
    @OneToMany(mappedBy = "ormawaChannel", fetch = FetchType.LAZY)
    private List<OrmawaChannelChat> ormawaChannelChats;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ormawa getOrmawa() {
        return ormawa;
    }

    public void setOrmawa(Ormawa ormawa) {
        this.ormawa = ormawa;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public List<OrmawaChannelChat> getOrmawaChannelChats() {
        return ormawaChannelChats;
    }

    public void setOrmawaChannelChats(List<OrmawaChannelChat> ormawaChannelChats) {
        this.ormawaChannelChats = ormawaChannelChats;
    }

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "channel_id", nullable = false)
    // private Channel channel;

    

}
