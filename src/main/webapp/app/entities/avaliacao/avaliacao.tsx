import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAvaliacao } from 'app/shared/model/avaliacao.model';
import { getEntities } from './avaliacao.reducer';

export const Avaliacao = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const avaliacaoList = useAppSelector(state => state.avaliacao.entities);
  const loading = useAppSelector(state => state.avaliacao.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="avaliacao-heading" data-cy="AvaliacaoHeading">
        Avaliacaos
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to="/avaliacao/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Avaliacao
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {avaliacaoList && avaliacaoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Comentario</th>
                <th>Nota</th>
                <th>Imagem</th>
                <th>Acesso Entrada Principal</th>
                <th>Acesso Banheiro</th>
                <th>Acesso Audio Visual</th>
                <th>Acesso Circulacao Interna</th>
                <th>Acesso Estacionamento</th>
                <th>Acesso Calcada</th>
                <th>Estabelecimento</th>
                <th>Info Adicional</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {avaliacaoList.map((avaliacao, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/avaliacao/${avaliacao.id}`} color="link" size="sm">
                      {avaliacao.id}
                    </Button>
                  </td>
                  <td>{avaliacao.comentario}</td>
                  <td>{avaliacao.nota}</td>
                  <td>
                    {avaliacao.imagem ? (
                      <div>
                        {avaliacao.imagemContentType ? (
                          <a onClick={openFile(avaliacao.imagemContentType, avaliacao.imagem)}>
                            <img src={`data:${avaliacao.imagemContentType};base64,${avaliacao.imagem}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {avaliacao.imagemContentType}, {byteSize(avaliacao.imagem)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{avaliacao.acessoEntradaPrincipal ? 'true' : 'false'}</td>
                  <td>{avaliacao.acessoBanheiro ? 'true' : 'false'}</td>
                  <td>{avaliacao.acessoAudioVisual ? 'true' : 'false'}</td>
                  <td>{avaliacao.acessoCirculacaoInterna ? 'true' : 'false'}</td>
                  <td>{avaliacao.acessoEstacionamento ? 'true' : 'false'}</td>
                  <td>{avaliacao.acessoCalcada ? 'true' : 'false'}</td>
                  <td>
                    {avaliacao.estabelecimento ? (
                      <Link to={`/estabelecimento/${avaliacao.estabelecimento.id}`}>{avaliacao.estabelecimento.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {avaliacao.infoAdicional ? (
                      <Link to={`/info-adicional/${avaliacao.infoAdicional.id}`}>{avaliacao.infoAdicional.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/avaliacao/${avaliacao.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`/avaliacao/${avaliacao.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`/avaliacao/${avaliacao.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Avaliacaos found</div>
        )}
      </div>
    </div>
  );
};

export default Avaliacao;
