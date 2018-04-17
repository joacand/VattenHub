import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HueComponent } from './hue/hue.component';

const routes: Routes = [
    { path: 'hue', component: HueComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ],
    declarations: []
})
export class AppRoutingModule { }