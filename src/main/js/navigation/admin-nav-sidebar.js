import React from 'react'
import ReactDOM from 'react-dom'
import { Link } from 'react-router-dom'

const AdminNavSidebar = () => (
	<div className="admin-nav-sidebar">
		<ul className="nav navbar-nav">
			<li className="active">
				<Link to="/admin">
					<span className="glyphicon glyphicon-home"></span>
					Dashboard
				</Link>
			</li>

			<li>
				<Link to="/admin/posts">
					<span className="glyphicon glyphicon-pencil"></span>
					Posts
				</Link>
			</li>

			<li>
				<Link to="/admin/pages">
					<span className="glyphicon glyphicon-file glyphicon-different-padding"></span>
					Pages
				</Link>
			</li>

			<li>
				<Link to="/admin/users">
					<span className="glyphicon glyphicon-user"></span>
					Users
				</Link>
			</li>

			<li>
				<Link to="/admin/comments">
					<span className="glyphicon glyphicon-comment glyphicon-different-padding"></span>
					Comments
				</Link>
			</li>

			<li className="dropdown">
				<Link to="/admin/settings" className="dropdown-toggle" data-toggle="dropdown">
					<span className="glyphicon glyphicon-cog glyphicon-different-padding"></span>
					Settings
					<span className="caret"></span>
				</Link>

				<ul className="dropdown-menu forAnimate" role="menu">
					<li>
						<Link to="#">Action</Link>
					</li>

					<li>
						<Link to="#">Another action</Link>
					</li>

					<li>
						<Link to="#">Something else here</Link>
					</li>

					<li className="divider"></li>

					<li>
						<Link to="#">Separated link</Link>
					</li>

					<li className="divider"></li>

					<li>
						<Link to="#">One more separated link</Link>
					</li>
				</ul>
			</li>
		</ul>
	</div>
)

export default AdminNavSidebar;
