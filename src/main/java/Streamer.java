// streamer class
public class Streamer {
    private Integer streamerType;
    private Integer id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public void setStreamerType(String streamerType) {
        this.streamerType = Integer.parseInt(streamerType);
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
