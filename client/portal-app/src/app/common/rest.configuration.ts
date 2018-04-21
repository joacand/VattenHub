import { Injectable } from '@angular/core';

@Injectable()
export class RestConfiguration {
    public Server = '//192.168.1.17:8080/';
    public HueApiUrl = 'hue/api/v1/';
    public ServerWithHueApiUrl = this.Server + this.HueApiUrl;
}