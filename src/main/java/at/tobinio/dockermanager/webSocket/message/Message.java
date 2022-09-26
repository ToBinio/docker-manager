package at.tobinio.dockermanager.webSocket.message;

/**
 * Created: 26.09.2022
 *
 * @author Tobias Frischmann
 */
public class Message {

    private String content;

    public Message(String content) {
        this.content = content;
    }

    public Message() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
