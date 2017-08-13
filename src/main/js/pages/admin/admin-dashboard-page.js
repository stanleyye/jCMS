import axios from 'axios'
import React, { Component } from 'react'
import ReactDOM from 'react-dom'

import PieChart from '../../visualizations/pie-chart'

class AdminDashboardPage extends Component {
	constructor(props) {
		super(props);

		this.state = {
			memoryDetails: []
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

					Object.keys(dictionary).forEach(currentKey => {
						// memoryDetails.push({
						// 	label: currentKey,
						// 	value: dictionary[currentKey]
						// });
						memoryDetails.push(dictionary[currentKey]);
					});

					that.setState({ memoryDetails });
					console.log(that.state.memoryDetails);
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

				<div>
					<svg
						width="100%"
						height="100%"
					>
						<PieChart
							width="500"
							height="500"
							radius="200"
							data={this.state.memoryDetails}
						/>
					</svg>
				</div>
			</div>
		);
	}
}

export default AdminDashboardPage
