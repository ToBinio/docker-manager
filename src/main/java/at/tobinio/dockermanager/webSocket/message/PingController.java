package at.tobinio.dockermanager.webSocket.message;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created: 26.09.2022
 *
 * @author Tobias Frischmann
 */
@Controller
public class PingController {

    @MessageMapping ("/ping")
    @SendTo ("/topic/ping")
    public Message handlePingMessage(Message message) {
        return message;
    }
}
