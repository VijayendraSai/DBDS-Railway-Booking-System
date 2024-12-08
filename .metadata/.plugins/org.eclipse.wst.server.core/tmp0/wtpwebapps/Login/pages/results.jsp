<!DOCTYPE html>
<html>
<head>
    <title>Search Results</title>
</head>
<body>
    <h1>Search Results</h1>
    <table>
        <thead>
            <tr>
                <th>Train Name</th>
                <th>Origin Departure</th>
                <th>Destination Arrival</th>
                <th>Fare</th>
                <th>Details</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="schedule" items="${schedules}">
                <tr>
                    <td>${schedule.train.name}</td>
                    <td>${schedule.originDeparture}</td>
                    <td>${schedule.destinationArrival}</td>
                    <td>${schedule.fare}</td>
                    <td><a href="TrainScheduleServlet?scheduleId=${schedule.scheduleId}">View Details</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
