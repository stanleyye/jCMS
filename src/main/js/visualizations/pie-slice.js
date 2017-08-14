import React, { Component } from 'react'

class PieSlice extends Component {
	render() {
    let {label, value, fill, innerRadius, outerRadius, padAngle} = this.props;
    console.log(value);
    console.log(fill);
    console.log(outerRadius);

    let arc = d3.arc()
      .innerRadius(innerRadius)
      .outerRadius(outerRadius)
      .padAngle(padAngle);

    return (
    	<g>
      	<path d={arc(value)} fill={fill} />
      	<text transform={`translate(${arc.centroid(value)})`}
              dy=".35em"
              textAnchor="middle"
              fill="white">
          {label}
        </text>
      </g>
    );
  }
}

export default PieSlice
