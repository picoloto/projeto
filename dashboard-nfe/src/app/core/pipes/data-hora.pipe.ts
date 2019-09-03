import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';
import 'moment/locale/pt-br';

@Pipe({ name: 'dataHora' })
export class DataHoraPipe implements PipeTransform {
    transform(value: string): string {
        if (value) {
            if (value.length === 10) {
                return moment(value).format('DD/MM/YYYY');
            } else if (value.length >= 16 && value.length <= 19) {
                return moment(value).format('DD/MM/YYYY HH:mm:ss');
            } else {
                return 'Data inválida';
            }
        }
        return '';
    }
}
