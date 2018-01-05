import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Redirect, Switch } from 'react-router-dom';
import Login from './pages/Login/Login';
import Signup from './pages/Signup/Signup';
import Project from './pages/Project/Project';
import Page404 from './pages/Page404/Page404.js';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  render() {
    return (
      <Router>
        <div className="layout">
          <Switch>
            <Route exact path="/" component={Project} />
            <Route path="/login" component={Login} />
            <Route path="/signup" component={Signup} />
            <Route path="*" component={Page404} />
          </Switch>
        </div>
      </Router>
    );
  }
}

export default App;
