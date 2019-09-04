import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { StatusNfe } from '../models/status-nfe';

@Injectable()
export class DashboardService {

  api: string = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getStatusAtualNfe(uf: string): Observable<StatusNfe[]> {
    const url = uf ? `${this.api}autorizadores?uf=${uf}` : `${this.api}`;
    return this.http.get<StatusNfe[]>(`${url}`);
  }

  getAutorizadores(): Observable<any> {
    return this.http.get<any>(`${this.api}listaAutorizadores`);
  }

  getIndisponibilidade(): Observable<any> {
    return this.http.get<any>(`${this.api}maiorIndisponibilidade`);
  }

  getStatusNfePorDataHora(data): Observable<any> {
    return this.http.get<any>(`${this.api}dataHoraStatus?data=${data}`);
  }
}
