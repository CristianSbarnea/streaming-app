// insert stream command
public class InsertStream extends CommandDatabase {

    Database database = Database.getInstance();

    @Override
    void execute(Object toInsert) {
        database.addStream((Stream) toInsert);
    }
    // undo the execute operation
    @Override
    void undo() {
        database.deleteStream();
    }
}
