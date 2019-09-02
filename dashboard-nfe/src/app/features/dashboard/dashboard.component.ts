import { Component, OnInit } from '@angular/core';
import { DashboardService } from './services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.sass'],
  providers: [DashboardService]
})
export class DashboardComponent implements OnInit {
  title = 'dashboard-nfe';

  statusAtualNfe = [];

  constructor(
    private dashboardService: DashboardService
  ) {
  }

  ngOnInit() {
    this.dashboardService.getStatusAtualNfe()
      .subscribe(r => {
        this.statusAtualNfe = r;
      }, error => alert(error));

    /*this.dashboardService.getAutorizadoresAntigos()
      .subscribe(r => console.log('Autorizadores Antigos: ', r));

    this.dashboardService.getAutorizadoresNovos()
      .subscribe(r => console.log('Autorizadores novos: ', r));

    this.dashboardService.getIndisponibilidade()
      .subscribe(r => console.log('Indisponibilidade: ', r));

    this.dashboardService.getStatusAtualNfePorUf()
      .subscribe(r => console.log('Status Atual por UF(AM): ', r));

    this.dashboardService.getStatusNfePorDataHora()
      .subscribe(r => console.log('Status por DataHora(2019-09-01 15:35:00): ', r));*/
  }

}
