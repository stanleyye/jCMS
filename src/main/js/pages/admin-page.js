import React from 'react'
import ReactDOM from 'react-dom'
import { Route, Switch } from 'react-router-dom'

import AdminNavHeader from '../navigation/admin-nav-header'
import AdminNavSidebar from '../navigation/admin-nav-sidebar'

import AdminCommentsPage from './admin/admin-comments-page'
import AdminDashboardPage from './admin/admin-dashboard-page'
import AdminPostsPage from './admin/admin-posts-page'
import AdminPagesPage from './admin/admin-pages-page'
import AdminSettingsPage from './admin/admin-settings-page'
import AdminUsersPage from './admin/admin-users-page'

class AdminPage extends React.Component {
	render() {
		console.log(this.props);
		return (
			<div>
				<AdminNavHeader />
				<AdminNavSidebar />

				<div className="admin-route-content-wrapper">
					<Switch>
						<Route exact path={`${this.props.match.url}`} component={AdminDashboardPage}/>
						<Route path={`${this.props.match.url}/posts`} component={AdminPostsPage}/>
						<Route path={`${this.props.match.url}/pages`} component={AdminPagesPage}/>
						<Route path={`${this.props.match.url}/users`} component={AdminUsersPage}/>
						<Route path={`${this.props.match.url}/comments`} component={AdminCommentsPage}/>
						<Route path={`${this.props.match.url}/settings`} component={AdminSettingsPage}/>
					</Switch>
				</div>
			</div>
		)
	}
}

export default AdminPage
