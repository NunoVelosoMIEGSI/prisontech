import { IEvento } from '@/shared/model/eventos/evento.model';

export interface ICamara {
  id?: number;
  descricao?: string;
  eventos?: IEvento[];
}

export class Camara implements ICamara {
  constructor(public id?: number, public descricao?: string, public eventos?: IEvento[]) {}
}
