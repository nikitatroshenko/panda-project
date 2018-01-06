import React, { Component } from 'react';
import PropTypes from 'prop-types';
import SvgIcon from 'react-inlinesvg';
import PlusIcon from './../../images/plus.svg';
import UserIcon from './../../images/user-default.svg';
import SearchIcon from './../../images/search.svg';
import './Project.css';

export default class Project extends Component {
  static propTypes = {
    history: PropTypes.object.isRequired,
  }

  state = {
    user: null,
    project: null,
    tickets: [],
    isUpdated: false,
  }

  componentWillMount() {
    if (window.localStorage.getItem('id')) {
      const headers = new Headers();
      headers.append('Content-type', 'application/json');
      fetch(`https://hci-panda-project.herokuapp.com/users/${window.localStorage.getItem('id')}`, {
        method: 'GET',
        headers,
        mode: 'cors',
      })
      .then(response => response.json())
      .then(body => this.setState({ user: body }))
      .then(() => fetch('https://hci-panda-project.herokuapp.com/projects', {
        method: 'GET',
        headers,
        mode: 'cors',
      }))
      .then(response => response.json())
      .then(body => {
        if (this.state.user) {
          return this.setState({ project: body.find(p => p.manager.id === this.state.user.id) });
        }
      })
      .then(() => fetch('https://hci-panda-project.herokuapp.com/tickets', {
        method: 'GET',
        headers,
        mode: 'cors',
      }))
      .then(response => response.json())
      .then(tickets => {
        if (this.state.project) {
          const projectTickets = tickets.filter(ticket => ticket.project && ticket.project.id === this.state.project.id);
          const todoTickets = projectTickets.filter(ticket => ticket.ticketStatus === 0);
          const wipTickets = projectTickets.filter(ticket => ticket.ticketStatus === 1);
          const doneTickets = projectTickets.filter(ticket => ticket.ticketStatus === 2);
          const blockedTickets = projectTickets.filter(ticket => ticket.ticketStatus === 3);
          return this.setState({ tickets: [todoTickets, wipTickets, doneTickets, blockedTickets] });
        }
      });
    }
  }

  updateTickets = () => {
    const headers = new Headers();
    headers.append('Content-type', 'application/json');
    fetch('https://hci-panda-project.herokuapp.com/tickets', {
      method: 'GET',
      headers,
      mode: 'cors',
    })
    .then(response => response.json())
    .then(tickets => {
      if (this.state.project) {
        const projectTickets = tickets.filter(ticket => ticket.project && ticket.project.id === this.state.project.id);
        const todoTickets = projectTickets.filter(ticket => ticket.ticketStatus === 0);
        const wipTickets = projectTickets.filter(ticket => ticket.ticketStatus === 1);
        const doneTickets = projectTickets.filter(ticket => ticket.ticketStatus === 2);
        const blockedTickets = projectTickets.filter(ticket => ticket.ticketStatus === 3);
        return this.setState({ tickets: [todoTickets, wipTickets, doneTickets, blockedTickets] });
      }
    });
  }

  render() {
    return (
      <div>
        <Header
          history={this.props.history}
          user={this.state.user}
          project={this.state.project}
          updateTickets={this.updateTickets}
        />
        <Content user={this.state.user} project={this.state.project} tickets={this.state.tickets} />
      </div>
    );
  }
}

class Header extends Component {
  static propTypes = {
    history: PropTypes.object.isRequired,
    user: PropTypes.object,
    project: PropTypes.object,
    updateTickets: PropTypes.func,
  }

  state = {
    searchText: '',
    isMenuShow: false,
    isAddTaskShow: false,
  }

  onNavigateToProject = () => {
    this.props.history.push({ pathname: '/' });
  }

  onChangeSearchText = (e) => {
    this.setState({ searchText: e.target.value });
  }

  onClickUser = () => {
    this.setState({ isMenuShow: !this.state.isMenuShow });
  }

  onLogout = () => {
    window.localStorage.removeItem('id');
    this.props.history.push({ pathname: '/login' });
  }

  onAddTask = () => {
    this.setState({ isAddTaskShow: !this.state.isAddTaskShow });
  }

  onSave = () => {
    this.setState({ isAddTaskShow: false }, this.props.updateTickets);
  }

  render() {

    return (
      <div className="header">
        <div className="header__navigation">
          <div className="header__link" onClick={this.onAddTask}>
            <SvgIcon src={PlusIcon} />
          </div>
          <div
            className="header__link"
            onClick={this.onNavigateToProject}
          >
            Taskbar
          </div>
          {this.state.isAddTaskShow && (
            <AddTask
              username={(this.props.user || {}).username}
              projectKey={(this.props.project || {}).projectKey}
              onSave={this.onSave}
            />
          )}
        </div>
        <div className="header__actions">
          <div className="header__search">
            <SvgIcon src={SearchIcon} className="header__search-icon"/>
            <input
              className="search__input"
              value={this.state.searchText}
              onChange={this.onChangeSearchText}
              placeholder="Search"
            />
          </div>
          <div className="header__user" onClick={this.onClickUser}>
            <SvgIcon src={UserIcon} className="header__avatar" />
            {this.props.user ? this.props.user.username : 'User'}
            {this.state.isMenuShow && (
              <div className="header__profile-action">
                <div className="header__profile-info">
                  <div className="header__profile-username">{this.props.user ? this.props.user.username : 'User'}</div>
                  <div className="header__profile-descr">Username</div>
                </div>
                <div onClick={this.onLogout} className="header__profile-logout">Logout</div>
              </div>
            )}
          </div>
        </div>
      </div>
    );
  }
}

class Content extends Component {
  static propTypes = {
    user: PropTypes.object,
    project: PropTypes.object,
    tickets: PropTypes.array,
  }

  render() {
    if (!this.props.project) {
      return null;
    }

    console.dir(this.props.tickets);

    return (
      <div className="content">
        <div className="project__info">
          <span>{this.props.project.projectKey}</span>
        </div>
        <div className="boards">
          <StatusBoard status={0} tickets={this.props.tickets ? this.props.tickets[0] : []} />
          <StatusBoard status={1} tickets={this.props.tickets ? this.props.tickets[1] : []} />
          <StatusBoard status={2} tickets={this.props.tickets ? this.props.tickets[2] : []} />
          <StatusBoard status={3} tickets={this.props.tickets ? this.props.tickets[3] : []} />
        </div>
      </div>
    );
  }
}

class StatusBoard extends Component {
  static propTypes = {
    status: PropTypes.number.isRequired,
    tickets: PropTypes.array,
  }

  mapStatus = (number) => {
    if (number === 0) {
      return 'To do';
    } else if (number === 1) {
      return 'In progress';
    } else if (number === 2) {
      return 'Done';
    } else if (number === 3) {
      return 'Blocked';
    }
  }

  render() {
    console.dir(this.props.tickets);

    const ticketsBlock = (this.props.tickets || []).map(t => (
      <Task title={t.name} description={t.description} />
    ));
    return (
      <div className="board">
        <div className="board__status">{this.mapStatus(this.props.status)}</div>
        <div className="board__tasks">
          {ticketsBlock}
        </div>
      </div>
    );
  }
}

class Task extends Component {
  static propTypes = {
    title: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
  }

  render() {
    return (
      <div className="task">
        <div className="task__title">{this.props.title}</div>
        <div className="task__description">{this.props.description}</div>
      </div>
    );
  }
}

class AddTask extends Component {
  static propTypes = {
    username: PropTypes.string,
    projectKey: PropTypes.string,
    onSave: PropTypes.func,
  }

  state = {
    title: '',
    description: '',
  }

  onChangeTitle = (e) => { this.setState({ title: e.target.value }) }

  onChangeDescription = (e) => { this.setState({ description: e.target.value }) }

  onSave = () => {
    let headers = new Headers();
    headers.append('Content-type', 'application/json');
    fetch(`https://hci-panda-project.herokuapp.com/${this.props.username}/${this.props.projectKey}/tickets`, {
      method: 'POST',
      headers: headers,
      body: JSON.stringify({
        name: this.state.title,
        ticketKey: `Ticket${Math.floor(Math.random() * Math.floor(10000))}`,
        description: this.state.description,
      })
    })
    .then(() => this.props.onSave());
  }

  render() {
    return (
      <div className="atask">
        <input
          className="atask__input"
          placeholder="Title"
          value={this.state.title}
          onChange={this.onChangeTitle}
        />
        <input
          className="atask__input"
          placeholder="Description"
          value={this.state.description}
          onChange={this.onChangeDescription}
        />
      <div className="atask__save" onClick={this.onSave}>Save</div>
      </div>
    );
  }
}
