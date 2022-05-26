import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './estabelecimento.reducer';

export const EstabelecimentoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const estabelecimentoEntity = useAppSelector(state => state.estabelecimento.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="estabelecimentoDetailsHeading">Estabelecimento</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{estabelecimentoEntity.id}</dd>
          <dt>
            <span id="idLocal">Id Local</span>
          </dt>
          <dd>{estabelecimentoEntity.idLocal}</dd>
          <dt>
            <span id="objectId">Object Id</span>
          </dt>
          <dd>{estabelecimentoEntity.objectId}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{estabelecimentoEntity.name}</dd>
          <dt>
            <span id="phone1">Phone 1</span>
          </dt>
          <dd>{estabelecimentoEntity.phone1}</dd>
          <dt>
            <span id="placeId">Place Id</span>
          </dt>
          <dd>{estabelecimentoEntity.placeId}</dd>
          <dt>
            <span id="types">Types</span>
          </dt>
          <dd>{estabelecimentoEntity.types}</dd>
          <dt>
            <span id="validatedBy">Validated By</span>
          </dt>
          <dd>{estabelecimentoEntity.validatedBy}</dd>
          <dt>
            <span id="vicinity">Vicinity</span>
          </dt>
          <dd>{estabelecimentoEntity.vicinity}</dd>
          <dt>
            <span id="vaga">Vaga</span>
          </dt>
          <dd>{estabelecimentoEntity.vaga}</dd>
          <dt>
            <span id="foto">Foto</span>
          </dt>
          <dd>{estabelecimentoEntity.foto}</dd>
          <dt>
            <span id="nota">Nota</span>
          </dt>
          <dd>{estabelecimentoEntity.nota}</dd>
          <dt>
            <span id="lat2lng2">Lat 2 Lng 2</span>
          </dt>
          <dd>{estabelecimentoEntity.lat2lng2}</dd>
          <dt>
            <span id="hasChairWheel">Has Chair Wheel</span>
          </dt>
          <dd>{estabelecimentoEntity.hasChairWheel}</dd>
          <dt>
            <span id="hasHandicappedHelp">Has Handicapped Help</span>
          </dt>
          <dd>{estabelecimentoEntity.hasHandicappedHelp}</dd>
          <dt>
            <span id="hasInterpreter">Has Interpreter</span>
          </dt>
          <dd>{estabelecimentoEntity.hasInterpreter}</dd>
          <dt>
            <span id="hasSpecialFurniture">Has Special Furniture</span>
          </dt>
          <dd>{estabelecimentoEntity.hasSpecialFurniture}</dd>
        </dl>
        <Button tag={Link} to="/estabelecimento" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/estabelecimento/${estabelecimentoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default EstabelecimentoDetail;
