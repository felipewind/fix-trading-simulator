import { Injectable } from "@angular/core";
import { IMqttMessage, MqttService } from "ngx-mqtt";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class OrderMqttService {

    // The topic that will be subscribed
    private endpoint: string;

    constructor(
        private _mqttService: MqttService,
    ) {
        this.endpoint = 'broker-orders';
    }

    topic(): Observable<IMqttMessage> {
        let topicName = `/${this.endpoint}`;
        return this._mqttService.observe(topicName);
    }
}