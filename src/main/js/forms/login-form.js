import axios from 'axios';
import React from 'react';
import { Form, Text } from 'react-form';

const handleLogin = (formData) => {
	return axios.post('/login', {
			params: {
				username: formData.username,
				password: formData.password
			}
		})
		.then(function(response) {
			console.log("response", response);
		})
		.catch(function(error) {
			console.log("error", error);
		});
}

class LoginForm extends React.Component {
	render() {
		return (
			<Form 
				onSubmit={(values) => {
					console.log('Success!', values);
					handleLogin(values);
				}}

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
							<Text field='password' placeholder='Password' />
						</div>

						<div>
							<button type='submit'>Login</button>
						</div>

					</form>
				)
			}}
			</Form>
		)
	}
}

export default LoginForm;