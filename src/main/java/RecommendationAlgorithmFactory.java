// factory for the recommendation algorithms
public class RecommendationAlgorithmFactory {

    // gives us the right algorithm for different types
    public static IAlgorithmsRecommend recommend(String type) {
        switch (type) {
            case "SONG":
                return new SongRecommendationAlgorithm();

            case "PODCAST":
                return new PodcastRecommendationAlgorithm();

            case "AUDIOBOOK":
                return new AudiobookRecommendationAlgorithm();

        }
        return null;
    }
}
