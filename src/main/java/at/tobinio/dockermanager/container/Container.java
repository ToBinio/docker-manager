package at.tobinio.dockermanager.container;

/**
 * Created: 27.09.2022
 *
 * @author Tobias Frischmann
 */
public class Container {

    private String name;
    private ContainerState containerState;

    public Container(String name, ContainerState containerState) {
        this.name = name;
        this.containerState = containerState;
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
}
