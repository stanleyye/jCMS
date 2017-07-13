import React from 'react'
import ReactDOM from 'react-dom'

class AdminPostsPage extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			posts: []
		}
	}

	componentDidMount() {
		axios.get('/api/posts').then(res => {
			console.log(res);
			const posts = res.data.map(post => post.data);
			this.setState({ posts });
		});
	}

	render() {
		return (
			<div>
				<h1>Posts</h1>
			</div>
		);
	}
}

export default AdminPostsPage
