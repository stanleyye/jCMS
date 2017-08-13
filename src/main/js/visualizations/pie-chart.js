import React, { Component } from 'react'

import PieSlice from './pie-slice.js'

class PieChart extends Component {
	constructor(props) {
		super(props);
    this.colorScheme = d3.schemeCategory10;
    this.colorScale = d3.scaleOrdinal(this.colorScheme);
    this.renderSlice = this.renderSlice.bind(this);
	}

	render() {
		let { width, height, data } = this.props;
		let pie = d3.pie()

		return (
			<g transform={`translate(${width/2}, ${height/2})`}>
        {/* Render a slice for each data point */}
        {pie(data).map(this.renderSlice)}
      </g>
		)
	}

	renderSlice(value, i) {
    // We'll create this component in a minute
    return (
      <PieSlice key={i}
             outerRadius={this.props.radius}
             value={value}
             fill={this.colorScale(i)} />
    );
  }
}

export default PieChart
