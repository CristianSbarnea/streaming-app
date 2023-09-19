import java.text.SimpleDateFormat;
import java.util.*;

public class PodcastSurpriseAlgorithm implements IAlgorithmsSurprise {
    // algorithm that gives the user surprise podcast recommendations based on user history
    @Override
    public void generateSurprise(String userId) {
        Integer id = Integer.parseInt(userId);
        List<Integer> listOfStreams = null;
        for (int i = 0; i < database.userList.size(); i++) {
            if (database.userList.get(i).getId().equals(id)) {
                listOfStreams = database.userList.get(i).getStreams();
                break;
            }
        }

        ArrayList<Stream> unheardStreams = new ArrayList<>();
        ArrayList<Integer> streamerIds = new ArrayList<>();

        for (int i = 0; i < database.streamList.size(); i++) {
            for (int j = 0; j < listOfStreams.size(); j++) {
                if (listOfStreams.get(j).equals(database.streamList.get(i).getId())) {
                    streamerIds.add(database.streamList.get(i).getStreamerId());
                }
            }
        }

        for (int j = 0; j < database.streamList.size(); j++) {
            if (database.streamList.get(j).getStreamType().equals(2)) {
                if (!streamerIds.contains(database.streamList.get(j).getStreamerId())) {
                    unheardStreams.add(database.streamList.get(j));
                }
            }
        }

        unheardStreams.sort(new Comparator<Stream>() {
            @Override
            public int compare(Stream o1, Stream o2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date1 = new Date(o1.getDateAdded() * 1000);
                String formattedDate1 = simpleDateFormat.format(date1);
                Date date2 = new Date(o2.getDateAdded() * 1000);
                String formattedDate2 = simpleDateFormat.format(date2);


                if (formattedDate1.equals(formattedDate2)) {
                    return (int) (o2.getNoOfStreams() - o1.getNoOfStreams());
                }
                return (int) (o2.getDateAdded() - o1.getDateAdded());
            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        ArrayList<String> strings = new ArrayList<String>();
        if (unheardStreams.size() > 2) {
            Date date1 = new Date(unheardStreams.get(0).getDateAdded() * 1000);
            String formattedDate1 = simpleDateFormat.format(date1);
            Date date2 = new Date(unheardStreams.get(1).getDateAdded() * 1000);
            String formattedDate2 = simpleDateFormat.format(date2);
            Date date3 = new Date(unheardStreams.get(2).getDateAdded() * 1000);
            String formattedDate3 = simpleDateFormat.format(date3);
            if (formattedDate1.equals(formattedDate2) && formattedDate2.equals(formattedDate3)) {
                strings.add(unheardStreams.get(0).streamToJson(database.getStreamer(unheardStreams.get(0).getStreamerId())));
                database.printJson(strings);
                return;
            }
        }

        for (int i = 0; i < unheardStreams.size() && i < 3; i++) {
            strings.add(unheardStreams.get(i).streamToJson(database.getStreamer(unheardStreams.get(i).getStreamerId())));
        }

        database.printJson(strings);
    }
}
