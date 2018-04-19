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
  public form: FormGroup = this.fb.group({
    checkboxes: this.fb.array([new FormControl(false), new FormControl(false), new FormControl(false)])
  });

  constructor(private router: Router, private fb: FormBuilder, private hueService: HueService) {
  }

  ngOnInit() {
  }

  turnHueOff() {
    this.hueService.turnHueOff(3);
    this.statusMessage = 'Hue lights were turned off!';
  }

  turnHueOn() {
    this.hueService.turnHueOn(3);
    this.statusMessage = 'Hue lights were turned on!';
  }

  addLight(id) {
    (<FormArray>this.form.get('checkboxes')).insert(id, new FormControl(false));
  }

  discoModeHue() {
    this.statusMessage = 'Disco mode on!';
  }

}
