import { IArea } from '@/shared/model/eventos/area.model';
import { IEvento } from '@/shared/model/eventos/evento.model';

export interface ICamara {
  id?: number;
  descricao?: string;
  estado?: boolean;
  areas?: IArea[];
  eventos?: IEvento[];
}

export class Camara implements ICamara {
  constructor(public id?: number, public descricao?: string, public estado?: boolean, public areas?: IArea[], public eventos?: IEvento[]) {
    this.estado = this.estado || false;
  }
}
