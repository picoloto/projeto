import { Component, OnInit } from '@angular/core';
import { DashboardService } from './services/dashboard.service';
import { Title } from '@angular/platform-browser';
import { CALENDARPTBR } from 'src/app/core/utils/calendar-ptbr';
import * as moment from 'moment';
import 'moment/locale/pt-br';
import { FormataStatus } from 'src/app/core/utils/formata-status';
import { TratamentoErrosService } from 'src/app/core/http/tratamento-erros.service';

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
  indisponibilidades = [];

  // Filtro de estados
  estados = [];

  // historico
  statusHistoricoNfe = [];
  carregandoHistoricoNfe = false;
  modalHistorico = false;
  ptbr = CALENDARPTBR;

  constructor(
    private dashboardService: DashboardService,
    private tratamentoErrosService: TratamentoErrosService,
    private titleService: Title
  ) {
  }

  ngOnInit() {
    this.setTituloAbaNavegador('Dashboard NFe');
    this.getStatusAtualNfe(null);
    this.getIndisponibilidade();
    this.getAutorizadores();
  }

  setTituloAbaNavegador(titulo: string) {
    this.titleService.setTitle(titulo);
  }

  recarregarNfeAtualClick() {
    this.getStatusAtualNfe(null);
    this.getAutorizadores();
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
        this.tratamentoErrosService.handleError(error);
        this.carregandoStatusAtualNfe = false;
      });
  }

  getIndisponibilidade() {
    this.carregandoIndisponibilidade = true;
    this.dashboardService.getIndisponibilidade()
      .subscribe(indisponibilidades => {
        this.indisponibilidades = [];
        if (indisponibilidades && indisponibilidades.length > 0) {
          indisponibilidades.forEach(i => {
            this.indisponibilidades.push({ uf: i[0], quantidade: i[1] });
          });
        }
        this.carregandoIndisponibilidade = false;
      }, error => {
        this.tratamentoErrosService.handleError(error);
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
        this.tratamentoErrosService.handleError(error);
      });
  }

  onChangeEstado(e) {
    if (e.value) {
      this.getStatusAtualNfe(e.value);
    } else {
      this.getStatusAtualNfe(null);
    }
  }

  exibeModalHistorico() {
    this.modalHistorico = true;
  }

  onSelectData(e) {
    this.carregandoHistoricoNfe = true;
    this.statusHistoricoNfe = [];
    this.dashboardService.getStatusNfePorDataHora(moment(e).format('YYYY-MM-DD'))
      .subscribe(nfes => {
        this.statusHistoricoNfe = nfes;
        this.carregandoHistoricoNfe = false;
      }, error => {
        this.tratamentoErrosService.handleError(error);
        this.carregandoHistoricoNfe = false;
      });
  }

  verificaCorStatus(status) {
    return FormataStatus.retornaCor(status);
  }

  verificaTitleStatus(status) {
    return FormataStatus.retornaTitle(status);
  }
}
