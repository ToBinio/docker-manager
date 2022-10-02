import {Component, Input} from '@angular/core';
import {Container, ContainerState} from "./container";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-container',
    templateUrl: './container.component.html',
    styleUrls: ['./container.component.scss']
})
export class ContainerComponent {
    @Input() container: Container = {name: "", containerState: ContainerState.RUNNING, uuid: "", owner: ""};

    constructor(private http: HttpClient) {
    }

    delete(): void {
        let requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
        };

        this.http.post("http://localhost:8080/api/remove-container", {uuid: this.container.uuid}, requestOptions).subscribe()
    }
}
