# BrokerFrontEnd

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 9.1.8.

## MQTT

```
npm install ngx-mqtt
```

Run:
```
$ docker run -it --rm -p 1883:1883 -p 9001:9001 --name mosquitto eclipse-mosquitto:1.6.2
```

Exposes Port 1883 (MQTT) 9001 (Websocket MQTT)

https://medium.com/@anant.lalchandani/dead-simple-mqtt-example-over-websockets-in-angular-b9fd5ff17b8e

https://bugfender.com/blog/using-mqtt-on-angular-apps/


## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
