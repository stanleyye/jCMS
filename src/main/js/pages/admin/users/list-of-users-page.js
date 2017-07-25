import axios from 'axios';
import React from 'react'
import ReactDOM from 'react-dom'

class ListOfUsersPage extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			users: []
		}
	}

	componentDidMount() {
		// TODO: Do a GET request for the roles for each user
		axios.get('/api/private/users')
			.then(res => {
				console.log(res);

				// Modify each user object to have date strings instead of
				// having Unix timestamps
				const users = res.data.map(user => {
					user.creationDate = new Date(user.creationDate).toString();
					return user;
				});

				this.setState({ users });
			})
			.catch(err => {
				console.log(err);
				console.log(err.response);
			});
	}

	render() {
		return (
			<div>
				<h1>Users</h1>

				<div className="admin-list-of-users-table-wrapper">
					<table className="admin-list-of-users-table">
						<tbody>
							<tr>
								{/* TODO: Add a table header for Role eventually */}
								<th>ID</th>
								<th>Name</th>
								<th>Username</th>
								<th>Email</th>
								<th>Date created</th>
							</tr>

							{
								this.state.users.map((user, index) => (
									<tr key ={index}>
										<td>{user.id}</td>
										<td>{user.name}</td>
										<td>{user.username}</td>
										<td>{user.email}</td>
										<td>{user.creationDate}</td>
									</tr>
								))
							}
						</tbody>
					</table>
				</div>
			</div>
		);
	}
}

export default ListOfUsersPage
