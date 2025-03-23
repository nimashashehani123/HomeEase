
// Toggle mobile menu
const hamburger = document.getElementById('hamburger');
const navLinks = document.querySelector('.navbar .nav-links');

hamburger.addEventListener('click', () => {
    navLinks.classList.toggle('active');
});

// Form Validation
function login() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

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
            // kamthi navigation ekak danna

        },

        error: function (xhr, status, error) {
            alert("Login failed")
        }
    });
}
