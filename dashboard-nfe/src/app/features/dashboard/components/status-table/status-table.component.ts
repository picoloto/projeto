import { Component, OnInit, Input } from '@angular/core';
import { StatusNfe } from 'src/app/features/dashboard/models/status-nfe';
import { FormataStatus } from 'src/app/core/utils/formata-status';

@Component({
  selector: 'app-status-table',
  templateUrl: './status-table.component.html',
  styleUrls: ['./status-table.component.sass']
})
export class StatusTableComponent {

  @Input() statusAtualNfe: StatusNfe[] = [];

  verificaCorStatus(status) {
    return FormataStatus.retornaCor(status);
  }

  verificaTitleStatus(status) {
    return FormataStatus.retornaTitle(status);
  }
}
