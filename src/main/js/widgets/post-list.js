import axios from 'axios'
import React, { Component } from 'react'
import ReactDOM from 'react-dom'
import { Row } from 'react-bootstrap'

class PostList extends Component {
  render() {
    let posts = this.props.data.map((post, index) => {
      return (
        <Row key={index} className="post">
          <h2>{post.title}</h2>
          <span>{post.author.username}</span>
          <p>{post.content}</p>
        </Row>
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
