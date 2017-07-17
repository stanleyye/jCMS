import React from 'react'
import ReactDOM from 'react-dom'
import { Nav, NavItem, Navbar, NavDropdown, MenuItem, Glyphicon } from 'react-bootstrap'
import { LinkContainer } from 'react-router-bootstrap'

// TODO: Add an "active" class to the <li> when the url matches the LinkContainer on the sidebar

const AdminNavSidebar = () => (
	<div className="admin-nav-sidebar">
		<Navbar fluid inverse>
			<Navbar.Collapse>
				<Nav>
					<LinkContainer exact to="/admin">
						<NavItem eventKey={1}>
							<Glyphicon glyph="home"/>
							Dashboard
						</NavItem>
					</LinkContainer>

					<NavDropdown
						eventKey={2}
						title={
							<span>
								<i className="glyphicon glyphicon-pencil"></i>
								Posts
							</span>
						}
						id="admin-posts-dropdown"
					>
						<LinkContainer to="/admin/posts">
	          	<MenuItem eventKey={2.1}>All Posts</MenuItem>
	          </LinkContainer>

	          <MenuItem eventKey={2.2}>Create a Post</MenuItem>
	        </NavDropdown>

					<LinkContainer to="/admin/pages">
						<NavItem eventKey={3}>
							<Glyphicon glyph="file"/>
							Pages
						</NavItem>
					</LinkContainer>

					<NavDropdown
						eventKey={4}
						title={
							<span>
								<i className="glyphicon glyphicon-user"></i>
								Users
							</span>
						}
						id="admin-users-dropdown"
					>
						<LinkContainer to="/admin/users">
	          	<MenuItem eventKey={4.1}>All Users</MenuItem>
	          </LinkContainer>

	          <LinkContainer to="/admin/users/create">
	          	<MenuItem eventKey={4.2}>Create a user</MenuItem>
	          </LinkContainer>
	        </NavDropdown>

					<LinkContainer to="/admin/comments">
						<NavItem eventKey={5}>
							<Glyphicon glyph="comment"/>
							Comments
						</NavItem>
					</LinkContainer>

					<LinkContainer to="/admin/settings">
						<NavItem eventKey={6}>
							<Glyphicon glyph="cog"/>
							Settings
						</NavItem>
					</LinkContainer>
				</Nav>
			</Navbar.Collapse>
		</Navbar>
	</div>
)

export default AdminNavSidebar;
