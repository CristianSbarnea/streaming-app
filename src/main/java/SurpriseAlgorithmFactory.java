// factory for the surprise algorithms
public class SurpriseAlgorithmFactory {

    //gives us the right surprise algorithm based on type
    public static IAlgorithmsSurprise surprise(String type) {
        switch(type) {
            case "SONG":
                return new SongSurpriseAlgorithm();

            case "PODCAST":
                return new PodcastSurpriseAlgorithm();

            case "AUDIOBOOK":
                return new AudiobookSurpriseAlgorithm();
        }
        return null;
    }
}
