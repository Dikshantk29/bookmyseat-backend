//// ===== API BASE URL =====
//// Change this:
// const API = 'http://localhost:8080/api';
//
//// To this:
////const API = window.location.origin + '/api';
//
//// ===== GENERIC FETCH HELPERS =====
//async function apiGet(endpoint) {
//    const res = await fetch(`${API}${endpoint}`);
//    if (!res.ok) {
//        const err = await res.json().catch(() => ({ message: res.statusText }));
//        throw new Error(err.message || 'Request failed');
//    }
//    return res.json();
//}
//
//async function apiPost(endpoint, data) {
//    const res = await fetch(`${API}${endpoint}`, {
//        method: 'POST',
//        headers: { 'Content-Type': 'application/json' },
//        body: JSON.stringify(data)
//    });
//    if (!res.ok) {
//        const err = await res.json().catch(() => ({ message: res.statusText }));
//        throw new Error(err.message || 'Request failed');
//    }
//    return res.json();
//}
//
//async function apiPut(endpoint, data) {
//    const res = await fetch(`${API}${endpoint}`, {
//        method: 'PUT',
//        headers: { 'Content-Type': 'application/json' },
//        body: data ? JSON.stringify(data) : undefined
//    });
//    if (!res.ok) {
//        const err = await res.json().catch(() => ({ message: res.statusText }));
//        throw new Error(err.message || 'Request failed');
//    }
//    return res.json();
//}
//
//async function apiDelete(endpoint) {
//    const res = await fetch(`${API}${endpoint}`, { method: 'DELETE' });
//    if (!res.ok) {
//        const err = await res.json().catch(() => ({ message: res.statusText }));
//        throw new Error(err.message || 'Request failed');
//    }
//    // might return text
//    const text = await res.text();
//    try { return JSON.parse(text); } catch { return text; }
//}

// ===== API BASE URL =====
const API = 'http://localhost:8080/api';

// Helper to generate headers with Auth Token
function getHeaders() {
    const headers = { 'Content-Type': 'application/json' };
    const token = localStorage.getItem('bms_token');
    if (token) {
        headers['Authorization'] = `Basic ${token}`;
    }
    return headers;
}

// Helper to handle 401 Unauthorized globally
async function handleResponse(res) {
    if (res.status === 401) {
        logout(); // Auto-logout if token is invalid
        throw new Error('Unauthorized: Please log in again.');
    }
    if (!res.ok) {
        const err = await res.json().catch(() => ({ message: res.statusText }));
        throw new Error(err.message || 'Request failed');
    }

    // Check if the response is empty before parsing JSON (for DELETE requests)
    const text = await res.text();
    return text ? JSON.parse(text) : {};
}

// ===== GENERIC FETCH HELPERS =====
async function apiGet(endpoint) {
    const res = await fetch(`${API}${endpoint}`, { headers: getHeaders() });
    return handleResponse(res);
}

async function apiPost(endpoint, data) {
    const res = await fetch(`${API}${endpoint}`, {
        method: 'POST',
        headers: getHeaders(),
        body: JSON.stringify(data)
    });
    return handleResponse(res);
}

async function apiPut(endpoint, data) {
    const res = await fetch(`${API}${endpoint}`, {
        method: 'PUT',
        headers: getHeaders(),
        body: data ? JSON.stringify(data) : undefined
    });
    return handleResponse(res);
}

async function apiDelete(endpoint) {
    const res = await fetch(`${API}${endpoint}`, {
        method: 'DELETE',
        headers: getHeaders()
    });
    return handleResponse(res);
}
// ===== USER APIs =====
const UserAPI = {
    register: (data) => apiPost('/users/register', data),
    login: (data) => apiPost('/users/login', data),
    getAll: () => apiGet('/users'),
    getById: (id) => apiGet(`/users/${id}`)
};

// ===== CITY APIs =====
const CityAPI = {
    add: (data) => apiPost('/cities', data),
    getAll: () => apiGet('/cities'),
    getById: (id) => apiGet(`/cities/${id}`)
};

// ===== MOVIE APIs =====
const MovieAPI = {
    add: (data) => apiPost('/movies', data),
    getAll: () => apiGet('/movies'),
    getById: (id) => apiGet(`/movies/${id}`),
    search: (title) => apiGet(`/movies/search?title=${encodeURIComponent(title)}`),
    getByGenre: (genre) => apiGet(`/movies/genre/${genre}`),
    getByLanguage: (lang) => apiGet(`/movies/language/${lang}`),
    update: (id, data) => apiPut(`/movies/${id}`, data),
    delete: (id) => apiDelete(`/movies/${id}`)
};

// ===== THEATER APIs =====
const TheaterAPI = {
    add: (data) => apiPost('/theaters', data),
    getAll: () => apiGet('/theaters'),
    getById: (id) => apiGet(`/theaters/${id}`),
    getByCity: (cityId) => apiGet(`/theaters/city/${cityId}`)
};

// ===== SCREEN APIs =====
const ScreenAPI = {
    add: (data) => apiPost('/screens', data),
    getAll: () => apiGet('/screens'),
    getById: (id) => apiGet(`/screens/${id}`),
    getByTheater: (theaterId) => apiGet(`/screens/theater/${theaterId}`)
};

// ===== SEAT APIs =====
const SeatAPI = {
    add: (data) => apiPost('/seats', data),
    getByScreen: (screenId) => apiGet(`/seats/screen/${screenId}`),
    getById: (id) => apiGet(`/seats/${id}`)
};

// ===== SHOW APIs =====
const ShowAPI = {
    add: (data) => apiPost('/shows', data),
    getAll: () => apiGet('/shows'),
    getById: (id) => apiGet(`/shows/${id}`),
    getByMovie: (movieId) => apiGet(`/shows/movie/${movieId}`),
    getByMovieAndDate: (movieId, date) => apiGet(`/shows/movie/${movieId}/date?date=${date}`)
};

// ===== BOOKING APIs =====
const BookingAPI = {
    create: (data) => apiPost('/bookings', data),
    getById: (id) => apiGet(`/bookings/${id}`),
    getByUser: (userId) => apiGet(`/bookings/user/${userId}`),
    cancel: (id) => apiDelete(`/bookings/${id}`),
    getAvailableSeats: (showId) => apiGet(`/bookings/show/${showId}/available-seats`)
};