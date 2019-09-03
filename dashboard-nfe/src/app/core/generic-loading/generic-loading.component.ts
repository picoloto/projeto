import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-generic-loading',
  templateUrl: './generic-loading.component.html',
  styleUrls: ['./generic-loading.component.sass']
})
export class GenericLoadingComponent {

  @Input() width = '50px';
  @Input() height = '50px';
}
