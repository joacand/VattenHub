import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable()
export class RestConfiguration {
    public Server = environment.backendServer;
    public HueApiUrl = 'hue/api/v1/';
    public ServerWithHueApiUrl = this.Server + this.HueApiUrl;
}