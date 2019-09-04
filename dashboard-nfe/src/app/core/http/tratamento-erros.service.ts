import { ErrorHandler, Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { MessageService } from 'primeng/components/common/messageservice';

@Injectable()
export class TratamentoErrosService implements ErrorHandler {
    tipoGrow: string;
    tituloGrow: string;
    mensagemGrow: string;

    constructor(
        private messageService: MessageService,
    ) { }

    getError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            this.handleError(error);
            return of(result as T);
        };
    }

    handleError(error: Error | HttpErrorResponse) {
        this.clearGrowl();
        if (error instanceof HttpErrorResponse) {
            if (!navigator.onLine) {
                // Navegador offline
                this.tipoGrow = 'error';
                this.tituloGrow = 'Falha na conexão com a internet';
                this.mensagemGrow = 'Por gentileza, verifique sua conexão.';
            } else if (error.status === 400) {
                // (error.status === 400)
                this.tipoGrow = 'error';
                this.tituloGrow = 'Ocorreu um erro';
                this.mensagemGrow = error.error.mensagem ? error.error.mensagem : error.error;
            } else if (error.status === 404) {
                // (error.status === 404)
                this.tipoGrow = 'error';
                this.tituloGrow = 'Registro não encontrado';
                this.mensagemGrow = error.error;
            } else {
                // (error.status === 403...)
                this.tipoGrow = 'error';
                this.tituloGrow = 'Falha na conexão com o servidor';
                this.mensagemGrow = 'Tente novamente mais tarde.';
            }
        } else {
            // Outros erros
            this.tipoGrow = 'error';
            this.tituloGrow = 'Falha inesperada';
            this.mensagemGrow = 'Tente novamente mais tarde.';
        }

        // Exibe mensagem de erro
        this.clearGrowl();
        return this.showGrowl(this.tipoGrow, this.tituloGrow, this.mensagemGrow);
    }

    showGrowl(tipo, titulo, mensagem) {
        this.messageService.add({ severity: tipo, summary: titulo, detail: mensagem });
    }

    clearGrowl() {
        this.messageService.clear();
    }
}
