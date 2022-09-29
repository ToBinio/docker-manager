import {Injectable} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class StompService {

    stompClient = Stomp.over(new SockJS("http://localhost:8080/websocket"));

    connectionState: ConnectionState = ConnectionState.NONE
    subscriptionsList: { topic: string, callback: any }[] = [];

    subscribe(topic: string, callback: any) {
        if (this.connectionState == ConnectionState.CONNECTED) {
            this.stompClient.subscribe(topic, callback)
        } else {

            this.subscriptionsList.push({topic, callback})

            if (this.connectionState == ConnectionState.NONE) {

                this.connectionState = ConnectionState.CONNECTING;

                this.stompClient.connect({}, (frame) => {
                    console.log(frame);

                    this.connectionState = ConnectionState.CONNECTED;

                    for (let subscriptionsListElement of this.subscriptionsList) {
                        this.subscribe(subscriptionsListElement.topic, subscriptionsListElement.callback)
                    }
                })
            }
        }
    }

    send(destination: string, body: any = {}) {
        this.stompClient.send(destination, {}, JSON.stringify(body));
    }
}

enum ConnectionState {
    NONE, CONNECTING, CONNECTED
}
