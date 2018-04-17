import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class HueService {

  headers = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });

  url = '//192.168.1.17';

  constructor(private http: HttpClient) {
  }

  turnHueOn() {
    let lightOn = { "on": true, "light": 3 };
    this.http.post(this.url+':8080/hue/api/v1/lights', lightOn, { headers: this.headers }).subscribe(r => { });
  }

  turnHueOff() {
    let lightOff = { "on": false, "light": 3 }
    this.http.post(this.url+':8080/hue/api/v1/lights', lightOff, { headers: this.headers }).subscribe(r => { });
  }
}
