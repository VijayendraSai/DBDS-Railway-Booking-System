<!DOCTYPE html>
<html>
<head>
    <title>Train Schedule Details</title>
</head>
<body>
    <h1>Train Schedule Details</h1>
    <p>Train: ${schedule.train.name}</p>
    <p>Departure: ${schedule.originDeparture}</p>
    <p>Arrival: ${schedule.destinationArrival}</p>
    <p>Fare: ${schedule.fare}</p>

    <h2>Stops</h2>
    <table>
        <thead>
            <tr>
                <th>Stop Order</th>
                <th>Station</th>
                <th>Arrival Time</th>
                <th>Departure Time</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="stop" items="${stops}">
                <tr>
                    <td>${stop.stopOrder}</td>
                    <td>${stop.station.name}</td>
                    <td>${stop.arrivalTime}</td>
                    <td>${stop.departureTime}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
