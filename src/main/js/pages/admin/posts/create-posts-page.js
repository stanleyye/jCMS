import React, { Component } from 'react'
import ReactDOM from 'react-dom'

import CreatePostsForm from '../../../forms/create-posts-form'

class CreatePostsPage extends Component {
	render() {
		return (
			<div>
				<h1>Create post</h1>
				<CreatePostsForm />
			</div>
		);
	}
}

export default CreatePostsPage
