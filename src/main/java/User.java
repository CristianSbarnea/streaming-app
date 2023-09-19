import java.util.ArrayList;
import java.util.List;

// class for a user
public class User {

        private String name;
        private Integer id;
        private List<Integer> streams = new ArrayList<Integer>();

        public void setName(String name) {
            this.name = name;
        }

        public void setId(String id) {
            this.id = Integer.parseInt(id);
        }

        public void setStreams(String streams) {
            String[] thisStreams = streams.split(" ");
            for (int i = 0; i < thisStreams.length; i++) {
                Integer idTemp = Integer.parseInt(thisStreams[i]);
                this.streams.add(idTemp);
            }
        }

    public List<Integer> getStreams() {
        return streams;
    }

    public Integer getId() {
        return id;
    }
}
