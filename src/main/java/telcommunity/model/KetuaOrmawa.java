package telcommunity.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ketua_ormawas")
public class KetuaOrmawa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ormawa_id", nullable = true)
    private Ormawa ormawa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
    
    @Column(name = "status", nullable = false)
    private String status; // FORMER, CURRENT, PENDING
    
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
