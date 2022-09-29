package at.tobinio.dockermanager.container;

import at.tobinio.dockermanager.webSocket.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created: 27.09.2022
 *
 * @author Tobias Frischmann
 */

@Service
public class ContainerService {

    @Autowired
    private WebSocketService webSocketService;

    private final List<Container> containers;

    public ContainerService() {
        containers = new ArrayList<>();

        addContainer(new Container("Test1", ContainerState.BUILDING));
        addContainer(new Container("Test2", ContainerState.HUFF));
        addContainer(new Container("Test3", ContainerState.RUNNING));
    }

    public void addContainer(Container container) {
        containers.add(container);
    }

    public Container[] getContainers() {
        return containers.toArray(new Container[]{});
    }

    public void sendContainerToEveryBody() {
        webSocketService.sendToEveryOne("/topic/all-container", getContainers());
    }
}
