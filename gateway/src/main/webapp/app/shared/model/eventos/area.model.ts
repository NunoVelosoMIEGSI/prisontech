import { IEvento } from '@/shared/model/eventos/evento.model';
import { ICamara } from '@/shared/model/eventos/camara.model';

export interface IArea {
  id?: number;
  nome?: string;
  numPessoasPerm?: number;
  limiteArea?: number;
  eventos?: IEvento[];
  camara?: ICamara;
}

export class Area implements IArea {
  constructor(
    public id?: number,
    public nome?: string,
    public numPessoasPerm?: number,
    public limiteArea?: number,
    public eventos?: IEvento[],
    public camara?: ICamara
  ) {}
}
