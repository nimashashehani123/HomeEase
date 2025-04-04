// Toggle mobile menu
const hamburger = document.getElementById('hamburger');
const navLinks = document.querySelector('.nav-links');

hamburger.addEventListener('click', () => {
    navLinks.classList.toggle('active');
});

// Role Toggle Functionality
const roleToggle = document.getElementById('roleToggle');
const roleLabel = document.getElementById('roleLabel');
const providerLoginModal = document.getElementById('providerLoginModal');
const closeModal = document.querySelector('.close');

roleToggle.addEventListener('change', function () {
    if (this.checked) {
        roleLabel.textContent = 'Provider';
        providerLoginModal.style.display = 'flex'; // Show the modal
    } else {
        roleLabel.textContent = 'Customer';
        providerLoginModal.style.display = 'none'; // Hide the modal
    }
});

// Close Modal
closeModal.addEventListener('click', () => {
    providerLoginModal.style.display = 'none';
    roleToggle.checked = false; // Reset the toggle
    roleLabel.textContent = 'Customer';
});

// Close Modal when clicking outside
window.addEventListener('click', (event) => {
    if (event.target === providerLoginModal) {
        providerLoginModal.style.display = 'none';
        roleToggle.checked = false; // Reset the toggle
        roleLabel.textContent = 'Customer';
    }
});

// Form Submission
function login() {
    console.log("-----------------------------------------")
    const email = document.getElementById('providerEmail').value;
    const password = document.getElementById('providerPassword').value;
    console.log(email)
    console.log(password)

    $.ajax({
        url: 'http://localhost:8080/api/v1/auth/authenticate',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            email: email,
            password: password
        }),
        success: function (response) {
            alert("Login successful");
            localStorage.setItem("token", response.data.token);
            console.log(response.data.token)
            // Assuming the token is stored in localStorage
            const token = localStorage.getItem("token");

            if (token) {
                try {
                    // Decode the token
                    const decodedToken = jwt_decode(token);

                    // Extract the role from the token payload
                    const role = decodedToken.role; // Assuming the role is stored in the "role" claim

                    console.log("Decoded Token:", decodedToken);
                    console.log("User Role:", role);

                    // Perform actions based on the role
                    if (role === "SERVICE_PROVIDER") {
                        console.log("User is a Service Provider");
                        window.location.href = "../view/providerdashboard.html";
                        closeModal.addEventListener('click', () => {
                            providerLoginModal.style.display = 'none';
                            roleToggle.checked = false; // Reset the toggle
                            roleLabel.textContent = 'Customer';
                        });
                        setTimeout(function() {
                            window.location.href = "../view/services.html"; // Replace with your login page URL
                        }, 500);
                        // Redirect or show provider-specific content
                    } else if (role === "CUSTOMER") {
                        console.log("User is a Customer");
                        // Redirect or show customer-specific content
                    }  else if (role === "ADMIN") {
                        console.log("User is a Customer");
                        // Redirect or show customer-specific content
                    } else {
                        console.log("Unknown role");
                    }
                } catch (error) {
                    console.error("Failed to decode token:", error);
                }
            } else {
                console.error("No token found in localStorage");
            }

        },

        error: function (xhr, status, error) {
            alert("Login failed")
        }
    });
}
// Contact Form Submission
document.getElementById('contactForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const message = document.getElementById('message').value;

    // Basic validation
    if (!name || !email || !message) {
        alert('Please fill out all fields.');
        return;
    }

    // Simulate form submission
    alert('Message sent successfully!');
    document.getElementById('contactForm').reset(); // Clear the form
});