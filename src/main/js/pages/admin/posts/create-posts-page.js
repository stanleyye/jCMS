import React from 'react'
import ReactDOM from 'react-dom'

import CreatePostsForm from '../../../forms/create-posts-form'

class CreatePostsPage extends React.Component {
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
