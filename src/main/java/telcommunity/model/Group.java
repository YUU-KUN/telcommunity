package telcommunity.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "`groups`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "group_name", nullable = false)
    private String group_name;

    @Column(name = "logo", nullable = false)
    private String logo;

    // @ManyToMany(mappedBy = "groups")
    // @JoinTable(name = "group_members", joinColumns = @JoinColumn(name =
    // "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    // private Set<User> members = new HashSet<>();

    // @OneToMany(mappedBy = "group")
    // private Set<Chat> chats = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    // public Set<Chat> getChats() {
    // return chats;
    // }

    // public void setChats(Set<Chat> chats) {
    // this.chats = chats;
    // }

    // public Set<User> getMembers() {
    // return members;
    // }

    // public void setMembers(Set<User> members) {
    // this.members = members;
    // }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
