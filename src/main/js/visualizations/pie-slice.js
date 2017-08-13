import React, { Component } from 'react'

class PieSlice extends Component {
	render() {
    let {value, fill, innerRadius = 0, outerRadius} = this.props;
    console.log(value);
    console.log(fill);
    console.log(outerRadius);

    let arc = d3.arc()
      .innerRadius(innerRadius)
      .outerRadius(outerRadius);
    return (
      <path d={arc(value)} fill={fill} />
    );
  }
}

export default PieSlice
