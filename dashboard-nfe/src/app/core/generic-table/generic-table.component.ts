import { Component, OnInit, Input } from '@angular/core';
import { StatusNfe } from 'src/app/features/dashboard/models/status-nfe';

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  styleUrls: ['./generic-table.component.sass']
})
export class GenericTableComponent {

  @Input() statusAtualNfe: StatusNfe[] = [];

  verificaCorStatus(status) {
    switch (status) {
      case ('NONE'):
        return '#616161';
      case ('OFF'):
        return '#FF5252';
      case ('ON'):
        return '#4CAF50';
      case ('WAIT'):
        return '#FFC107';
      default:
        return '#616161';
    }
  }

  verificaTitleStatus(status) {
    switch (status) {
      case ('NONE'):
        return 'Sem status';
      case ('OFF'):
        return 'Offline';
      case ('ON'):
        return 'Online';
      case ('WAIT'):
        return 'Instável';
      default:
        return 'Sem status';
    }
  }
}
