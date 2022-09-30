import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { ContainerComponent } from './container/container.component';
import { ContainerManagerComponent } from './container-manager/container-manager.component';

@NgModule({
    declarations: [
        AppComponent,
        ContainerComponent,
        ContainerManagerComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
