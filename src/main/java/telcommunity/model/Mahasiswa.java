package telcommunity.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mahasiswas")
public class Mahasiswa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "nim", nullable = false, unique = true)
    private String nim;

    @Column(name = "angkatan", nullable = false)
    private String angkatan;

    @Column(name = "fakultas", nullable = false)
    private String fakultas;

    @Column(name = "jurusan", nullable = false)
    private String jurusan;

    @Column(name = "kelas", nullable = false)
    private String kelas;

    // @ManyToMany
    // @JoinTable(name = "mahasiswa_group", joinColumns = @JoinColumn(name =
    // "mahasiswa_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    // private Set<Group> groups = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "mahasiswa_channel", joinColumns = @JoinColumn(name = "mahasiswa_id"), inverseJoinColumns = @JoinColumn(name = "channel_id"))
    private Set<Channel> channels = new HashSet<>();

    public void joinChannel(Channel channel) {
        this.channels.add(channel);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}