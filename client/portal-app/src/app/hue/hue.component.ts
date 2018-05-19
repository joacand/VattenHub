import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomMaterialModule } from "./../material.module";
import { FormGroup, FormControl } from '@angular/forms';
import { FormBuilder, FormArray } from '@angular/forms';
import { HueService } from './hue.service';

@Component({
  selector: 'app-hue',
  templateUrl: './hue.component.html',
  styleUrls: ['./hue.component.css']
})
export class HueComponent implements OnInit {
  statusMessage = '';
  index = 0;
  lights: string[];

  public form: FormGroup = this.fb.group({
    checkboxes: this.fb.array([])
  });

  public actions: string[];

  public presets: string[];

  constructor(private router: Router, private fb: FormBuilder, private hueService: HueService) {
  }

  ngOnInit() {
    this.hueService.getLights().subscribe((data: string[]) => this.lights = data['lights'],
      error => () => { console.log('Error when getting lights from server', error) },
      () => {
        console.log('Successfully got lights: ', this.lights);

        for (let entry of this.lights) {
          console.log("Found light: ", entry);
          this.addLight(entry);
        }
      }
    );

    this.hueService.getActions().subscribe((data: string[]) => this.actions = data,
      error => () => { console.log('Error when getting actions from server', error) },
      () => { console.log('Successfully got actions: ', this.actions) });

    this.hueService.getPresets().subscribe((data: string[]) => this.presets = data.map(p => p['name']),
      error => () => { console.log('Error when getting presets from server', error) },
      () => { console.log('Successfully got presets: ', this.presets); });

  }

  turnHueOff() {
    this.changeHueLight(false);
  }

  turnHueOn() {
    this.changeHueLight(true);
  }

  changeHueLight(on: boolean) {
    let cbs = <FormArray>this.form.get('checkboxes');
    let lightsChanged: string = '';

    for (var _i = 0; _i < cbs.controls.length; _i++) {
      let cb = cbs.controls[_i];
      if (cb.value == true) {
        if (on) {
          this.hueService.turnHueOn(_i + 1);
        }
        else {
          this.hueService.turnHueOff(_i + 1);
        }
        lightsChanged = lightsChanged.concat(' ' + (_i + 1));
      }
    }

    this.statusMessage = 'Following Hue lights were changed: ' + lightsChanged;
  }

  addLight(id) {
    (<FormArray>this.form.get('checkboxes')).insert(id, new FormControl(false));
  }

  actionHue(action) {
    this.hueService.startAction(action);
    this.statusMessage = 'Started ' + action;
  }

  presetHue(preset) {
    this.hueService.startPreset(preset);
    this.statusMessage = 'Started ' + preset;
  }

}
