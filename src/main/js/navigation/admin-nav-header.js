import jwtDecode from 'jwt-decode'
import React from 'react'
import ReactDOM from 'react-dom'

// TODO: get the token value that is stored inside the 'jCMSCookie' cookie
const getCookie = (cookieName) => {

}

class AdminNavHeader extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			current_user: {}
		}
	}

	componentDidMount() {
		// console.log(jwtDecode(getCookie('jCMSCookie')));
	}

	render() {
		return (
			<div>
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
							<ul className="nav navbar-nav navbar-right">
								<li className="dropdown">
									<a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
										Dropdown <span className="caret"></span>
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
