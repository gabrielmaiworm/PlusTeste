import { IAvaliacao } from 'app/shared/model/avaliacao.model';

export interface IEstabelecimento {
  id?: number;
  idLocal?: number | null;
  objectId?: string | null;
  name?: string | null;
  phone1?: string | null;
  placeId?: string | null;
  types?: string | null;
  validatedBy?: string | null;
  vicinity?: string | null;
  vaga?: string | null;
  foto?: string | null;
  nota?: number | null;
  lat2lng2?: string | null;
  hasChairWheel?: string | null;
  hasHandicappedHelp?: string | null;
  hasInterpreter?: string | null;
  hasSpecialFurniture?: string | null;
  avaliacaos?: IAvaliacao[] | null;
}

export const defaultValue: Readonly<IEstabelecimento> = {};
