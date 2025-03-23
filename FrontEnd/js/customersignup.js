$(document).ready(function () {
    // Save button click event
    $('#save-btn').on('click', function (event) {
        event.preventDefault(); // Prevent form submission

        // Validate confirm password
        const password = $('#password').val();
        const confirmPassword = $('#confirm-password').val();

        if (password !== confirmPassword) {
            $('#confirmPasswordError').show(); // Show error message
            $('#confirm-password').css('border-color', 'red'); // Highlight the input field
            return; // Stop further execution
        } else {
            $('#confirmPasswordError').hide(); // Hide error message
            $('#confirm-password').css('border-color', '#ccc'); // Reset input field style
        }

        // Create user object
        const user = {
            fullName: $('#fullname').val(),
            email: $('#email').val(),
            phone: $('#phone').val(),
            address: $('#address').val(),
            password: password, // Only send the password (not confirmPassword)
        };

        // Submit form data using AJAX
        $.ajax({
            url: 'http://localhost:8080/api/v1/users/register',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            data: JSON.stringify(user),
            success: function (response) {
                alert('User Saved');
                if (response && response.data) {
                    let token = response.data.token;
                    console.log('Token:', token);

                    // Store token in localStorage
                    localStorage.setItem('authToken', token);
                } else {
                    console.log('No token received.');
                }
            },
            error: function (err) {
                console.log(err);
            },
        });
    });
});