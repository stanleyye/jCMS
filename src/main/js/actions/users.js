import axios from 'axios';
import { 
	REGISTER_USER, 
	REGISTER_USER_REQUEST, 
	REGISTER_USER_SUCCESS, 
	REGISTER_USER_FAILURE,
	LOGIN_USER,
	LOGIN_USER_REQUEST,
	LOGIN_USER_SUCCESS,
	LOGIN_USER_FAILURE 
} from './action-types';

/*
 * TODO: refactor registerUser + loginUser later
 */

/* Registration */
export function registerUser(formData) {
	return dispatch => {
		dispatch(registerUserRequest());
		
		return axios.post('/register', formData)
			.then(function(response) {
				console.log("response", response);
				dispatch(registerUserSuccess(response.status, response));
			})
			.catch(function(error) {
				console.log("error", error);
				dispatch(registerUserFailure(error.status, error));
			});
	}
}

export function registerUserRequest() {
	return {
		type: REGISTER_USER_REQUEST,
 		payload: null
	}
}

export function registerUserSuccess(status, response) {
 	return {
 		type: REGISTER_USER_SUCCESS,
 		payload: {
 			status,
 			response
 		}
 	};
 }

export function registerUserFailure(status, err) {
	return {
		type: REGISTER_USER_FAILURE,
		payload: {
			status,
			err
		}
	};
}

/* Login */
export function loginUser(formData) {
	return dispatch => {
		dispatch(loginUserRequest());
		
		return axios.post('/login', formData)
			.then(function(response) {
				console.log("response", response);
				console.log("token", response.data.token);
				// store token in localStorage - persists across all tabs
				localStorage.setItem('jwt', response.data.token);

				dispatch(loginUserSuccess(response.status, response.data.token));
			})
			.catch(function(error) {
				console.log("error", error);
				dispatch(loginUserFailure(error.status, error));
			});
	}
}

export function loginUserRequest() {
	return {
		type: LOGIN_USER_REQUEST,
 		payload: null
	}
}

export function loginUserSuccess(status, token) {
 	return {
 		type: LOGIN_USER_SUCCESS,
 		payload: {
 			status,
 			token
 		}
 	};
 }

export function loginUserFailure(status, err) {
	return {
		type: LOGIN_USER_FAILURE,
		payload: {
			status,
			err
		}
	};
}

/* Logout */
export function logoutUser() {
	localStorage.removeItem('jwt');
}