import axios from 'axios';
import React from 'react'
import ReactDOM from 'react-dom'
import { Route, Switch } from 'react-router-dom'

import CreatePostsPage from './posts/create-posts-page'

class AdminPostsPage extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			posts: []
		}
	}

	componentDidMount() {
		axios.get('/api/public/posts').then(res => {
			console.log(res);
			const posts = res.data.map(post => post.data);
			this.setState({ posts });
		});
	}

	render() {
		return (
			<div>
				<h1>Posts</h1>
				<Switch>
					<Route path={`${this.props.match.url}/create`} component={CreatePostsPage}/>
				</Switch>
			</div>
		);
	}
}

export default AdminPostsPage
