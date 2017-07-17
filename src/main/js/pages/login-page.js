import React from 'react'
import ReactDOM from 'react-dom'

import LoginForm from '../forms/login-form'

class LoginPage extends React.Component {
	render() {
		return (
			<div>
				<h1>Login</h1>
				<LoginForm />
			</div>
		);
	}
}

export default LoginPage
