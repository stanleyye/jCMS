import axios from 'axios';
import React, { Component } from 'react';
import { Form, Select, Text } from 'react-form';

// Register the user
const registerUser = (formData) => {
	console.log(formData);
	axios.post('/api/private/users', formData)
		.then(function(response) {
			console.log("response", response);
		})
		.catch(function(error) {
			console.log(error.response);
		});
}

class RegisterForm extends Component {
	constructor(props) {
		super(props);

		this.state = {
			roles: []
		}
	}

	componentDidMount() {
		// Get the role levels from the API
		axios.get('/api/private/roles')
			.then(res => {
				const roles = res.data.map(role => {
					return {
						key: role.id,
						label: role.roleName,
						value: role.id,
					}
				});

				// Sort the IDs in ascending order because we are setting the default
				// <Select> value to be the first role
				roles.sort((a, b) => parseFloat(a.values) - parseFloat(b.values));
				this.setState({ roles });
				console.log(this.state.roles);
		})
		.catch(err => console.log(err));
	}

	render() {
		return (
			<div className="create-user-form-wrapper">
				<Form
					defaultValues={{
						roleId: 'admin'
					}}

					onSubmit={(values) => {
						console.log('Success!', values);
						registerUser(values);
					}}

					validate={values => {
						const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
						const { name, roleId, username, email, password } = values
						return {
							name: (!name || name.trim() === '') ? 'Your name is invalid' : null,
							roleId: !roleId ? 'A role is required' : null,
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
									<Text
										field='name'
										placeholder='Name'
									/>
								</div>

								<div>
									<h4 className="input-heading">Role</h4>
									<Select
										field="roleId"
										options={ this.state.roles }
									/>
								</div>

								<div>
									<h4 className="input-heading">Username</h4>
									<Text
										field='username'
										placeholder='Username'
									/>
								</div>

								<div>
									<h4 className="input-heading">Email</h4>
									<Text
										field='email'
										placeholder='Email'
									/>
								</div>

								<div>
									<h4 className="input-heading">Password</h4>
									<Text
										type='password'
										field='password'
										placeholder='Password'
									/>
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
