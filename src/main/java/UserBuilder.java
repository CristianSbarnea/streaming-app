// class that builds a user
public class UserBuilder {

    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder withId(String id) {
        this.user.setId(id);
        return this;
    }

    public UserBuilder withName(String name) {
        this.user.setName(name);
        return this;
    }

    public UserBuilder withStreams(String streams) {
        this.user.setStreams(streams);
        return this;
    }

    public User build() {
        return user;
    }
}
