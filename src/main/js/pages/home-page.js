import axios from 'axios'
import React from 'react'
import ReactDOM from 'react-dom'
import ReactPaginate from 'react-paginate'
import { Grid, Row } from 'react-bootstrap'

import PostList from '../widgets/post-list'

class HomePage extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			data: [],
			page: 1,
			size: 10
		}
	}

	componentDidMount() {
		this.loadPostsFromServer();
	}

	// Load the posts from the server
	loadPostsFromServer() {
		let that = this;
		let postsUrl = '/api/public/posts?page=' + this.state.page +
											'&size=' + this.state.size;

		axios.get(postsUrl)
			.then(function(response) {
				console.log("response", response);
				that.setState({
					data: response.data.content,
					numOfPages: response.data.totalPages
				})
			})
			.catch(function(error) {
				console.log("error", error);
				console.log(error.toString());
			});
	}

	// Refresh the posts when the user clicks a pagination button
	handlePageClick(data) {
		console.log(data);
		let that = this;
		let selectedPage = data.selected;

		// Callback after setting the state
		this.setState({ page: selectedPage }, function() {
			that.loadPostsFromServer();
		});
	}

	render() {
		return (
			<Grid>
				<Row>
					<h1>Home Page</h1>
				</Row>

				{/* PostList is already wrapped in <Row> components */}
				<PostList
					data={this.state.data}
				/>
				<ReactPaginate
					previousLabel={"previous"}
					nextLabel={"next"}
					breakLabel={<a href="">...</a>}
					breakClassName={"break-label"}
          pageCount={this.state.pageCount}
					marginPagesDisplayed={2}
					pageRangeDisplayed={5}
					onPageChange={this.handlePageClick.bind(this)}
					containerClassName={"pagination"}
					subContainerClassName={"pages pagination"}
					activeClassName={"active"}
        />

			</Grid>
		);
	}
}

export default HomePage
