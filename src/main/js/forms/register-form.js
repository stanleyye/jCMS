import React from 'react';
import { Form, Text } from 'react-form';

const handleLogin = (formData) => {
	console.log(formData);
	return axios.post('/register', formData)
		.then(function(response) {
			console.log("response", response);
		})
		.catch(function(error) {
			console.log("error", error);
		});
}

class RegisterForm extends React.Component {
	render() {
		return (
			<div className="register-form-wrapper">
				<Form
					onSubmit={(values) => {
						console.log('Success!', values);
						handleLogin(values);
					}}

					validate={values => {
						const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
						const { name, username, email, password } = values
						return {
							name: (!name || name.trim() === '') ? 'Your name is invalid' : null,
							username: (!username || username.trim() === '') ? 'A username is required' : null,
							email: (!email || email.trim() === '' || !emailRegex.test(email)) ?  'The email address is invalid' : null,
							password: (!password || password.trim() === '' || (password && password.length < 8)) ? 'Password must be at least 8 characters long' : null
						}
					}}
				>

					{({submitForm}) => {
						return (
							<form onSubmit={submitForm}>

								<div>
									<h4 className="input-heading">Name</h4>
									<Text field='name' placeholder='Name' />
								</div>

								<div>
									<h4 className="input-heading">Username</h4>
									<Text field='username' placeholder='Username' />
								</div>

								<div>
									<h4 className="input-heading">Email</h4>
									<Text field='email' placeholder='Email' />
								</div>

								<div>
									<h4 className="input-heading">Password</h4>
									<Text type='password' field='password' placeholder='Password' />
								</div>

								<div>
									<button type='submit'>Create user</button>
								</div>

							</form>
						)
					}}
				</Form>
			</div>
		)
	}
}

export default RegisterForm;
