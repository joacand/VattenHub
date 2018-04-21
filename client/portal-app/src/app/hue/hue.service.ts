import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { RestConfiguration } from '../common/rest.configuration';

@Injectable()
export class HueService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });

  private restLightsUrl: string;

  constructor(private http: HttpClient, private restConfiguration: RestConfiguration) {
    this.restLightsUrl = this.restConfiguration.ServerWithHueApiUrl + 'lights';
  }

  turnHueOn(light) {
    let lightOn = { "on": true, "light": light };
    this.http.post(this.restLightsUrl, lightOn, { headers: this.headers }).subscribe(r => { });
  }

  turnHueOff(light) {
    let lightOff = { "on": false, "light": light }
    this.http.post(this.restLightsUrl, lightOff, { headers: this.headers }).subscribe(r => { });
  }

  getLights(): Observable<string[]> {
    console.log('Tring to get lights...');
    return this.http.get<string[]>(this.restLightsUrl, { headers: this.headers });
  }
}
