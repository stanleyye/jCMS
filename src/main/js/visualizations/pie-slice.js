import React, { Component } from 'react'

class PieSlice extends Component {
	constructor(props) {
		super(props);
		this.onMouseOver = this.onMouseOver.bind(this);
		this.onMouseOut = this.onMouseOut.bind(this);
	}


	onMouseOut() {
		// TODO
	}

	onMouseOver() {
		this.props.showPieChartDetails(this.props.value.data);
	}

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
			<g onMouseOver={this.onMouseOver} onMouseOut={this.onMouseOut}>
				<path d={arc(value)} fill={fill} />
				<text
					transform={`translate(${arc.centroid(value)})`}
					dy=".35em"
					textAnchor="middle"
					fill="white"
					className="pie-chart-label"
				>
					{label}
				</text>
			</g>
		);
	}
}

export default PieSlice
