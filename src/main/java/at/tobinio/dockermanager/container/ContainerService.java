package at.tobinio.dockermanager.container;

import at.tobinio.dockermanager.webSocket.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

        basicAddContainer(new Container("Test1", ContainerState.DOWNLOAD, "ToBinio"));
        basicAddContainer(new Container("Test2", ContainerState.RUNNING, "ToBinio"));
        basicAddContainer(new Container("Test3", ContainerState.RUNNING, "ToBinio"));
    }

    public void addContainer(Container container) {
        basicAddContainer(container);

        sendContainerToEveryBody();
        updateContainer(container.getUUID());
    }

    private void basicAddContainer(Container container) {
        String uuid = UUID.randomUUID().toString();
        container.setUUID(uuid);

        containers.put(uuid, container);
    }

    public void removeContainer(String uuid) {
        containers.remove(uuid);
        sendContainerToEveryBody();
    }

    public Container[] getContainers() {
        return containers.values().toArray(new Container[]{});
    }

    public void updateContainer(String uuid) {

        Container container = containers.get(uuid);

        //todo handle errors

        try {
            //Download files
            container.setContainerState(ContainerState.DOWNLOAD);
            sendContainerToEveryBody();

            Process downLoadProcess = Runtime.getRuntime().exec("git clone https://github.com/%s/%s".formatted(container.getOwner(), container.getName()));
            downLoadProcess.waitFor();
            printProcessInfo(downLoadProcess, "download");

            //deleting folders
            container.setContainerState(ContainerState.REMOVING_GARBAGE);
            sendContainerToEveryBody();

            Process deleteFoldersProcess = Runtime.getRuntime().exec("rm -r %s".formatted(container.getName()));
            deleteFoldersProcess.waitFor();
            printProcessInfo(deleteFoldersProcess, "delete folders");

            //running
            container.setContainerState(ContainerState.RUNNING);
            sendContainerToEveryBody();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printProcessInfo(Process process, String name) throws IOException {
        BufferedReader readerInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader readerError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        System.out.println("--" + name + "--");

        System.out.println("input:");
        String lineInput = "";
        while ((lineInput = readerInput.readLine()) != null) {
            System.out.println("    " + lineInput);
        }

        System.out.println("error:");
        String lineError = "";
        while ((lineError = readerError.readLine()) != null) {
            System.out.println("    " + lineError);
        }

        System.out.println("exit Value:" + process.exitValue());
    }

    public void sendContainerToEveryBody() {
        webSocketService.sendToEveryOne("/topic/all-container", getContainers());
    }
}
