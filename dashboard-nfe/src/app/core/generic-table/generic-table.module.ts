import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GenericTableComponent } from 'src/app/core/generic-table/generic-table.component';
import { TableModule } from 'primeng/table';

@NgModule({
  declarations: [
    GenericTableComponent
  ],
  imports: [
    CommonModule,
    TableModule,
  ],
  exports: [
    GenericTableComponent
  ],
  providers: [],
  bootstrap: [GenericTableComponent]
})
export class GenericTableModule { }
