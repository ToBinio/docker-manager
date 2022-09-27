import {Injectable} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Injectable({
    providedIn: 'root'
})
export class StompService {

    stompClient = Stomp.over(new SockJS("http://localhost:8080/websocket"));
    isConnected: boolean = false;

    subscribe(topic: string, callback: any) {
        if (this.isConnected) {
            this.stompClient.subscribe(topic, callback)
        } else {
            this.stompClient.connect({}, (frame) => {
                console.log(frame);
                this.isConnected = true;

                this.subscribe(topic, callback)
            })
        }
    }

    send(destination: string, body: any) {
        this.stompClient.send(destination, {}, JSON.stringify(body));
    }
}
