import { IEvento } from '@/shared/model/eventos/evento.model';
import { IArea } from '@/shared/model/eventos/area.model';

export interface ICamara {
  id?: number;
  descricao?: string;
  estado?: boolean;
  enderecoIp?: string;
  enderecoMac?: string;
  eventos?: IEvento[];
  areas?: IArea[];
}

export class Camara implements ICamara {
  constructor(
    public id?: number,
    public descricao?: string,
    public estado?: boolean,
    public enderecoIp?: string,
    public enderecoMac?: string,
    public eventos?: IEvento[],
    public areas?: IArea[]
  ) {
    this.estado = this.estado || false;
  }
}
