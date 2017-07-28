import jwtDecode from 'jwt-decode'
import React from 'react'
import ReactDOM from 'react-dom'
import { Nav, NavItem, Navbar, NavDropdown, MenuItem, Glyphicon } from 'react-bootstrap'
import { LinkContainer } from 'react-router-bootstrap'

// Gets the value from a specified cookie
const readCookie = (cookieName) => {
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

	render() {
		return (
			<div>
				{
					/*
					<Navbar fluid inverse>
					<Navbar.Collapse>
						<Nav>
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
						</Nav>
					</Navbar.Collapse>
				</Navbar>
				*/
				}

				<nav className="navbar navbar-default">
					<div className="container-fluid">
						<div className="navbar-header">
							<button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span className="sr-only">Toggle navigation</span>
								<span className="icon-bar"></span>
								<span className="icon-bar"></span>
								<span className="icon-bar"></span>
							</button>
							<a className="navbar-brand" href="#">Brand</a>
						</div>

						<div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							Hello { this.state.current_user.sub } !
							<ul className="nav navbar-nav navbar-right">
								<li className="dropdown">
									<a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
										<span className="caret"></span>
									</a>
									<ul className="dropdown-menu">
										<li><a href="#">Profile</a></li>
										<li><a href="#">Settings</a></li>
										<li role="separator" className="divider"></li>
										<li><a href="#">Logout</a></li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</nav>
			</div>
		);
	}
}

export default AdminNavHeader;
