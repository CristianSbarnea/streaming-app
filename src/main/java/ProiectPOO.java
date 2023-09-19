import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProiectPOO {

    public static void main(String[] args) {

        if(args == null) {
            System.out.println("Nothing to read here");
            return;
        }

        Database database = Database.getInstance();  // init database
        InsertUser insertUser = new InsertUser();  // init insertUser command
        InsertStreamer insertStreamer = new InsertStreamer();
        InsertStream insertStream = new InsertStream();
        final String resourcesMain = "src/main/resources/";
        final String streamers = resourcesMain + args[0];
        final String streams = resourcesMain + args[1];
        final String users = resourcesMain + args[2];
        final String commands = resourcesMain + args[3];
        boolean flag = true;
        String line;

        try (FileReader fr = new FileReader(streamers); BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                if (flag) {
                    flag = false;
                    continue;
                }
                String[] info = line.split(",");
                for (int i = 0; i < info.length; i++) {
                    info[i] = info[i].substring(1, info[i].length() - 1);
                }
                Streamer streamer = new StreamerBuilder().
                                    withType(info[0]).
                                    withId(info[1]).
                                    withName(info[2]).
                                    build();
                insertStreamer.execute(streamer);  // insert streamer
            }
        } catch (IOException e) {
            System.out.println("Error opening file " + streamers);
        }

        flag = true;
        try (FileReader fr = new FileReader(streams); BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                if (flag) {
                    flag = false;
                    continue;
                }
                String[] info = line.split("\"");
                List<String> list = new ArrayList<String>(Arrays.asList(info));
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(","))
                        list.remove(i);
                }
                if (list.get(0).equals(""))
                    list.remove(0);
                Stream stream = new StreamBuilder().
                                withType(list.get(0)).
                                withId(list.get(1)).
                                withGenre(list.get(2)).
                                withNoOfStreams(list.get(3)).
                                withStreamerId(list.get(4)).
                                withLength(list.get(5)).
                                dateAdded(list.get(6)).
                                withName(list.get(7)).
                                build();
                insertStream.execute(stream);  // insert stream
            }
        } catch (IOException e) {
            System.out.println("Error opening file " + streams);
        }

        flag = true;
        try (FileReader fr = new FileReader(users); BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                if (flag) {
                    flag = false;
                    continue;
                }
                String[] info = line.split(",");
                for (int i = 0; i < info.length; i++) {
                    info[i] = info[i].substring(1, info[i].length() - 1);  // delete first and last char
                }
                User user = new UserBuilder().
                            withId(info[0]).
                            withName(info[1]).
                            withStreams(info[2]).
                            build();
                insertUser.execute(user);  // insert user
            }
        } catch (IOException e) {
            System.out.println("Error opening file " + users);
        }

        flag = true;
        try (FileReader fr = new FileReader(commands); BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                String[] info = line.split(" ");
                switch (info[1]) {
                    case "LIST":
                        database.list(info[0]);  // list command
                        break;
                    case "ADD":
                        List<String> listTemp = new ArrayList<String>(Arrays.asList(line.split(" ", 7)));
                        for (int i = 0; i < listTemp.size(); i++) {
                            if (listTemp.get(i).equals("") || listTemp.get(i).equals(" ")) {
                                listTemp.remove(i);
                            }
                        }
                        Stream stream = new StreamBuilder().
                                        withStreamerId(listTemp.get(0)).
                                        withType(listTemp.get(2)).
                                        withId(listTemp.get(3)).
                                        withGenre(listTemp.get(4)).
                                        withLength(listTemp.get(5)).
                                        withNoOfStreams("0").
                                        dateAdded(String.format("%s",System.currentTimeMillis() / 1000))
                                        .build();
                        insertStream.execute(stream);
                        break;
                    case "DELETE":
                        // delete command
                        database.removeStream(info[2]);
                        break;
                    case "LISTEN":
                        // listen command
                        database.userListen(info[0], info[2]);
                        break;
                    case "RECOMMEND":
                        // factory that gives us the right algorithm based on criteria
                        IAlgorithmsRecommend algorithmRecommend = RecommendationAlgorithmFactory.recommend(info[2]);
                        algorithmRecommend.generateRecommendation(info[0]);
                        break;
                    case "SURPRISE":
                        // factory that gives us the right algorithm based on criteria
                        IAlgorithmsSurprise algorithmSurprise = SurpriseAlgorithmFactory.surprise(info[2]);
                        algorithmSurprise.generateSurprise(info[0]);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error opening file " + commands);
        }
    }
}
