package at.tobinio.dockermanager;

import at.tobinio.dockermanager.container.Container;
import at.tobinio.dockermanager.container.ContainerService;
import at.tobinio.dockermanager.container.ContainerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class DockerManagerApplication {

    public static void main(String[] args) {

        SpringApplication.run(DockerManagerApplication.class, args);

    }

}
