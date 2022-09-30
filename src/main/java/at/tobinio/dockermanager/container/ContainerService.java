package at.tobinio.dockermanager.container;

import at.tobinio.dockermanager.webSocket.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created: 27.09.2022
 *
 * @author Tobias Frischmann
 */

@Service
public class ContainerService {

    @Autowired
    private WebSocketService webSocketService;

    private final Map<String, Container> containers;

    public ContainerService() {
        containers = new HashMap<>();

        addContainer(new Container("Test1", ContainerState.BUILDING));
        addContainer(new Container("Test2", ContainerState.HUFF));
        addContainer(new Container("Test3", ContainerState.RUNNING));
    }

    public void addContainer(Container container) {
        String uuid = UUID.randomUUID().toString();

        container.setUUID(uuid);

        containers.put(uuid, container);
    }

    public void removeContainer(String uuid) {
        containers.remove(uuid);
    }

    public Container[] getContainers() {
        return containers.values().toArray(new Container[]{});
    }

    public void sendContainerToEveryBody() {
        webSocketService.sendToEveryOne("/topic/all-container", getContainers());
    }
}
