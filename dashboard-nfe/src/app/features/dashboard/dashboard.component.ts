import { Component, OnInit } from '@angular/core';
import { DashboardService } from './services/dashboard.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.sass'],
  providers: [DashboardService]
})
export class DashboardComponent implements OnInit {

  // Status atual
  dataHoraConsulta = '';
  statusAtualNfeAntiga = [];
  numeroVersaoAntiga = '3.10';
  statusAtualNfeNova = [];
  numeroVersaoNova = '4.00';
  carregandoStatusAtualNfe = false;

  // Indisponibilidade
  carregandoIndisponibilidade = false;
  indisponibilidades;
  opcoesGrafico;
  coresGrafico = [];

  // Filtro de estados
  estados = [];

  constructor(
    private dashboardService: DashboardService,
    private titleService: Title
  ) {
  }

  ngOnInit() {
    this.setTituloAbaNavegador('Dashboard NFe');
    this.montaOpcoesGrafico();
    this.montaCoresGrafico();
    this.getStatusAtualNfe(null);
    this.getIndisponibilidade();
    this.getAutorizadores();


    /*
    this.dashboardService.getStatusAtualNfePorUf()
      .subscribe(r => console.log('Status Atual por UF(AM): ', r));

    this.dashboardService.getStatusNfePorDataHora()
      .subscribe(r => console.log('Status por DataHora(2019-09-01 15:35:00): ', r));*/
  }

  setTituloAbaNavegador(titulo: string) {
    this.titleService.setTitle(titulo);
  }

  recarregarNfeAtualClick() {
    this.getStatusAtualNfe(null);
    this.getAutorizadores();
  }

  montaOpcoesGrafico() {
    this.opcoesGrafico = {
      legend: {
        position: 'bottom'
      }
    };
  }

  montaCoresGrafico() {
    this.coresGrafico = [
      '#ff5252',
      '#404040',
      '#484848',
      '#505050',
      '#585858',
      '#606060',
      '#686868',
      '#696969',
      '#707070',
      '#787878',
      '#808080',
      '#888888',
      '#909090',
      '#989898',
      '#A0A0A0'
    ];
  }

  getStatusAtualNfe(uf) {
    this.carregandoStatusAtualNfe = true;
    this.dashboardService.getStatusAtualNfe(uf)
      .subscribe(nfes => {
        this.statusAtualNfeAntiga = [];
        this.statusAtualNfeNova = [];
        if (nfes && nfes.length > 0) {
          this.dataHoraConsulta = nfes[0].dataHoraStatus;
          nfes.forEach(nfe => {
            if (nfe.versao === this.numeroVersaoAntiga) {
              this.statusAtualNfeAntiga.push(nfe);
            } else {
              this.statusAtualNfeNova.push(nfe);
            }
          });
        }
        this.carregandoStatusAtualNfe = false;
      }, error => {
        alert(error);
        this.carregandoStatusAtualNfe = false;
      });
  }

  getIndisponibilidade() {
    this.carregandoIndisponibilidade = true;
    this.dashboardService.getIndisponibilidade()
      .subscribe(indisponibilidades => {
        const labelsGrafico = [];
        const datasetsGrafico = [];
        const dataGrafico = [];
        if (indisponibilidades && indisponibilidades.length > 0) {
          indisponibilidades.forEach(i => {
            labelsGrafico.push(i[0]);
            dataGrafico.push(i[1]);
          });
          datasetsGrafico.push({ data: dataGrafico, backgroundColor: this.coresGrafico });
        }
        this.indisponibilidades = {
          datasets: datasetsGrafico,
          labels: labelsGrafico
        };
        this.carregandoIndisponibilidade = false;
      }, error => {
        alert(error);
        this.carregandoIndisponibilidade = false;
      });
  }

  getAutorizadores() {
    this.estados = [];
    this.dashboardService.getAutorizadores()
      .subscribe(autorizadores => {
        if (autorizadores && autorizadores.length > 0) {
          autorizadores.forEach(a => {
            this.estados.push({ label: a, value: a });
          });
        }
      }, error => {
        alert(error);
      });
  }

  onChangeEstado(e) {
    if (e.value) {
      this.getStatusAtualNfe(e.value);
    } else {
      this.getStatusAtualNfe(null);
    }
  }

}
