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
            console.log("data (buffer)");
            console.log(data.toString());
            that.state.posts.push(data.toString());
            that.setState({posts: that.state.posts});
            console.log("Posts state:");
            console.log(that.state.posts);
        });
	}

	render() {
		return (
		    <h1>{this.state.posts}</h1>
		);
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)