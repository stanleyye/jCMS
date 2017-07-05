import React from 'react';
import ReactDOM from 'react-dom';

import BottomNavBar from './navigation/bottom-nav-bar';
import NavHeader from './navigation/nav-header';

class Index extends React.Component {
	render() {
		return (
			<div>
				<NavHeader />
				<h1>Main page</h1>
				<BottomNavBar />
			</div>
		);
	}
}

ReactDOM.render(
	<Index />,
  document.getElementById('root')
);