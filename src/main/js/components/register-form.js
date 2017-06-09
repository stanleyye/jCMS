import React, { Component } from 'react';
import { Field, reduxForm } from 'redux-form';
import { registerUser, registerUserSuccess, registerUserFailure } from '../actions/users';

const validate = values => {
  const errors = {};
  const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/
  if (!values.first_name) {
    errors.first_name = 'Required';
  } else if (values.first_name.trim() == '') {
    errors.first_name = 'Invalid first name';
  }

  if (!values.last_name) {
    errors.last_name = 'Required';
  } else if (values.last_name.trim() == '') {
    errors.last_name = 'Invalid last name';
  }

  if (!values.email) {
    errors.email = 'Required';
  } else if (values.email.length < 7 || !emailRegex.test(values.email)) {
    errors.email = 'Invalid email';
  }

  if (!values.username) {
    errors.username = 'Required';
  } else if (values.username.trim() == '') {
    errors.username = 'Invalid username';
  }

  if (!values.password) {
    errors.password = 'Required';
  } else if (values.password.trim() == '' || !passwordRegex.test(values.password)) {
    errors.password  = 'Invalid password. Requires atleast 1 alphabet and 1 number and 8 characters?';
  }

  return errors;
}

// const asyncValidate = (values, dispatch) => {
//   // TODO: create an action that handles validating username + email
//   return dispatch(registerUser(values))
//     .then((res) => {
//       console.log(values);
//     });
// }

const renderField = ({ input, label, type, meta: { touched, error } }) => (
  <div>
    <label>{label}</label>
    <div>
      <input {...input} placeholder={label} type={type}/>
      {touched && ((error && <span>{error}</span>))}
    </div>
  </div>
)

class RegisterForm extends Component {
	render() {
		const { handleSubmit, submitting } = this.props;

		return (
      <form onSubmit={handleSubmit}>
        <Field name="first_name" type="text" label="First name" component={renderField} />
        <Field name="last_name" type="text" label="Last name" component={renderField} />
        <Field name="email" type="email" label="Email" component={renderField} />
        <Field name="username" type="text" label="Username" component={renderField} />
        <Field name="password" type="password" label="Password" component={renderField} />

        <div>
          <button type="submit" disabled={ submitting }>
            Submit
          </button>
        </div>
      </form>
    );
	}
}

RegisterForm = reduxForm({
  form: 'RegisterForm', // a unique name for this form
  validate,
  // asyncValidate,
  // asyncBlurFields: [ 'username']
})(RegisterForm);

export default RegisterForm;