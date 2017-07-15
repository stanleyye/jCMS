import React from 'react'
import ReactDOM from 'react-dom'

class AdminUsersPage extends React.Component {
		constructor(props) {
		super(props);

		this.state = {
			users: []
		}
	}

	componentDidMount() {
		axios.get('/api/users').then(res => {
			console.log(res);
			const users = res.data.map(user => user.data);
			this.setState({ users });
		});
	}

	render() {
		return (
			<div>
				<h1>Users</h1>
			</div>
		);
	}
}

export default AdminUsersPage
