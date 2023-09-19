import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

// class for a stream
public class Stream implements Comparable<Stream> {

    private Integer streamType;
    private Integer id;
    private Integer streamGenre;
    private Integer streamerId;
    private Long noOfStreams;
    private Long length;
    private Long dateAdded;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = Long.parseLong(dateAdded);
    }
    public void setNoOfStreams(String noOfStreams) {
        this.noOfStreams = Long.parseLong(noOfStreams);
    }

    public void setLength(String length) {
        this.length = Long.parseLong(length);
    }
    public void setStreamerId(String streamerId) {
        this.streamerId = Integer.parseInt(streamerId);
    }
    public void setStreamGenre(String streamGenre) {
        this.streamGenre = Integer.parseInt(streamGenre);
    }
    public void setStreamType(String streamType) {
        this.streamType = Integer.parseInt(streamType);
    }

    public Long getLength() {
        return length;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStreamerId() {
        return streamerId;
    }

    public Integer getStreamType() {
        return streamType;
    }

    public Long getNoOfStreams() {
        return noOfStreams;
    }

    // function that takes a stream object and transforms it into json format
    public String streamToJson(Streamer streamer) {
        Long totalSec = this.getLength();
        Long minutes = (totalSec % 3600) / 60;
        Long seconds = totalSec % 60;
        String time;
        if (totalSec < 60 * 60) {
            time = String.format("%02d:%02d", minutes, seconds);
        } else {
            Long hours = totalSec / 3600;
            time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        Date date = new Date(this.getDateAdded() * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formattedDate = simpleDateFormat.format(date);
        return "\"id\":" + "\"" + this.getId() + "\"" +",\"name\":" + "\"" + this.getName() +
                            "\"" + ",\"streamerName\":" + "\"" + streamer.getName() + "\"" + ",\"noOfListenings\":" +
                           "\"" + this.getNoOfStreams() + "\"" + ",\"length\":" +
                            "\"" + time + "\"" + ",\"dateAdded\":" + "\"" + formattedDate +
                            "\"";
    }

    @Override
    public int compareTo(Stream o) {
        return (int)(getNoOfStreams() - o.getNoOfStreams());
    }
}
