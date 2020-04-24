import { IEvento } from '@/shared/model/eventos/evento.model';
import { IArea } from '@/shared/model/eventos/area.model';

export interface ITipoevento {
  id?: number;
  descricao?: string;
  eventos?: IEvento[];
  areas?: IArea[];
}

export class Tipoevento implements ITipoevento {
  constructor(public id?: number, public descricao?: string, public eventos?: IEvento[], public areas?: IArea[]) {}
}
