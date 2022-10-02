package at.tobinio.dockermanager.rest;

import at.tobinio.dockermanager.container.Container;
import at.tobinio.dockermanager.container.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    }

    @PostMapping ("/api/remove-container")
    public void removeContainerHandler(@RequestBody Requests.RemoveRequest request) {
        containerService.removeContainer(request.uuid());
    }
}
