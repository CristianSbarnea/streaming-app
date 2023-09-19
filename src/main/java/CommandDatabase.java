// abstract class for command design pattern, applied on the database
public abstract class CommandDatabase {

    abstract void execute(Object toInsert);
    abstract void undo();

}
