import "./App.css";
import { BrowserRouter, Routes, Route, Router } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import Register from "./pages/Register";
import LoginCustomer from "./pages/LoginCustomer";
import SearchTrainSchedule from "./pages/SearchTrainSchedule";
import TrainBooking from "./pages/TrainBooking";
import MyReservations from "./pages/MyReservations";
import CancelReservation from "./pages/CancelReservation";
import CustomerService from "./pages/CustomerService";
import SearchQuestionService from "./pages/SearchQuestionService";
import SendQuestionService from "./pages/SendQuestionService";
import HomeRepresentative from "./pages/HomeRepresentative";
import LoginEmployee from "./pages/LoginEmployee";
import HomeAdmin from "./pages/HomeAdmin";
import ReplyService from "./pages/ReplyService";
import StationSchedules from "./pages/StationSchedules";
import CustomerBookingsTransitAndDate from "./pages/CustomerBookingsTransitAndDate";
import AddRepresentative from "./pages/AddRepresentative";
import EditRepresentative from "./pages/EditRepresentative";
import DeleteRepresentative from "./pages/DeleteRepresentative";
import SalesReport from "./pages/SalesReport";
import ReservationByTransitLine from "./pages/ReservationByTransitLine";
import ReservationsByCustomer from "./pages/ReservationsByCustomer";
import RevenueByTransitLine from "./pages/RevenueByTransitLine";
import RevenueByCustomer from "./pages/RevenueByCustomer";
import BestCustomer from "./pages/BestCustomer";
import Top5TransitLines from "./pages/Top5TransitLines";
import SortTrainSchedule from "./pages/SortTrainSchedule";
import AllRepresentative from "./pages/AllRepresentative";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/home" element={<Home />} />
          <Route path="/home-representative" element={<HomeRepresentative />} />
          <Route path="/home-admin" element={<HomeAdmin />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<LoginCustomer />} />
          <Route path="/login-employee" element={<LoginEmployee />} />
          <Route
            path="/search-train-schedule"
            element={<SearchTrainSchedule />}
          />
          <Route path="/sort-train-schedule" element={<SortTrainSchedule />} />
          <Route path="/train-booking" element={<TrainBooking />} />
          <Route path="/my-reservations" element={<MyReservations />} />
          <Route path="/cancel-reservation" element={<CancelReservation />} />
          <Route path="/customer-service" element={<CustomerService />} />
          <Route path="/search-question" element={<SearchQuestionService />} />
          <Route path="/send-question" element={<SendQuestionService />} />
          <Route path="/reply-service" element={<ReplyService />} />
          <Route path="/station-schedules" element={<StationSchedules />} />
          <Route
            path="/customer-bookings-transit-date"
            element={<CustomerBookingsTransitAndDate />}
          />

          <Route path="/all-representative" element={<AllRepresentative />} />
          <Route path="/add-representative" element={<AddRepresentative />} />
          <Route path="/edit-representative" element={<EditRepresentative />} />
          <Route
            path="delete-representative"
            element={<DeleteRepresentative />}
          />
          <Route path="/sales-report" element={<SalesReport />} />
          <Route
            path="/reservations-transit-line"
            element={<ReservationByTransitLine />}
          />
          <Route
            path="/reservations-customer"
            element={<ReservationsByCustomer />}
          />
          <Route
            path="/revenue-transit-line"
            element={<RevenueByTransitLine />}
          />
          <Route path="/revenue-customer" element={<RevenueByCustomer />} />
          <Route path="/get-best-customer" element={<BestCustomer />} />
          <Route path="/top-trainsit-lines" element={<Top5TransitLines />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
