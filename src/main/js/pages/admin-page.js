import React from 'react'
import ReactDOM from 'react-dom'

import AdminNavHeader from '../navigation/admin-nav-header'
import AdminNavSidebar from '../navigation/admin-nav-sidebar'

class AdminPage extends React.Component {
	render() {
		return (
			<div>
				<AdminNavHeader />
				<AdminNavSidebar />
				<div>
					<h1>Admin Page</h1>
				</div>
			</div>
		);
	}
}

export default AdminPage
