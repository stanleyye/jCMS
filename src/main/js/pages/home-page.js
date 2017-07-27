import axios from 'axios'
import React from 'react'
import ReactDOM from 'react-dom'
import ReactPaginate from 'react-paginate';

import PostList from '../widgets/post-list'

class HomePage extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			data: [],
			offset: 0
		}
	}

	loadPostsFromServer() {
		let getPostsUrl = '/api/public/posts?limit=' + this.props.perPage + '&offset=' +
			this.state.offset;
		axios.get(getPostsUrl)
			.then(function(response) {
				console.log("response", response);
			})
			.catch(function(error) {
				console.log("error", error);
				console.log(error.toString());
			});
	}

	componentDidMount() {
		this.loadPostsFromServer();
	}

	render() {
		return (
			<div>
				<h1>Home Page</h1>
				<PostList data={this.state.data}/>
				<ReactPaginate previousLabel={"previous"}
                       nextLabel={"next"} />
			</div>
		);
	}
}

export default HomePage
