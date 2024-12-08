<!DOCTYPE html>
<html>
<head>
    <title>Search Train Schedules</title>
</head>
<body>
    <h1>Search for Train Schedules</h1>
    <form action="SearchServlet" method="get">
        Origin: <input type="text" name="origin" required><br>
        Destination: <input type="text" name="destination" required><br>
        Date of Travel: <input type="date" name="date" required><br>
        <input type="submit" value="Search">
    </form>
</body>
</html>
