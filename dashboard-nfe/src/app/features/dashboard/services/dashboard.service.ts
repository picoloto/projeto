import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { StatusNfe } from '../models/status-nfe';

@Injectable()
export class DashboardService {

  api: string = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getStatusAtualNfe(): Observable<StatusNfe[]> {
    return this.http.get<StatusNfe[]>(`${this.api}`);
  }

  getAutorizadoresAntigos(): Observable<any> {
    return this.http.get<any>(`${this.api}autorizadoresVersaoAntiga`);
  }

  getAutorizadoresNovos(): Observable<any> {
    return this.http.get<any>(`${this.api}autorizadoresVersaoNova`);
  }

  getIndisponibilidade(): Observable<any> {
    return this.http.get<any>(`${this.api}maiorIndisponibilidade`);
  }

  getStatusAtualNfePorUf(): Observable<any> {
    const uf = 'AM';
    return this.http.get<any>(`${this.api}autorizadores?uf=${uf}`);
  }

  getStatusNfePorDataHora(): Observable<any> {
    const dataHora = '2019-09-01 15:35:00';
    return this.http.get<any>(`${this.api}dataHoraStatus?dataHora=${dataHora}`);
  }
}
