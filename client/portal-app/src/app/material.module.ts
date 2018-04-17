import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatToolbarModule } from '@angular/material';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MatCheckboxModule } from '@angular/material/checkbox';

@NgModule({
    imports: [CommonModule,
        MatToolbarModule,
        MatButtonModule,
        MatCardModule,
        MatIconModule,
        MatDividerModule,
        MatListModule,
        MatCheckboxModule],
    exports: [CommonModule,
        MatToolbarModule,
        MatButtonModule,
        MatCardModule,
        MatIconModule,
        MatDividerModule,
        MatListModule,
        MatCheckboxModule],
})
export class CustomMaterialModule { }