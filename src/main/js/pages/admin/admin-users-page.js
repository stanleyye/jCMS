import React, { Component } from 'react'
import ReactDOM from 'react-dom'
import { Route, Switch } from 'react-router-dom'

import CreateUsersPage from './users/create-users-page'
import ListOfUsersPage from './users/list-of-users-page'

class AdminUsersPage extends Component {
	render() {
		return (
			<div>
				<Switch>
						<Route exact path={`${this.props.match.url}`} component={ListOfUsersPage}/>
						<Route path={`${this.props.match.url}/create`} component={CreateUsersPage}/>
					</Switch>
			</div>
		);
	}
}

export default AdminUsersPage
