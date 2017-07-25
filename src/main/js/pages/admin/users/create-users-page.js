import React from 'react'
import ReactDOM from 'react-dom'

import CreateNewUserForm from '../../../forms/create-users-form'

class CreateUsersPage extends React.Component {
	render() {
		return (
			<div>
				<h1>Create user</h1>
				<CreateNewUserForm />
			</div>
		);
	}
}

export default CreateUsersPage
