import { IEvento } from '@/shared/model/eventos/evento.model';

export interface IArea {
  id?: number;
  nome?: string;
  numMinPessoa?: number;
  numMaxPessoa?: number;
  limiteArea?: number;
  eventos?: IEvento[];
}

export class Area implements IArea {
  constructor(
    public id?: number,
    public nome?: string,
    public numMinPessoa?: number,
    public numMaxPessoa?: number,
    public limiteArea?: number,
    public eventos?: IEvento[]
  ) {}
}
