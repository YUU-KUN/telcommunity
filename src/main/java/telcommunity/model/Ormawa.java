package telcommunity.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ormawas")
public class Ormawa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "ormawa_name", nullable = false)
    private String ormawa_name;

    // private KetuaOrmawa ketua;

    // @OneToOne
    // private Channel channel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrmawa_name() {
        return ormawa_name;
    }

    public void setOrmawa_name(String ormawa_name) {
        this.ormawa_name = ormawa_name;
    }

    // public KetuaOrmawa getKetua() {
    // return ketua;
    // }

    // public void setKetua(KetuaOrmawa ketua) {
    // this.ketua = ketua;
    // }

    // public Channel getChannel() {
    // return channel;
    // }

    // public void setChannel(Channel channel) {
    // this.channel = channel;
    // }

}
