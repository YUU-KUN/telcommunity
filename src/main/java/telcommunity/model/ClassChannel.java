package telcommunity.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ClassChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "class_name", nullable = false)
    private String class_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dosen_id", nullable = false, unique = false)
    // @Column(name = "dosen_id", nullable = false)
    private Dosen dosen;

    @Column(name = "logo", nullable = false)
    private String logo;

    // ClassChannelChat
    @OneToMany(mappedBy = "classChannel", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ClassChannelChat> classChannelChats;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<ClassChannelChat> getClassChannelChats() {
        return classChannelChats;
    }

    public void setClassChannelChats(List<ClassChannelChat> classChannelChats) {
        this.classChannelChats = classChannelChats;
    }

    public Object getChat_balloons() {
        return null;
    }

}
