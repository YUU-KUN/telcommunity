package telcommunity.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ketua_ormawas")
public class KetuaOrmawa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // @OneToOne
    // private Ormawa ormawa;
}
