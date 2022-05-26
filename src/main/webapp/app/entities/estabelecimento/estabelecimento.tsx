import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IEstabelecimento } from 'app/shared/model/estabelecimento.model';
import { getEntities } from './estabelecimento.reducer';

export const Estabelecimento = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const estabelecimentoList = useAppSelector(state => state.estabelecimento.entities);
  const loading = useAppSelector(state => state.estabelecimento.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="estabelecimento-heading" data-cy="EstabelecimentoHeading">
        Estabelecimentos
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to="/estabelecimento/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Estabelecimento
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {estabelecimentoList && estabelecimentoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Id Local</th>
                <th>Object Id</th>
                <th>Name</th>
                <th>Phone 1</th>
                <th>Place Id</th>
                <th>Types</th>
                <th>Validated By</th>
                <th>Vicinity</th>
                <th>Vaga</th>
                <th>Foto</th>
                <th>Nota</th>
                <th>Lat 2 Lng 2</th>
                <th>Has Chair Wheel</th>
                <th>Has Handicapped Help</th>
                <th>Has Interpreter</th>
                <th>Has Special Furniture</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {estabelecimentoList.map((estabelecimento, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/estabelecimento/${estabelecimento.id}`} color="link" size="sm">
                      {estabelecimento.id}
                    </Button>
                  </td>
                  <td>{estabelecimento.idLocal}</td>
                  <td>{estabelecimento.objectId}</td>
                  <td>{estabelecimento.name}</td>
                  <td>{estabelecimento.phone1}</td>
                  <td>{estabelecimento.placeId}</td>
                  <td>{estabelecimento.types}</td>
                  <td>{estabelecimento.validatedBy}</td>
                  <td>{estabelecimento.vicinity}</td>
                  <td>{estabelecimento.vaga}</td>
                  <td>{estabelecimento.foto}</td>
                  <td>{estabelecimento.nota}</td>
                  <td>{estabelecimento.lat2lng2}</td>
                  <td>{estabelecimento.hasChairWheel}</td>
                  <td>{estabelecimento.hasHandicappedHelp}</td>
                  <td>{estabelecimento.hasInterpreter}</td>
                  <td>{estabelecimento.hasSpecialFurniture}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/estabelecimento/${estabelecimento.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/estabelecimento/${estabelecimento.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/estabelecimento/${estabelecimento.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Estabelecimentos found</div>
        )}
      </div>
    </div>
  );
};

export default Estabelecimento;
