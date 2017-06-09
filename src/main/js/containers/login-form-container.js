import React, { Component } from 'react'
import { connect } from 'react-redux';
import { loginUser } from '../actions/users';
import LoginForm from '../components/login-form';

class LoginFormContainer extends Component {
	constructor(props) {
    super(props)
    this.handleSubmit = this.handleSubmit.bind(this);
  }

	handleSubmit(values) {
		console.log("values", values);
		this.props.dispatch(loginUser(values));
	}

	render() {
		const { status, token, message, error, loading } = this.props
		return (
			<div>
      	<LoginForm onSubmit={this.handleSubmit} />
      	<p>status: {status}</p>
      	<p>token: {token}</p>
      	<p>message: {message}</p>
      	<p>error: {error}</p>
      	<p>loading: {loading}</p>
      </div>
		)
	}
}

function mapStateToProps(state) {
	var message = null, token = null;
	console.log(state);

	if (state && state.users && state.users.message && state.users.message.data) {
		message = state.users.message.data.message;
	}

  return {
    status: state.users.status,
    token: state.users.token,
		message: message,
		error: state.users.error,
		loading: state.users.loading
  };
}

export default connect(mapStateToProps)(LoginFormContainer);