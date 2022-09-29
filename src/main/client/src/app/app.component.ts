import {Component, OnInit} from '@angular/core';
import {StompService} from "./stomp.service";
import {Frame} from "stompjs";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
    pingResponse: string = "";
    pingMessage: string = "";

    containers: { name: string, containerState: ContainerState }[] = [];

    constructor(private stompService: StompService, private http: HttpClient) {
    }

    ngOnInit(): void {
        this.stompService.subscribe("/topic/ping", (data: Frame) => {
            console.log("?");
            this.pingResponse = data.body;
        })

        this.stompService.subscribe("/topic/all-container", (data: Frame) => {
            this.containers = JSON.parse(data.body) as { name: string, containerState: ContainerState }[];
        })
    }

    sendPing() {
        this.stompService.send("/ws/ping", {content: this.pingMessage})
    }

    requestContainer() {
        this.stompService.send("/ws/all-container");
    }

    addContainer() {

        let requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
        };

        this.http.post("http://localhost:8080/api/add-container", {
            name: "huff",
            containerState: ContainerState.BUILDING
        }, requestOptions).subscribe()
    }
}

enum ContainerState {
    RUNNING,
    BUILDING,
    HUFF
}
