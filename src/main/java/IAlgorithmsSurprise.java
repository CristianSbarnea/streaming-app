// interface for the suprise algorithms
public interface IAlgorithmsSurprise {
    Database database = Database.getInstance();
    public void generateSurprise(String userId);
}
