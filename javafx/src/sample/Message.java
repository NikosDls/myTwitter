package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by theodori on 3/5/2015.
 */
public class Message {

    String Id;
    String  From;
    String  Message;

    public Message(String id, String from, String message) {
        Id = id;
        From = from;
        Message = message;
    }



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void print(){
        System.out.println("Message:"+Id+","+Message);
    }
}
