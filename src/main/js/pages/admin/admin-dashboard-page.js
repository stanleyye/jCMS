import axios from 'axios'
import React, { Component } from 'react'
import ReactDOM from 'react-dom'

import PieChart from '../../visualizations/pie-chart'

class AdminDashboardPage extends Component {
	constructor(props) {
		super(props);

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

						// Remember the total memory size
						if (key === 'Total') {
							totalMemory = keyValue;
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

	render() {
		return (
			<div>
				<h1>Admin Dashboard</h1>

				<div className="pie-chart">
					<div id="pie-chart-details">
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
							width={500}
							height={500}
							innerRadius={200 * 0.55}
							outerRadius={200}
							padAngle={0.02}
							data={this.state.memoryDetails}
							showPieChartDetails={this.showPieChartDetails}
						/>
					</svg>
				</div>
			</div>
		);
	}

	showPieChartDetails(detailsObj) {
		this.setState({
			pieChartDetailsTitle: "asdfsdF",
			pieChartDetailsBody: "asdfsd"
		})
	}
}

export default AdminDashboardPage
