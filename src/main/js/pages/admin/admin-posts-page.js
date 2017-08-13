import axios from 'axios';
import React, { Component } from 'react'
import ReactDOM from 'react-dom'
import { Route, Switch } from 'react-router-dom'

import CreatePostsPage from './posts/create-posts-page'
import ListOfPostsPage from './posts/list-of-posts-page'

class AdminPostsPage extends Component {
	render() {
		return (
			<div>
				<h1>Posts</h1>
				<Switch>
					<Route exact path={`${this.props.match.url}`} component={ListOfPostsPage}/>
					<Route path={`${this.props.match.url}/create`} component={CreatePostsPage}/>
				</Switch>
			</div>
		);
	}
}

export default AdminPostsPage
