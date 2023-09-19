// class that builds a stream
public class StreamBuilder {

    private Stream stream;

    public StreamBuilder() {
        stream = new Stream();
    }

    public StreamBuilder withType(String type) {
        stream.setStreamType(type);
        return this;
    }

    public StreamBuilder withId(String id) {
        stream.setId(id);
        return this;
    }

    public StreamBuilder withGenre(String genre) {
        stream.setStreamGenre(genre);
        return this;
    }

    public StreamBuilder withNoOfStreams(String no) {
        stream.setNoOfStreams(no);
        return this;
    }

    public StreamBuilder withStreamerId(String streamerId) {
        stream.setStreamerId(streamerId);
        return this;
    }

    public StreamBuilder withLength(String length) {
        stream.setLength(length);
        return this;
    }

    public StreamBuilder dateAdded(String date) {
        stream.setDateAdded(date);
        return this;
    }

    public StreamBuilder withName(String name) {
        stream.setName(name);
        return this;
    }

    public Stream build() {
        return stream;
    }
}
