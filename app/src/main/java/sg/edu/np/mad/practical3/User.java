package sg.edu.np.mad.practical3;

public class User {
    public String name;
    public String description;
    public Integer id;
    public Boolean followed;

    public User(String name, String description, Integer id, Boolean followed) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.followed = followed;
    }
    public User(){

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getFollowed() {
        return followed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }

}
