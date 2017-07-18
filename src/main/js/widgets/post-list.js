import axios from 'axios'
import React from 'react'
import ReactDOM from 'react-dom'

class PostList extends React.Component {
  render() {
    let posts = this.props.data.map((post, index) => {
      return (
        <div key={index} className="post">
          <h2>{post.title}</h2>
          <span>{post.author}</span>
          <p>{post.content}</p>
        </div>
      )
    });

    return (
      <div className="post-list">
        {posts}
      </div>
    );
  }
}

export default PostList
