import React from 'react';
import ReactDOM from 'react-dom';

class AdminNavSidebar extends React.Component {
	render() {
		return (
			<div>
				<ul className="nav navbar-nav">
					<li className="active">
						<a href="#">
							Dashboard
							<span style="font-size:16px;" className="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span>
						</a>
					</li>

					<li>
						<a href="#">
							Profile
							<span style="font-size:16px;" className="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span>
						</a>
					</li>

					<li>
						<a href="#">
							Messages
							<span style="font-size:16px;" className="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span>
						</a>
					</li>

					<li className="dropdown">
						<a href="#" className="dropdown-toggle" data-toggle="dropdown">
							Settings
							<span className="caret"></span>
							<span style="font-size:16px;" className="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span>
						</a>

						<ul className="dropdown-menu forAnimate" role="menu">
							<li>
								<a href="#">Action</a>
							</li>

							<li><a href="#">Another action</a></li>

							<li><a href="#">Something else here</a></li>
							<li className="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li className="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul>
					</li>
					<li><a href="#">Home<span style="font-size:16px;" className="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
					<li ><a href="#">Profile<span style="font-size:16px;" className="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>
					<li ><a href="#">Messages<span style="font-size:16px;" className="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
					<li className="dropdown">
						<a href="#" className="dropdown-toggle" data-toggle="dropdown">Settings <span className="caret"></span><span style="font-size:16px;" className="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
						<ul className="dropdown-menu forAnimate" role="menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li className="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li className="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul>
					</li>
				</ul>
			</div>
		);
	}
}

export default AdminNavSidebar;
