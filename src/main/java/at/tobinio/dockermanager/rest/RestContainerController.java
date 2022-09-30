package at.tobinio.dockermanager.rest;

import at.tobinio.dockermanager.container.Container;
import at.tobinio.dockermanager.container.ContainerService;
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

    static class RemoveRequest {
        private String uuid;

        public RemoveRequest(String uuid) {
            this.uuid = uuid;
        }

        public RemoveRequest() {
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }

    @PostMapping ("/api/remove-container")
    public void removeContainerHandler(@RequestBody RemoveRequest request) {
        containerService.removeContainer(request.uuid);
        containerService.sendContainerToEveryBody();
    }
}
