import { IEvento } from '@/shared/model/eventos/evento.model';

export interface ITipoevento {
  id?: number;
  descricao?: string;
  eventos?: IEvento[];
}

export class Tipoevento implements ITipoevento {
  constructor(public id?: number, public descricao?: string, public eventos?: IEvento[]) {}
}
