import React from 'react'
import ReactDOM from 'react-dom'
import { Route } from 'react-router-dom'

import AdminPage from './pages/admin-page'
import HomePage from './pages/home-page'
import LoginPage from './pages/login-page'
import RegisterPage from './pages/register-page'

class App extends React.Component {
	render() {
		return (
			<div>
				<Switch>
					<Route exact path='/' component={HomePage}/>
					<Route path='/admin' component={AdminPage}/>
					<Route path='/login' component={LoginPage}/>
				</Switch>
			</div>
		);
	}
}

export default App;
