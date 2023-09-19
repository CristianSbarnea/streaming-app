// insert streamer command for the database
public class InsertStreamer extends CommandDatabase {

    Database database = Database.getInstance();

    @Override
    void execute(Object toInsert) {
        database.addStreamer((Streamer) toInsert);
    }

    // undo the execute command
    @Override
    void undo() {
        database.deleteStreamer();
    }
}
