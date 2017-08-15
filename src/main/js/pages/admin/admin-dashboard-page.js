import axios from 'axios'
import React, { Component } from 'react'
import ReactDOM from 'react-dom'

import PieChart from '../../visualizations/pie-chart'

class AdminDashboardPage extends Component {
	constructor(props) {
		super(props);

		this.showPieChartDetails = this.showPieChartDetails.bind(this);

		this.state = {
			memoryDetails: [],
			totalMemory: 0
		}
	}

	componentDidMount() {
		let that = this;

		axios.get('/api/private/config-details/memory')
			.then(res => {
				const memoryDetails = [];
				console.log(res);

				if (res && res.data && res.data.length > 0) {
					const dictionary = res.data[0];
					let totalMemory = 0;

					Object.keys(dictionary).forEach( key => {
						const keyValue = dictionary[key];

						// Remember the total memory size and continue to the next
						// iteration
						if (key === 'Total') {
							totalMemory = keyValue;
							return;
						}

						memoryDetails.push({
							label: key,
							value: keyValue
						});
					});

					that.setState({memoryDetails, totalMemory});
					console.log(that.state);
				}

				console.log(memoryDetails);
			})
			.catch(err => {
				console.log(err);
				console.log(err.response);
			});
	}

	showPieChartDetails(detailsObj) {
		const percent = ((detailsObj.value / this.state.totalMemory) * 100).toFixed(2) ;
		const megabytes = (detailsObj.value / Math.pow(2, 20)).toFixed(2);
		this.setState({
			pieChartDetailsTitle: percent + "%",
			pieChartDetailsBody: megabytes + " MB"
		})
	}

	render() {
		return (
			<div>
				<h1>Admin Dashboard</h1>

				<div className="pie-chart">
					<h3>Memory Usage</h3>
					<div className="pie-chart-details">
						<h4>
							{this.state.pieChartDetailsTitle}
						</h4>
						<p>
							{this.state.pieChartDetailsBody}
						</p>
					</div>

					<svg
						width="100%"
						height="100%"
					>
						<PieChart
							width={325}
							height={325}
							innerRadius={130 * 0.65}
							outerRadius={130}
							padAngle={0.02}
							data={this.state.memoryDetails}
							showPieChartDetails={this.showPieChartDetails}
						/>
					</svg>
				</div>
			</div>
		);
	}
}

export default AdminDashboardPage
