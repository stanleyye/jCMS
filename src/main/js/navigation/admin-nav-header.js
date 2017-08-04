import jwtDecode from 'jwt-decode'
import React from 'react'
import ReactDOM from 'react-dom'
import { Nav, NavItem, Navbar, NavDropdown, MenuItem, Glyphicon } from 'react-bootstrap'
import { LinkContainer } from 'react-router-bootstrap'

class AdminNavHeader extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			current_user: {}
		}
	}

	componentDidMount() {
		// Get the value (JWT) from the jCMSCookie
		let jCMSCookieValue = readCookie('jCMSCookie');
		let decodedJWTPayload = jwtDecode(jCMSCookieValue);
		console.log(decodedJWTPayload);
		this.setState({
			current_user: decodedJWTPayload
		})
	}

	// Deletes a specified cookie. Does not work on HTTP only cookies.
	deleteCookie(cookieName, domain) {
		if (domain) {
			document.cookie = cookieName +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;domain=.' + domain + ';';
		} else {
			document.cookie = cookieName +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
		}
	}

	logout() {
		deleteCookie('jCMSCookie', 'localhost:8080');
		redirectToHomePage();
	}

	// Gets the value from a specified cookie
	readCookie(cookieName) {
		let index,
				arrayOfCookies = document.cookie.split(';'),
				cookieNameWithEqualOperator = cookieName + "=";

		for (index = 0; index < arrayOfCookies.length; index++) {
				let cookie = arrayOfCookies[index];
				while (cookie.charAt(0) == ' ') {
					cookie = cookie.substring(1,cookie.length)
				};
				if (cookie.indexOf(cookieNameWithEqualOperator) == 0) {
					return cookie.substring(
						cookieNameWithEqualOperator.length, cookie.length
					);
				}
		}
		return null;
	}

	// HTTP Redirect (server side routing) to home page
	redirectToHomePage() {
		window.location.replace("/");
	}

	render() {
		return (
			<div className="admin-nav-header">
				<Navbar fluid inverse>
					<Navbar.Header>
						<LinkContainer exact to="/">
							<Navbar.Brand>
								Site
							</Navbar.Brand>
						</LinkContainer>
						<Navbar.Toggle />
					</Navbar.Header>

					<Navbar.Collapse>
						<Nav pullRight>
							<NavItem eventKey={1}>
								Hello { this.state.current_user.sub } !
							</NavItem>

							<NavDropdown
								eventKey={2}
								title={
									<span>
										<i className="glyphicon glyphicon-cog"></i>
									</span>
								}
								id="admin-posts-dropdown"
							>

								<LinkContainer to="/admin">
									<MenuItem eventKey={2.1}>Profile</MenuItem>
								</LinkContainer>

								<LinkContainer to="/admin">
									<MenuItem eventKey={2.2}>Settings</MenuItem>
								</LinkContainer>

								<MenuItem eventKey={2.3} onClick={logout}>
									Logout
								</MenuItem>
							</NavDropdown>
						</Nav>
					</Navbar.Collapse>
				</Navbar>
			</div>
		);
	}
}

export default AdminNavHeader;
