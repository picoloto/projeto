import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { DashboardComponent } from './dashboard.component';

import { PanelModule } from 'primeng/panel';
import { ButtonModule } from 'primeng/button';
import { ChartModule } from 'primeng/chart';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { CalendarModule } from 'primeng/calendar';

import { DataHoraPipe } from 'src/app/core/pipes/data-hora.pipe';
import { GenericLoadingModule } from 'src/app/core/generic-loading/generic-loading.module';
import { StatusTableComponent } from './components/status-table/status-table.component';

@NgModule({
  declarations: [
    DashboardComponent,
    StatusTableComponent,
    DataHoraPipe
  ],
  imports: [
    CommonModule,
    PanelModule,
    ButtonModule,
    ChartModule,
    DropdownModule,
    TableModule,
    DialogModule,
    CalendarModule,
    GenericLoadingModule,
    RouterModule.forChild([
      { path: '', component: DashboardComponent }
    ])
  ],
  providers: [],
  bootstrap: [DashboardComponent]
})
export class DashboardModule { }
