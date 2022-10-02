import {Component, OnInit} from '@angular/core';
import {Container, ContainerState} from "../container/container";
import {StompService} from "../stomp.service";
import {HttpClient} from "@angular/common/http";
import {Frame} from "stompjs";

@Component({
    selector: 'app-container-manager',
    templateUrl: './container-manager.component.html',
    styleUrls: ['./container-manager.component.scss']
})
export class ContainerManagerComponent implements OnInit {

    containers: Container[] = [];
    name: string = "";
    owner: string = "";

    constructor(private stompService: StompService, private http: HttpClient) {
    }

    ngOnInit(): void {
        this.stompService.subscribe("/topic/all-container", (data: Frame) => {
            this.containers = JSON.parse(data.body) as Container[];
        })

        this.stompService.send("/ws/all-container");
    }

    addContainer() {

        let requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
        };

        this.http.post("http://localhost:8080/api/add-container", {
            name: this.name,
            containerState: ContainerState.DOWNLOAD,
            owner: this.owner
        } as Container, requestOptions).subscribe()
    }

}
