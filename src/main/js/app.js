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
	        var i, posts = JSON.parse(data.toString())['_embedded']['posts'];
	        for (i = 0; i < posts.length; i+=1)
	            that.state.posts.push(posts[i]);

	        console.log(data);
	        console.log(data.toJSON());
	        console.log(data.toString());
	     
	        console.log(JSON.parse(data.toString()));
	        console.log(JSON.parse(data.toString())['_embedded']);
	        console.log(JSON.parse(data.toString())['_embedded']['posts']);

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
        <div className="post" key={post['_links']['self']['href']}>
          <h1>{post.title}</h1>
          <p>By {post.author}</p>
          <p>{post.summary}</p>
          <p>{post.content}</p>
        </div>
      ))
    }
  </div>
);

ReactDOM.render(
	<App />,
	document.getElementById('react')
)