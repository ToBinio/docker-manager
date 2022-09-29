package at.tobinio.dockermanager.webSocket;

import at.tobinio.dockermanager.container.Container;
import at.tobinio.dockermanager.container.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created: 27.09.2022
 *
 * @author Tobias Frischmann
 */
@Controller
public class WebSocketContainerController {

    @Autowired
    private ContainerService containerService;

    @MessageMapping ("/all-container")
    @SendTo ("/topic/all-container")
    public Container[] handleAllContainer() {
        return containerService.getContainers();
    }

}
