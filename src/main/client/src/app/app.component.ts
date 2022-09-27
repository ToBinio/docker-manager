import {Component, OnInit} from '@angular/core';
import {StompService} from "./stomp.service";
import {Frame} from "stompjs";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    stompService;
    pingResponse: string = "";
    pingMessage: string = "";

    constructor(stompService: StompService) {
        this.stompService = stompService;
    }

    ngOnInit(): void {
        this.stompService.subscribe("/topic/ping", (data: Frame) => {
            this.pingResponse = data.body;
        })
    }

    sendPing() {
        this.stompService.send("/ws/ping", {content: this.pingMessage})
    }

}
