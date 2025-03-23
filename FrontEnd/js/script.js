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
document.getElementById('providerLoginForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const email = document.getElementById('providerEmail').value;
    const password = document.getElementById('providerPassword').value;

    // Basic validation
    if (!email || !password) {
        alert('Please fill out all fields.');
        return;
    }

    // Simulate backend login
    alert('Provider login successful!');
    providerLoginModal.style.display = 'none'; // Close the modal
    roleToggle.checked = false; // Reset the toggle
    roleLabel.textContent = 'Customer';
});

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