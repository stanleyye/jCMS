import React from 'react';
import ReactDOM from 'react-dom';

import LoginForm from '../forms/login-form';

class LoginPage extends React.Component {
	// constructor(props) {
 //    super(props);
 //    this.state = {
 //      'username': '',
 //      'password': ''
 //    };

 //    this.handleChange = this.handleChange.bind(this);
 //    this.handleSubmit = this.handleSubmit.bind(this);
 //  }

 //  handleChange(event) {
 //    const name = event.target.name;
 //    const value = event.target.value;
 //    this.setState({
 //      [name]: value
 //    });
 //  }

 //  handleSubmit(event) {
 //    event.preventDefault();
 //    fetch('/register', {  
 //      method: 'post',  
 //      headers: {  
 //        "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"  
 //    },  
 //      body: 'username=' + this.state.username + '&password=' + this.state.password  
 //    })
 //    // .then(json)
 //    .then(function (data) {  
 //      console.log('Request succeeded with JSON response', data);  
 //    })  
 //    .catch(function (error) {  
 //      console.log('Request failed', error);  
 //    });

 //  }

  // <form role="form" onSubmit={this.handleSubmit}>
  //       <label>
  //         Username:
  //         <input type="text" name="username" placeholder="Enter username" 
  //                value={this.state.username} onChange={this.handleChange} />
  //       </label>
  //       <label>
  //         Password:
  //         <input type="password" name="password" placeholder="Enter password" 
  //                value={this.state.password} onChange={this.handleChange} />
  //         </label>
  //       <input type="submit" value="Submit" />
      // </form>

  render() {
    return (
      <div>
        <h1>Login</h1>
        <LoginForm />
      </div>
    );
  }
}

ReactDOM.render(
  <LoginPage />,
  document.getElementById('login-page')
);