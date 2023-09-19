// interface for the recommending algorithms
public interface IAlgorithmsRecommend {
    Database database = Database.getInstance();
    public void generateRecommendation(String userId);
}
