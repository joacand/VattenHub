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

  hueSliderChanged(change) {
    let value = change.value;
    console.log("Setting light brightness to " + value)
    this.changeLightBri(value);
  }

  changeLightBri(value) {
    let lights = this.getLights();
    this.hueService.setHueBri(value, lights);
  }

  changeHueLight(on: boolean) {
    let cbs = <FormArray>this.form.get('checkboxes');
    let lightsChanged: string = '';

    let lights = this.getLights();

    if (on) {
      this.hueService.turnHueOn(lights);
    }
    else {
      this.hueService.turnHueOff(lights);
    }

    this.statusMessage = 'Following Hue lights were changed: ' + lightsChanged;
  }

  getLights(): number[] {
    let cbs = <FormArray>this.form.get('checkboxes');
    let lights: number[] = [];

    for (var _i = 0; _i < cbs.controls.length; _i++) {
      let cb = cbs.controls[_i];
      if (cb.value == true) {
        lights.push(_i + 1);
      }
    }

    this.statusMessage = 'Following Hue lights were calculated: ' + lights;
    return lights;
  }

  addLight(id) {
    (<FormArray>this.form.get('checkboxes')).insert(id, new FormControl(false));
  }

  actionHue(action) {
    let lights = this.getLights();
    this.hueService.startAction(action, lights);
    this.statusMessage = 'Started ' + action;
  }

  presetHue(preset) {
    this.hueService.startPreset(preset);
    this.statusMessage = 'Started ' + preset;
  }

}
