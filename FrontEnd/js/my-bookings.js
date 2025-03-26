$(document).ready(function() {

    // Check authentication
    const token = localStorage.getItem("token");
    if (!token) {
        window.location.href = "/login";
        return;
    }

    // Load bookings on page load
    loadBookings();

    // Set up event listeners
    $('#apply-filters').click(loadBookings);
    $('#reset-filters').click(resetFilters);
    $('#refresh-btn').click(loadBookings);

    // Initialize date pickers
    const today = new Date().toISOString().split('T')[0];
    $('#date-to').val(today);
});

async function getUserIdFromEmail(userEmail) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: 'http://localhost:8080/api/v1/users/getidbyemail',
            type: 'GET',
            data: { email: userEmail },
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            success: function(response) {
                if (response.code === 200 && response.data) {
                    resolve(response.data.data);
                } else {
                    reject(new Error(response.message || "Failed to get user ID"));
                }
            },
            error: function(xhr) {
                reject(new Error(xhr.responseJSON?.message || "Failed to fetch user ID"));
            }
        });
    });
}

async function loadBookings() {
    showLoadingState();

    const status = $('#status-filter').val();
    const dateFrom = $('#date-from').val();
    const dateTo = $('#date-to').val();

    // Determine endpoint based on user role
    const userRole = getUserRoleFromToken();
    const userEmail = getUserEmailFromToken();
    const userId = await getUserIdFromEmail(userEmail);
    console.log(userId);
    if (!userId) {
        throw new Error("Failed to get user ID");
    }
    const endpoint = userRole === 'SERVICE_PROVIDER'
        ? `http://localhost:8080/api/v1/bookings/provider/${userId}`
        : `http://localhost:8080/api/v1/bookings/customer/${userId}`;

    $.ajax({
        url: endpoint,
        method: 'GET',
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        data: {
            status: status === 'ALL' ? null : status,
            fromDate: dateFrom,
            toDate: dateTo
        },
        success: function(response) {
            if (response.code === 200 && response.data.data && response.data.data.length > 0) {
                displayBookings(response.data.data);
            } else {
                showEmptyState();
            }
        },
        error: function(xhr) {
            if (xhr.status === 401) {
                alert("Session expired. Please login again.");
                window.location.href = "/login";
            } else {
                alert("Failed to load bookings. Please try again.");
                showEmptyState();
            }
        }
    });
}

function displayBookings(bookings) {
    const container = $('#bookings-container');
    container.empty();

    bookings.forEach(booking => {
        const bookingDate = new Date(booking.bookingDateTime);
        const formattedDate = bookingDate.toLocaleDateString();
        const formattedTime = bookingDate.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

        container.append(`
            <div class="booking-card card mb-3">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-start mb-2">
                        <div>
                            <h5 class="card-title mb-1">${booking.service.serviceName}</h5>
                            <p class="text-muted small mb-2">Booking #${booking.bookingId}</p>
                        </div>
                        <span class="status-badge badge ${getStatusBadgeClass(booking.status)}">
                            ${booking.status}
                        </span>
                    </div>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="d-flex align-items-center mb-2">
                                <i class="bi bi-calendar-date me-2"></i>
                                <span>${formattedDate} at ${formattedTime}</span>
                            </div>
                            <div class="d-flex align-items-center">
                                <i class="bi bi-person me-2"></i>
                                <span>${userRole === 'SERVICE_PROVIDER' ? 'Customer' : 'Provider'}: ${userRole === 'SERVICE_PROVIDER' ? booking.customer.name : booking.service.serviceProviderName}</span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="d-flex align-items-center mb-2">
                                <i class="bi bi-cash-coin me-2"></i>
                                <span>Rs.${booking.service.fixedPrice.toFixed(2)}</span>
                            </div>
                            <div class="d-flex align-items-center">
                                <i class="bi bi-clock me-2"></i>
                                <span>${booking.hoursWorked ? booking.hoursWorked + ' hours' : 'Duration not set'}</span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="d-flex justify-content-between align-items-center">
                        <a href="/booking-details.html?id=${booking.bookingId}" class="btn btn-sm btn-outline-primary">
                            <i class="bi bi-eye"></i> View Details
                        </a>
                        ${generateActionButtons(booking)}
                    </div>
                </div>
            </div>
        `);
    });
}

function generateActionButtons(booking) {
    const userRole = getUserRoleFromToken();
    let buttons = '';

    if (userRole === 'CUSTOMER' && booking.status === 'PENDING') {
        buttons = `
            <button class="btn btn-sm btn-danger" onclick="cancelBooking(${booking.bookingId})">
                <i class="bi bi-x-circle"></i> Cancel
            </button>
        `;
    } else if (userRole === 'SERVICE_PROVIDER') {
        if (booking.status === 'PENDING') {
            buttons = `
                <div class="btn-group">
                    <button class="btn btn-sm btn-success" onclick="updateBookingStatus(${booking.bookingId}, 'ACCEPTED')">
                        <i class="bi bi-check-circle"></i> Accept
                    </button>
                    <button class="btn btn-sm btn-danger" onclick="updateBookingStatus(${booking.bookingId}, 'CANCELLED')">
                        <i class="bi bi-x-circle"></i> Reject
                    </button>
                </div>
            `;
        } else if (booking.status === 'ACCEPTED') {
            buttons = `
                <div class="btn-group">
                    <button class="btn btn-sm btn-primary" onclick="updateBookingStatus(${booking.bookingId}, 'COMPLETED')">
                        <i class="bi bi-check-all"></i> Complete
                    </button>
                </div>
            `;
        }
    }

    return buttons;
}

function updateBookingStatus(bookingId, status) {
    if (status === 'CANCELLED' && !confirm("Are you sure you want to cancel this booking?")) {
        return;
    }

    $.ajax({
        url: `/api/v1/bookings/${bookingId}/status`,
        method: 'PATCH',
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token"),
            "Content-Type": "application/json"
        },
        data: JSON.stringify({ status: status }),
        success: function() {
            loadBookings(); // Refresh the list
        },
        error: function(xhr) {
            alert(xhr.responseJSON?.message || "Failed to update status");
        }
    });
}

function cancelBooking(bookingId) {
    updateBookingStatus(bookingId, 'CANCELLED');
}

function resetFilters() {
    $('#status-filter').val('ALL');
    $('#date-from').val('');
    $('#date-to').val(new Date().toISOString().split('T')[0]);
    loadBookings();
}

function showLoadingState() {
    $('#bookings-container').html(`
        <div class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
            <p class="mt-2">Loading your bookings...</p>
        </div>
    `);
    $('#empty-state').hide();
}

function showEmptyState() {
    $('#bookings-container').empty();
    $('#empty-state').show();
}

// Helper functions
function getStatusBadgeClass(status) {
    const classes = {
        'PENDING': 'bg-warning',
        'ACCEPTED': 'bg-primary',
        'COMPLETED': 'bg-success',
        'CANCELLED': 'bg-secondary'
    };
    return classes[status] || 'bg-light text-dark';
}

function getUserRoleFromToken() {
    const token = localStorage.getItem("token");
    if (!token) return null;

    try {
        const decoded = jwt_decode(token);
        return decoded.role; // Assuming your JWT has a 'role' claim
    } catch (e) {
        console.error("Error decoding token:", e);
        return null;
    }
}
function getUserEmailFromToken() {
    const token = localStorage.getItem("token");
    if (!token) return null;

    try {
        const decoded = jwt_decode(token);
        return decoded.sub; // Assuming your JWT has a 'role' claim
    } catch (e) {
        console.error("Error decoding token:", e);
        return null;
    }


}