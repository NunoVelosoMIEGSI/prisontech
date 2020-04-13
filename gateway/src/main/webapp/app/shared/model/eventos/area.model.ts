import { IEvento } from '@/shared/model/eventos/evento.model';
import { ICamara } from '@/shared/model/eventos/camara.model';

export interface IArea {
  id?: number;
  nome?: string;
  numMinPessoa?: number;
  numMaxPessoa?: number;
  limiteArea?: number;
  eventos?: IEvento[];
  camara?: ICamara;
}

export class Area implements IArea {
  constructor(
    public id?: number,
    public nome?: string,
    public numMinPessoa?: number,
    public numMaxPessoa?: number,
    public limiteArea?: number,
    public eventos?: IEvento[],
    public camara?: ICamara
  ) {}
}
