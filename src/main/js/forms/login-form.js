import React from 'react';
import { Form, Text } from 'react-form';

const LoginForm = (
  <Form 
    onSubmit={(values) => {
      console.log('Success!', values);
    }}

    validate={values => {
      const { username, password } = values
      return {
        username: (!username || username.trim() === '') ? 'A username is required' : null, 
        password: (!password || password.trim() === '' || (password && password.length < 8)) ? 'The password must be at least 8 characters long' : null
      }
    }}
  >

    {({submitForm}) => {
      return (
        <form onSubmit={submitForm}>

          <div>
            <h6>Username</h6>
            <Text field='username' placeholder='Username' />
          </div>

          <div>
            <h6>Password</h6>
            <Text field='password' placeholder='Password' />
          </div>

          <div>
            <button type='submit'>Login</button>
          </div>

        </form>
      )
    }}
  </Form>
)

export default LoginForm;