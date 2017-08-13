import axios from 'axios';
import React, { Component } from 'react';
import { Form, Text, Textarea } from 'react-form';

// Create the post
const CreatePost = (formData) => {
	console.log(formData);
	axios.post('/api/private/posts', formData)
		.then(function(response) {
			console.log("response", response);
		})
		.catch(function(error) {
			console.log(error.response);
		});
}

class CreatePostForm extends Component {
	render() {
		return (
			<div className="create-post-form-wrapper">
				<Form
					onSubmit={(values) => {
						console.log('Success!', values);
						CreatePost(values);
					}}

					validate={values => {
						const { title, summary, content } = values
						return {
							title: (!title || title.trim() === '') ? 'The title is invalid' : null,
							summary: (!roleId || summary.trim() === '') ? 'A summary is required' : null,
							content: (!content || content.trim() === '') ? 'A content body is required' : null
						}
					}}
				>

					{({submitForm}) => {
						return (
							<form onSubmit={submitForm}>

								<div>
									<h4 className="input-heading">Title</h4>
									<Text
										field='title'
										placeholder='Title'
									/>
								</div>

								<div>
									<h4 className="input-heading">Summary</h4>
									<Textarea
										field='summary'
										placeholder='Summary'
            			/>
								</div>

								<div>
									<h4 className="input-heading">Content</h4>
									<Textarea
										field='content'
										placeholder='Content body'
            			/>
								</div>

								<div>
									<button type='submit'>Create post</button>
								</div>

							</form>
						)
					}}
				</Form>
			</div>
		)
	}
}

export default CreatePostForm;
