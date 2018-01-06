import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './Login.css';

export default class Login extends Component {
  static propTypes = {
    history: PropTypes.object.isRequired,
  }

  state = {
    username: '',
    password: '',
    error: '',
    users: [],
  }

  magic = '123456';

  componentWillMount() {
    const headers = new Headers();
    headers.append('Content-type', 'application/json');
    fetch('https://hci-panda-project.herokuapp.com/users', {
      method: 'GET',
      headers,
      mode: 'cors',
    })
    .then(response => response.json())
    .then(body => {
      this.setState({ users: body })
    });
  }

  onChangeUsername = (e) => {
    this.setState({
      username: e.target.value,
    });
  }

  onChangePassword = (e) => {
    this.setState({
      password: e.target.value,
    });
  }

  onClickLogin = () => {
    const user = this.state.users.find(user => user.username === this.state.username);
    if (user) {
      if (this.state.password === this.magic) {
        window.localStorage.setItem('id', user.id);
        this.props.history.push({ pathname: '/' });
      } else {
        this.setState({ error: 'Invalid password!' });
      }
    } else {
      this.setState({ error: 'User with such username doesn\'t exist!' });
    }
  }

  onClickSignup = () => this.props.history.push({ pathname: '/signup' });

  render() {
    return (
      <div className="login">
        <input
          value={this.state.username}
          onChange={this.onChangeUsername}
          className="login__input"
          placeholder="Username"
        />
        <input
          value={this.state.password}
          onChange={this.onChangePassword}
          className="login__input"
          placeholder="Password"
          type="password"
        />
        <div className="login__button" onClick={this.onClickLogin}>
          Let me in
        </div>
        <div className="login__signup" onClick={this.onClickSignup}>Sign up</div>
        {this.state.error && (
          <div className="login__error">{this.state.error}</div>
        )}
      </div>
    );
  }
}
