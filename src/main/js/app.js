import React from 'react'
import ReactDOM from 'react-dom'
import { Route, Switch } from 'react-router-dom'

import AdminPage from './pages/admin-page'
import HomePage from './pages/home-page'
import LoginPage from './pages/login-page'
import RegisterPage from './pages/register-page'

const App = () => (
	<div>
		<Switch>
			<Route exact path='/' component={HomePage}/>
			<Route path='/admin' component={AdminPage}/>
			<Route path='/login' component={LoginPage}/>
			<Route path='/register' component={RegisterPage}/>
		</Switch>
	</div>
)

export default App;
