import React from 'react'
import ReactDOM from 'react-dom'

import RegisterForm from '../forms/register-form'

class RegisterPage extends React.Component {
  render() {
    return (
      <div>
        <h1>Register</h1>
        <RegisterForm />
      </div>
    );
  }
}

export default RegisterPage