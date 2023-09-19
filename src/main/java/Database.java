import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {

    private static Database database = null;
    List<User> userList = new ArrayList<User>();
    List<Streamer> streamerList = new ArrayList<Streamer>();
    List<Stream> streamList = new ArrayList<Stream>();

    private Database() {

    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void deleteUser() {
        userList.remove(userList.size() - 1);
    }

    public void addStream(Stream stream) {
        streamList.add(stream);
    }
    public void addStreamBeginning(Stream stream) {
        streamList.add(0, stream);
    }
    public void deleteStream() {
        streamList.remove(streamList.size() - 1);
    }
    public void deleteStreamBeginning() {
        streamList.remove(0);
    }
    public void addStreamer(Streamer streamer) {
        streamerList.add(streamer);
    }
    public void deleteStreamer() {
        streamerList.remove(streamerList.size() - 1);
    }

    // function that prints the list of streams for an id of a user or streamer
    public void list(String idString) {
        Integer ID = Integer.parseInt(idString);
        ArrayList<String> toPrint = new ArrayList<String>();
        for (int i = 0; i < streamList.size(); i++) {
            if (Objects.equals(streamList.get(i).getStreamerId(), ID)) {
                toPrint.add(streamList.get(i).streamToJson(getStreamer(ID)));
            }
        }

        printJson(toPrint);
        toPrint.clear();

        for (int i = 0; i < userList.size(); i++) {
            if (Objects.equals(userList.get(i).getId(), ID)) {
                User user = userList.get(i);
                for (int k = 0; k < user.getStreams().size(); k++) {
                    for (int j = 0; j < streamList.size(); j++) {
                        if (user.getStreams().get(k).equals(streamList.get(j).getId())) {
                            toPrint.add(streamList.get(j).streamToJson(getStreamer(streamList.get(j).getStreamerId())));
                        }
                    }
                }
            }
        }
        printJson(toPrint);


    }

    // function that searches a streamer object for its id and returns a reference to it
    public Streamer getStreamer(Integer id) {
        for (int i = 0; i < streamerList.size(); i++) {
            if (Objects.equals(streamerList.get(i).getId(), id)) {
                return streamerList.get(i);
            }
        }
        return null;
    }

    // function that deletes a stream
    public void removeStream(String ID) {
        Integer id = Integer.parseInt(ID);
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).getStreams().remove(id);
        }

        for (int i = 0; i < streamList.size(); i++) {
            if (streamList.get(i).getId().equals(id)) {
                streamList.remove(i);
                return;
            }
        }
    }

    // function that updates user streams and stream when he starts listening
    public void userListen(String userId, String streamId) {
        Integer UID = Integer.parseInt(userId);
        Integer SID = Integer.parseInt(streamId);
        for (int i = 0; i < userList.size(); i++) {
            if (Objects.equals(userList.get(i).getId(), UID)) {
                if (!userList.get(i).getStreams().contains(SID)) {
                    userList.get(i).getStreams().add(SID);
                }
            }
        }

        for (int i = 0; i < streamList.size(); i++) {
            if (Objects.equals(streamList.get(i).getId(), SID)) {
                long noOfStr = streamList.get(i).getNoOfStreams();
                noOfStr++;
                String p = String.format("%d", noOfStr);
                streamList.get(i).setNoOfStreams(p);
            }
        }
    }

    // function that prints to json, already json formatted objects
    public void printJson(ArrayList<String> toPrint) {
        if (toPrint.size() != 0) {
            System.out.print("[{");
        }
        for (int i = 0; i < toPrint.size(); i++) {
            System.out.print(toPrint.get(i));
            if (i == toPrint.size() - 1) {
                System.out.println("}]");
            } else {
                System.out.print("},{");
            }
        }
    }


}
