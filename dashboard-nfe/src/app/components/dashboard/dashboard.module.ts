import { NgModule } from '@angular/core';

import { DashboardComponent } from './dashboard.component';

import { PanelModule } from 'primeng/panel';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    PanelModule,
    RouterModule.forChild([
      {path: '', component: DashboardComponent}
    ])
  ],
  exports: [
    PanelModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [DashboardComponent]
})
export class DashboardModule { }
