import axios from 'axios';
import React from 'react';
import { Form, Text } from 'react-form';

class LoginForm extends React.Component {
		constructor(props) {
			super(props);

			this.state = {
				errorMessage: ""
			}
		}
		// Handles user login authentication
	handleLogin = (formData) => {
		return axios.post('/login', {
				username: formData.username,
				password: formData.password
			})
			.then(function(response) {
				redirectToAdminPage();
			})
			.catch(function(error) {
				console.log("error", error.response);
				this.setState({
					errorMessage: error.response
				})
			});
	}

	// HTTP Redirect (server side routing) to admin page
	redirectToAdminPage = () => {
		window.location.replace("/admin");
	}

	render() {
		return (
			<div className="login-form-wrapper">
				<Form
					onSubmit={(values) => {
						this.handleLogin(values);
					}}

					postSubmit=

					validate={values => {
						const { username, password } = values
						return {
							username: (!username || username.trim() === '') ? 'A username is required' : null,
							password: (!password || password.trim() === '' || (password && password.length < 8)) ? 'The password must be at least 8 characters long' : null
						}
					}}
				>

				{({submitForm}) => {
					return (
						<form onSubmit={submitForm}>

							<div>
								<h4 className="input-heading">Username</h4>
								<Text field='username' placeholder='Username' />
							</div>

							<div>
								<h4 className="input-heading">Password</h4>
								<Text type='password' field='password' placeholder='Password' />
							</div>

							<div>
								<button type='submit'>Login</button>
							</div>

						</form>
					)
				}}
				</Form>

				<div>
					{ this.state.errorMessage }
				</div>
			</div>
		)
	}
}

export default LoginForm;
