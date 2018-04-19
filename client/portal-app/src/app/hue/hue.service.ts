import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class HueService {

  headers = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });

  url = '//192.168.1.17';

  constructor(private http: HttpClient) {
  }

  turnHueOn(light) {
    let lightOn = { "on": true, "light": light };
    this.http.post(this.url + ':8080/hue/api/v1/lights', lightOn, { headers: this.headers }).subscribe(r => { });
  }

  turnHueOff(light) {
    let lightOff = { "on": false, "light": light }
    this.http.post(this.url + ':8080/hue/api/v1/lights', lightOff, { headers: this.headers }).subscribe(r => { });
  }
}
