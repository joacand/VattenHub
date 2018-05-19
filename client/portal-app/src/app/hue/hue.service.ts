import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { RestConfiguration } from '../common/rest.configuration';

@Injectable()
export class HueService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });

  private restLightsUrl: string;
  private restLightActionsUrl: string;
  private restLightPresetsUrl: string;

  constructor(private http: HttpClient, private restConfiguration: RestConfiguration) {
    this.restLightsUrl = this.restConfiguration.ServerWithHueApiUrl + 'lights';
    this.restLightActionsUrl = this.restConfiguration.ServerWithHueApiUrl + 'action';
    this.restLightPresetsUrl = this.restConfiguration.ServerWithHueApiUrl + 'preset';
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
    console.log('Trying to get lights...');
    return this.http.get<string[]>(this.restLightsUrl, { headers: this.headers });
  }

  startAction(action) {
    console.log('Trying to start action', action);
    let actionObj = { "actionName": action }
    this.http.post(this.restLightActionsUrl, actionObj, { headers: this.headers }).subscribe(r => { });
  }

  getActions() {
    console.log('Fetching actions');
    return this.http.get<string[]>(this.restLightActionsUrl, { headers: this.headers });
  }

  getPresets(): Observable<string[]> {
    console.log('Fetching presets');
    return this.http.get<string[]>(this.restLightPresetsUrl, { headers: this.headers });
  }

  startPreset(preset) {
    console.log('Trying to start preset', preset);
    this.http.post(this.restLightPresetsUrl, preset, { headers: this.headers }).subscribe(r => { });
  }
}
