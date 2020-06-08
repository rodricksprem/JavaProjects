
import * as moment from 'moment';
import { AbstractControl } from '@angular/forms';

export class CustomValidator {
    static dateVaidator(AC: AbstractControl) {
      if (AC && AC.value && !moment(AC.value, 'YYYY-MM-DD',true).isValid()) {
          console.log("validator is true");
        return {'dateVaidator': true};
      }
      return null;
    }
  }