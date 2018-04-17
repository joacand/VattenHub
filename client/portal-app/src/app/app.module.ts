import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app.routing.module';

import { AppComponent } from './app.component';
import { HueComponent } from './hue/hue.component';

import { CustomMaterialModule } from "./material.module";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HueService } from './hue/hue.service';

import { HttpClient } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HueComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CustomMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [HueService],
  bootstrap: [AppComponent]
})
export class AppModule { }
