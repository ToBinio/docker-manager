package at.tobinio.dockermanager.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created: 27.09.2022
 *
 * @author Tobias Frischmann
 */

@Service
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendToEveryOne(String destination, Object payload) {
        simpMessagingTemplate.convertAndSend(destination, payload);
    }
}
