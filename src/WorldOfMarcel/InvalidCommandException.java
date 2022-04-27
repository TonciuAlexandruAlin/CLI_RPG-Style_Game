package WorldOfMarcel;

public class InvalidCommandException extends Throwable{

    public String message;
    public InvalidCommandException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
