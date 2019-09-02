import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { CommonModule } from '@angular/common';

import { PanelModule } from 'primeng/panel';
import { TableModule } from 'primeng/table';

import { GenericTableComponent } from 'src/app/core/generic-table/generic-table.component';


@NgModule({
  declarations: [
    DashboardComponent,
    GenericTableComponent
  ],
  imports: [
    CommonModule,
    PanelModule,
    TableModule,
    RouterModule.forChild([
      { path: '', component: DashboardComponent }
    ])
  ],
  exports: [
    PanelModule,
    TableModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [DashboardComponent]
})
export class DashboardModule { }
