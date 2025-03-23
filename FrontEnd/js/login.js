
// Toggle mobile menu
const hamburger = document.getElementById('hamburger');
const navLinks = document.querySelector('.navbar .nav-links');

hamburger.addEventListener('click', () => {
    navLinks.classList.toggle('active');
});

// Form Validation
document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault(); // Prevent form submission

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    if (!email || !password) {
        alert('Please fill out all fields.');
    } else {
        alert('Login successful!');
        // Redirect to another page or perform further actions
    }
});
