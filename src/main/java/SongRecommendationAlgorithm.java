import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongRecommendationAlgorithm implements IAlgorithmsRecommend {
    // algorithm that recommends songs based on user history
    public void generateRecommendation(String userId) {
        Integer id = Integer.parseInt(userId);
        List<Integer> listOfStreams = null;
        for (int i = 0; i < database.userList.size(); i++) {
            if (database.userList.get(i).getId().equals(id)) {
                listOfStreams = database.userList.get(i).getStreams();
                break;
            }
        }

        ArrayList<Stream> streamsToPrint = new ArrayList<Stream>();
        ArrayList<Integer> streamersIds = new ArrayList<Integer>();
        for (int i = 0; i < database.streamList.size(); i++) {
            if (listOfStreams.contains(database.streamList.get(i).getId())) {
                streamersIds.add(database.streamList.get(i).getStreamerId());
            }
        }

        for (int i = 0 ; i < streamersIds.size(); i++) {
            for (int j = 0; j < database.streamList.size(); j++) {
                if (database.streamList.get(j).getStreamType().equals(1)) {
                    if (database.streamList.get(j).getStreamerId().equals(streamersIds.get(i))
                            && !listOfStreams.contains(database.streamList.get(j).getId())) {
                        streamsToPrint.add(database.streamList.get(j));
                    }
                }
            }
        }

        ArrayList<String> strings = new ArrayList<String>();
        Collections.sort(streamsToPrint);
        for (int i = 0; i < streamsToPrint.size() && i < 5; i++) {
            strings.add(streamsToPrint.get(i).streamToJson(database.getStreamer(streamsToPrint.get(i).getStreamerId())));
        }

        database.printJson(strings);
    }
}
