import React from 'react';
import ReactDOM from 'react-dom';
import { Route } from 'react-router-dom'

// import BottomNavBar from './navigation/bottom-nav-bar'
// import NavHeader from './navigation/nav-header'
import LoginPage from './pages/login-page'
import RegisterPage from './pages/register-page'

const Home = () => (
		<h1>Home page</h1>
)

class App extends React.Component {
	render() {
		return (
			<div>
	      <Route exact path='/' component={Home}/>
	      <Route path='/login' component={LoginPage}/>
	      <Route path='/register' component={RegisterPage}/>
	    </div>
		);
	}
}

export default App;