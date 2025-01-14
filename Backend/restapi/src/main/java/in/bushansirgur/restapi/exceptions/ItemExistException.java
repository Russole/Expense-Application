package in.bushansirgur.restapi.exceptions;

public class ItemExistException extends RuntimeException{
    public ItemExistException(String message) {
        super(message);
    }

}
