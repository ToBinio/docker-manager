package at.tobinio.dockermanager.rest;

import at.tobinio.dockermanager.container.Container;
import at.tobinio.dockermanager.container.ContainerService;
import at.tobinio.dockermanager.webSocket.WebSocketContainerController;
import at.tobinio.dockermanager.webSocket.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created: 28.09.2022
 *
 * @author Tobias Frischmann
 */

@RestController
public class RestContainerController {

    @Autowired
    private ContainerService containerService;

    @PostMapping ("/api/add-container")
    public void AddContainerHandler(@RequestBody Container container) {
        containerService.addContainer(container);

        containerService.sendContainerToEveryBody();
    }
}
