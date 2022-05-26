import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IEstabelecimento } from 'app/shared/model/estabelecimento.model';
import { getEntity, updateEntity, createEntity, reset } from './estabelecimento.reducer';

export const EstabelecimentoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const estabelecimentoEntity = useAppSelector(state => state.estabelecimento.entity);
  const loading = useAppSelector(state => state.estabelecimento.loading);
  const updating = useAppSelector(state => state.estabelecimento.updating);
  const updateSuccess = useAppSelector(state => state.estabelecimento.updateSuccess);
  const handleClose = () => {
    props.history.push('/estabelecimento');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...estabelecimentoEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...estabelecimentoEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="plusTesteApp.estabelecimento.home.createOrEditLabel" data-cy="EstabelecimentoCreateUpdateHeading">
            Create or edit a Estabelecimento
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField name="id" required readOnly id="estabelecimento-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField label="Id Local" id="estabelecimento-idLocal" name="idLocal" data-cy="idLocal" type="text" />
              <ValidatedField label="Object Id" id="estabelecimento-objectId" name="objectId" data-cy="objectId" type="text" />
              <ValidatedField label="Name" id="estabelecimento-name" name="name" data-cy="name" type="text" />
              <ValidatedField label="Phone 1" id="estabelecimento-phone1" name="phone1" data-cy="phone1" type="text" />
              <ValidatedField label="Place Id" id="estabelecimento-placeId" name="placeId" data-cy="placeId" type="text" />
              <ValidatedField label="Types" id="estabelecimento-types" name="types" data-cy="types" type="text" />
              <ValidatedField label="Validated By" id="estabelecimento-validatedBy" name="validatedBy" data-cy="validatedBy" type="text" />
              <ValidatedField label="Vicinity" id="estabelecimento-vicinity" name="vicinity" data-cy="vicinity" type="text" />
              <ValidatedField label="Vaga" id="estabelecimento-vaga" name="vaga" data-cy="vaga" type="text" />
              <ValidatedField label="Foto" id="estabelecimento-foto" name="foto" data-cy="foto" type="text" />
              <ValidatedField label="Nota" id="estabelecimento-nota" name="nota" data-cy="nota" type="text" />
              <ValidatedField label="Lat 2 Lng 2" id="estabelecimento-lat2lng2" name="lat2lng2" data-cy="lat2lng2" type="text" />
              <ValidatedField
                label="Has Chair Wheel"
                id="estabelecimento-hasChairWheel"
                name="hasChairWheel"
                data-cy="hasChairWheel"
                type="text"
              />
              <ValidatedField
                label="Has Handicapped Help"
                id="estabelecimento-hasHandicappedHelp"
                name="hasHandicappedHelp"
                data-cy="hasHandicappedHelp"
                type="text"
              />
              <ValidatedField
                label="Has Interpreter"
                id="estabelecimento-hasInterpreter"
                name="hasInterpreter"
                data-cy="hasInterpreter"
                type="text"
              />
              <ValidatedField
                label="Has Special Furniture"
                id="estabelecimento-hasSpecialFurniture"
                name="hasSpecialFurniture"
                data-cy="hasSpecialFurniture"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/estabelecimento" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default EstabelecimentoUpdate;
