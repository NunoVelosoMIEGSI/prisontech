export interface ILogseventos {
  id?: number;
  dataHoraInicio?: Date;
  dataHoraFim?: Date;
  classeOrigem?: string;
  metodoOrigem?: string;
}

export class Logseventos implements ILogseventos {
  constructor(
    public id?: number,
    public dataHoraInicio?: Date,
    public dataHoraFim?: Date,
    public classeOrigem?: string,
    public metodoOrigem?: string
  ) {}
}
