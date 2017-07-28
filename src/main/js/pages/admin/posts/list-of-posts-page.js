import axios from 'axios';
import React from 'react'
import ReactDOM from 'react-dom'

class ListOfPostsPage extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			posts: []
		}
	}

	componentDidMount() {
		let that = this;

		axios.get('/api/public/posts')
			.then(res => {
				console.log(res);

				// Modify each post object to have date strings instead of
				// having Unix timestamps
				const posts = res.data.map(post => {
					post.publicationDate = new Date(post.publicationDate).toString();
					return post;
				});

				that.setState({ posts });
			})
			.catch(err => {
				console.log(err);
				console.log(err.response);
			});
	}

	render() {
		return (
			<div>
				<h1>Users</h1>

				<div className="admin-list-of-posts-table-wrapper">
					<table className="admin-list-of-posts-table">
						<tbody>
							<tr>
								<th>Post ID</th>
								<th>Author ID</th>
								<th>Author name</th>
								<th>Date created</th>
								<th>Title</th>
								<th>Summary</th>
							</tr>

							{
								this.state.posts.map((post, index) => (
									<tr key ={index}>
										<td>{post.id}</td>
										<td>{post.author.id}</td>
										<td>{post.author.name}</td>
										<td>{post.publicationDate}</td>
										<td>{post.title}</td>
										<td>{post.summary}</td>
									</tr>
								))
							}
						</tbody>
					</table>
				</div>
			</div>
		);
	}
}

export default ListOfPostsPage
