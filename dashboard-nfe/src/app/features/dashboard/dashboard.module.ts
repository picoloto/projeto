import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { DashboardComponent } from './dashboard.component';

import { PanelModule } from 'primeng/panel';
import { ButtonModule } from 'primeng/button';
import { ChartModule } from 'primeng/chart';
import { DropdownModule } from 'primeng/dropdown';

import { GenericLoadingModule } from 'src/app/core/generic-loading/generic-loading.module';
import { GenericTableModule } from 'src/app/core/generic-table/generic-table.module';
import { DataHoraPipe } from 'src/app/core/pipes/data-hora.pipe';

@NgModule({
  declarations: [
    DashboardComponent,
    DataHoraPipe
  ],
  imports: [
    CommonModule,
    PanelModule,
    ButtonModule,
    ChartModule,
    DropdownModule,
    GenericLoadingModule,
    GenericTableModule,
    RouterModule.forChild([
      { path: '', component: DashboardComponent }
    ])
  ],
  providers: [],
  bootstrap: [DashboardComponent]
})
export class DashboardModule { }
