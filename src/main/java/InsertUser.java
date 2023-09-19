// insert user command
public class InsertUser extends CommandDatabase {

    Database database = Database.getInstance();

    @Override
    void execute(Object toInsert) {
        database.addUser((User) toInsert);
    }

    @Override
    void undo() {
        database.deleteUser();
    }
}
