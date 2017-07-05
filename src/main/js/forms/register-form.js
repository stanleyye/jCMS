import React from 'react';
import { Form, Text } from 'react-form';

const handleLogin = (formData) => {
  console.log(formData);
  return axios.post('/register', formData)
    .then(function(response) {
      console.log("response", response);
    })
    .catch(function(error) {
      console.log("error", error);
    });
}

const RegisterForm = (
  <Form 
    onSubmit={(values) => {
      console.log('Success!', values);
      handleLogin(values);
    }}

    validate={values => {
      const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      const { name, username, email, password } = values
      return {
        name: (!name || name.trim() === '') ? 'Your name is invalid' : null,
        username: (!username || username.trim() === '') ? 'A username is required' : null, 
        email: (!email || email.trim() === '' || !emailRegex.test(email)) ?  'The email address is invalid' : null,
        password: (!password || password.trim() === '' || (password && password.length < 8)) ? 'The password must be at least 8 characters long' : null
      }
    }}
  >

    {({submitForm}) => {
      return (
        <form onSubmit={submitForm}>

          <div>
            <h6>Name</h6>
            <Text field='name' placeholder='Name' />
          </div>

          <div>
            <h6>Username</h6>
            <Text field='username' placeholder='Username' />
          </div>

          <div>
            <h6>Email</h6>
            <Text field='email' placeholder='Email' />
          </div>

          <div>
            <h6>Password</h6>
            <Text field='password' placeholder='Password' />
          </div>

          <div>
            <button type='submit'>Register</button>
          </div>

        </form>
      )
    }}
  </Form>
)

export default RegisterForm;