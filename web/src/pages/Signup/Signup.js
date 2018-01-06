import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './Signup.css';

export default class Signup extends Component {
  static propTypes = {
    history: PropTypes.object.isRequired,
  }

  state = {
    username: '',
    password: '',
    role: 'Developer',
    error: '',
    users: [],
    user: null,
  }

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
      this.setState({ users: body || [] })
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

  onClickDeveloperRole = () => {
    this.setState({ role: 'Developer' });
  }

  onClickManagerRole = () => {
    this.setState({ role: 'Manager' });
  }

  onClickSignup = () => {
    const user = this.state.users.find(user => user.username === this.state.username);
    if (!user) {
      let headers = new Headers();
      headers.append('Content-type', 'application/json');
      fetch('https://hci-panda-project.herokuapp.com/users', {
        method: 'POST',
        headers: headers,
        body: JSON.stringify({
          username: this.state.username,
          role: this.state.role,
        })
      })
      .then(response => {
        console.log(response);
        if (!response.ok) {
          alert('Failed to register!')
        } else {
          return fetch('https://hci-panda-project.herokuapp.com/users', {
            method: 'GET',
            headers,
            mode: 'cors',
          });
        }
      })
      .then(response => response.json())
      .then(body => this.setState({ users: body, user: body.find(u => u.username === this.state.username) }))
      .then(() => fetch(`https://hci-panda-project.herokuapp.com/${this.state.username}/projects`, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify({
          projectKey: `${this.state.username}-project`,
        })
      }))
      .then(response => {
        console.log(response);
        if (!response.ok) {
          alert('Failed to register!')
        } else {
          window.localStorage.setItem('id', this.state.user.id)
          this.props.history.push({ pathname: '/' });
        }
      });
    } else {
      this.setState({ error: 'User with such username exists!' });
    }
  }

  onClickLogin = () => this.props.history.push({ pathname: '/login' });

  render() {
    return (
      <div className="signup">
        <input
          value={this.state.username}
          onChange={this.onChangeUsername}
          className="signup__input"
          placeholder="Username"
        />
        <input
          value={this.state.password}
          onChange={this.onChangePassword}
          className="signup__input"
          placeholder="Password"
          type="password"
        />
        <div className="signup__role">
          <div style={{ color: `${this.state.role === 'Developer' ? '#00cc00' : 'currentColor'}`}} onClick={this.onClickDeveloperRole}>Developer</div>
          <div style={{ color: `${this.state.role === 'Manager' ? '#00cc00' : 'currentColor'}`}} onClick={this.onClickManagerRole}>Manager</div>
        </div>
        <div className="signup__button" onClick={this.onClickSignup}>
          Sign up
        </div>
        <div className="login__signup" onClick={this.onClickLogin}>Log in</div>
        {this.state.error && (
          <div className="signup__error">{this.state.error}</div>
        )}
      </div>
    );
  }
}
