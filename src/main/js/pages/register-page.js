import React from 'react';
import ReactDOM from 'react-dom';

import RegisterForm from '../components/register-form';
import RegisterFormContainer from '../containers/register-form-container';
import { registerUser } from '../actions/users';

class RegisterPage extends React.Component {
  render() {
    return (
      <div>
        <h1>
          Register Page
        </h1>

        <RegisterFormContainer />
      </div>
    );
  }
}

export default RegisterPage;
