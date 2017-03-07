'use strict';
const React = require('react');
const ReactDOM = require('react-dom');
var Client = require('node-rest-client').Client;
var client = new Client();

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
		    posts: [],
		};

	}

	componentDidMount() {
	    var that = this;
	    client.get("http://localhost:8080/posts", function (data, response) {
	        var i, posts = JSON.parse(data.toString())._embedded.posts;
	        for (i = 0; i < posts.length; i+=1)
	            this.state.posts.push(posts[i]);

	        console.log(data.toJSON());
	     
	        console.log(JSON.parse(data.toString()));


	 

	        console.log(response);
	        console.log(response.entity._embedded);

          that.setState({posts: that.state.posts});
          console.log("Posts state:");
          console.log(that.state.posts);
        });
	}

	render() {
		return (
		    <Posts listOfPosts={this.state.posts}/>
		);
	}
}

const Posts = ({listOfPosts}) => (
  <div>
    {
      listOfPosts.map(post => (
        <div class="post">
          <h1>{post.title}</h1>
          <p>{post.summary}</p>
        </div>
      ))
    }
  </div>
);

ReactDOM.render(
	<App />,
	document.getElementById('react')
)