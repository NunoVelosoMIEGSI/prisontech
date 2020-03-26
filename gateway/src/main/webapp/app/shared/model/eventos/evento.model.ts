import { IArea } from '@/shared/model/eventos/area.model';
import { ICamara } from '@/shared/model/eventos/camara.model';
import { ITipoevento } from '@/shared/model/eventos/tipoevento.model';

export interface IEvento {
  id?: number;
  descricao?: string;
  numPessoasPerm?: number;
  numPessoasDet?: number;
  dataHoraInicio?: Date;
  dataHoraFim?: Date;
  area?: IArea;
  camara?: ICamara;
  tipoevento?: ITipoevento;
}

export class Evento implements IEvento {
  constructor(
    public id?: number,
    public descricao?: string,
    public numPessoasPerm?: number,
    public numPessoasDet?: number,
    public dataHoraInicio?: Date,
    public dataHoraFim?: Date,
    public area?: IArea,
    public camara?: ICamara,
    public tipoevento?: ITipoevento
  ) {}
}
