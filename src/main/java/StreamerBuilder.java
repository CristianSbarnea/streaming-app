// class that builds a streamer
public class StreamerBuilder {

    private Streamer streamer;

    public StreamerBuilder() {
        streamer = new Streamer();
    }

    public StreamerBuilder withType(String type) {
        streamer.setStreamerType(type);
        return this;
    }

    public StreamerBuilder withName(String name) {
        streamer.setName(name);
        return this;
    }

    public StreamerBuilder withId(String id) {
        streamer.setId(id);
        return this;
    }

    public Streamer build() {
        return streamer;
    }

}
