import React, { Component } from 'react';
import PropTypes from 'prop-types';
import SvgIcon from 'react-inlinesvg';
import Header from './components/Header';

class Project extends Component {
  static propTypes = {
    history: PropTypes.object.isRequired,
  }

  render() {
    return (
      <div>
        <Header history={this.props.history} />
      </div>
    );
  }
}

export default Project;
