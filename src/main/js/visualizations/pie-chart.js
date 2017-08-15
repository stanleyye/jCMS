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
		let {width, height, data} = this.props;
		let pie = d3.pie()

		return (
			<g transform={`translate(${width/2}, ${height/2})`}>
				{/* Render a pie slice for each data point */}
				{pie.value(d => d.value)(data).map(this.renderSlice)}
			</g>
		)
	}

	renderSlice(generatorObj, i) {
		let {innerRadius, outerRadius, padAngle} = this.props;
		console.log(generatorObj);

		return (
			<PieSlice
				key={i}
				padAngle={padAngle}
				innerRadius={innerRadius}
				outerRadius={outerRadius}
				value={generatorObj}
				label={generatorObj.data.label}
				fill={this.colorScale(i)}
				showPieChartDetails={this.props.showPieChartDetails}
			/>
		);
	}
}

export default PieChart
