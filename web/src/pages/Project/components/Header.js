import React, { Component } from 'react';
import PropTypes from 'prop-types';
import SvgIcon from 'react-inlinesvg';
import PlusIcon from './../../../images/plus.svg';
import './Header.css';

class Header extends Component {
  static propTypes = {
    history: PropTypes.object.isRequired,
  }

  onNavigateToProject = () => {
    this.props.history.push({ pathname: '/' });
  }

  render() {

    return (
      <div className="header">
        <div className="header__link">
          <SvgIcon src={PlusIcon} />
        </div>
        <div
          className="header__link"
          onClick={this.onNavigateToProject}
        >
          Task Bar
        </div>
      </div>
    );
  }
}

export default Header;
