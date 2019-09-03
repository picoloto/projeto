import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GenericLoadingComponent } from 'src/app/core/generic-loading/generic-loading.component';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

@NgModule({
  declarations: [
    GenericLoadingComponent
  ],
  imports: [
    CommonModule,
    ProgressSpinnerModule,
  ],
  exports: [
    GenericLoadingComponent
  ],
  providers: [],
  bootstrap: [GenericLoadingComponent]
})
export class GenericLoadingModule { }
