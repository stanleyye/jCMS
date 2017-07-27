import React from 'react'
import ReactDOM from 'react-dom'
import { Route, Switch } from 'react-router-dom'

import ProfilePage from './settings/profile-page'

class AdminSettingsPage extends React.Component {
	render() {
		return (
			<div>
				<h1>Settings</h1>
				<Switch>
					<Route path={`${this.props.match.url}/profile`} component={ProfilePage}/>
				</Switch>
			</div>
		);
	}
}

export default AdminSettingsPage
