export class FormataStatus {

    public static retornaCor(status): string {
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

    public static retornaTitle(status): string {
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
