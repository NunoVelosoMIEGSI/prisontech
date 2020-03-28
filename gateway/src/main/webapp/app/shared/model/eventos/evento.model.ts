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
  path?: string;
  formato?: string;
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
    public path?: string,
    public formato?: string,
    public area?: IArea,
    public camara?: ICamara,
    public tipoevento?: ITipoevento
  ) {}
}
