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

const AdminPage = ({ match }) => (
	<div>
		<AdminNavHeader />
		<AdminNavSidebar />

		<div className="admin-route-wrapper">
			<Switch>
				<Route path={`${match.url}`} component={AdminDashboardPage}/>
				<Route path={`${match.url}/posts`} component={AdminPostsPage}/>
				<Route path={`${match.url}/pages`} component={AdminPagesPage}/>
				<Route path={`${match.url}/users`} component={AdminUsersPage}/>
				<Route path={`${match.url}/comments`} component={AdminCommentsPage}/>
				<Route path={`${match.url}/settings`} component={AdminSettingsPage}/>
			</Switch>
		</div>

		<h1>Bottom nav bar test</h1>
	</div>
)

export default AdminPage
