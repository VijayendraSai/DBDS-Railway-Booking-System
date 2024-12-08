<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Employee</title>
</head>
<body>
    <h1>Employee Registration</h1>
    <form action="registerEmployee" method="post">
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" required><br><br>

        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" required><br><br>

        <label for="username">Username:</label>
        <input type="text" name="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" name="password" required><br><br>

        <label for="ssn">SSN:</label>
        <input type="text" name="ssn" required><br><br>

        <label for="role">Role:</label>
        <input type="text" name="role" required><br><br>

        <button type="submit">Register</button>
    </form>
</body>
</html>
