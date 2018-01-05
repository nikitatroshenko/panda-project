import React from 'react';
import PropTypes from 'prop-types';
import './Page404.css';

export default function Page404({ history }) {
  const returnPage = () => history.push({ pathname: '/' });
  return (
    <div className="page-404">
      <div className="page-404__error">404</div>
      <div className="page-404__description">Not Found</div>
      <div
        className="page-404__return"
        onClick={returnPage}
      >
        Return
      </div>
    </div>
  );
}

Page404.propTypes = {
  history: PropTypes.object.isRequired,
};
