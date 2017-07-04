'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import { 
	BrowserRouter as Router,
	Route,
	Switch } from 'react-router-dom';

import Home from './pages/home';
import LoginPage from './pages/login-page';
import RegisterPage from './pages/register-page';

const App = (props) => (
	<Router>
	  <Switch>
	  	<Route path="/login" component={LoginPage}/>
	    <Route path="/register" component={RegisterPage}/>
	    <Route exact path="/" component={Home}/>
	  </Switch>
	</Router>
);

export default App;