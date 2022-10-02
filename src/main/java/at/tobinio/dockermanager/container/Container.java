package at.tobinio.dockermanager.container;

import java.util.UUID;

/**
 * Created: 27.09.2022
 *
 * @author Tobias Frischmann
 */
public class Container {

    private String name;
    private ContainerState containerState;
    private String UUIDString;
    private String owner;

    public Container(String name, ContainerState containerState, String owner) {
        this.name = name;
        this.containerState = containerState;
        this.UUIDString = UUID.randomUUID().toString();
    }

    public Container() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContainerState getContainerState() {
        return containerState;
    }

    public void setContainerState(ContainerState containerState) {
        this.containerState = containerState;
    }

    public String getUUID() {
        return UUIDString;
    }

    public void setUUID(String UUIDString) {
        this.UUIDString = UUIDString;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
