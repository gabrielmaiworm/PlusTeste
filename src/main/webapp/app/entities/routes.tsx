import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Avaliacao from './avaliacao';
import Estabelecimento from './estabelecimento';
import InfoAdicional from './info-adicional';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default ({ match }) => {
  return (
    <div>
      <Switch>
        {/* prettier-ignore */}
        <ErrorBoundaryRoute path={`${match.url}avaliacao`} component={Avaliacao} />
        <ErrorBoundaryRoute path={`${match.url}estabelecimento`} component={Estabelecimento} />
        <ErrorBoundaryRoute path={`${match.url}info-adicional`} component={InfoAdicional} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </Switch>
    </div>
  );
};
