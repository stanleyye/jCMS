import React, { Component } from 'react';
import { Field, reduxForm } from 'redux-form';

const validate = values => {
  const errors = {};

  if (!values.username) {
    errors.username = 'Required';
  } else if (values.username.trim() == '') {
    errors.username = 'Invalid username. Cannot be empty.';
  }

  if (!values.password) {
    errors.password = 'Required';
  } else if (values.password.trim() == '') {
    errors.password  = 'Invalid password. Cannot be empty.';
  }

  return errors;
}

const renderField = ({ input, label, type, meta: { touched, error } }) => (
  <div>
    <input {...input} placeholder={label} type={type}/>
    <div className="form-error">
      {touched && ((error && <span>{error}</span>))}
    </div>
  </div>
)

class LoginForm extends Component {
	render() {
		const { handleSubmit, submitting } = this.props;

		return (
      <form onSubmit={handleSubmit} className="login-form">
        <Field name="username" type="text" label="Username" component={renderField} />
        <Field name="password" type="password" label="Password" component={renderField} />

        <div className="form-submit">
          <button type="submit" disabled={ submitting }>
            Submit
          </button>
        </div>
      </form>
    );
	}
}

// Decorate the form component
LoginForm = reduxForm({
  form: 'login', // a unique name for this form
  validate,
})(LoginForm);

export default LoginForm;