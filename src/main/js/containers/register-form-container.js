import React, { Component } from 'react'
import { connect } from 'react-redux';
import { registerUser } from '../actions/users';
import RegisterForm from '../components/register-form';

class RegisterFormContainer extends Component {
	constructor(props) {
    super(props)
    this.handleSubmit = this.handleSubmit.bind(this);
  }

	handleSubmit(values) {
		console.log("values", values);
		this.props.dispatch(registerUser(values));
	}

	render() {
		const { status, message, error, loading } = this.props
		return (
			<div>
      	<RegisterForm onSubmit={this.handleSubmit} />
      	<p>status: {status}</p>
      	<p>message: {message}</p>
      	<p>error: {error}</p>
      	<p>loading: {loading}</p>
      </div>
		)
	}
}

function mapStateToProps(state) {
	var message = null;
	console.log(state);

	if (state && state.users && state.users.message && state.users.message.data) {
		message = state.users.message.data.message;
	}

  return {
    status: state.users.status,
		message: message,
		error: state.users.error,
		loading: state.users.loading
  };
}

export default connect(mapStateToProps)(RegisterFormContainer);