# 🚄 Online Railway Reservation System

## 📘 Overview

This project is a full-stack **relational database application** for managing an **online railway booking system**. It supports multiple user roles—**Customers**, **Customer Representatives**, and **Admins**—and enables secure, efficient management of train schedules, reservations, and customer support through a web interface using **Java**, **JSP**, **MySQL**, and **JDBC**.

---

## 🎥 Demo Video

**[Watch Demo](https://drive.google.com/file/d/1vJl2Wz4_D5HXab5tgMd4yMkdqDO8hwT9/view?usp=share_link)**

---

## 🌐 Technologies Used

- **Frontend**: React
- **Backend**: Spring Boot
- **Database**: MySQL
- **Web Server**: Apache Tomcat (localhost setup)

---

## 🧑‍💼 System Users & Roles

### 🔹 Customers
- Browse train schedules
- Make **One-Way** or **Round-Trip** reservations
- View current and past reservation history
- Cancel existing reservations
- Ask questions via a forum-like feature

### 🔹 Customer Representatives
- Manage train schedules
- Respond to customer queries
- View customer reservation lists per transit line or station
- Generate station-specific train schedules (origin/destination)

### 🔹 Admin/Manager
- Manage representative accounts (Add/Edit/Delete)
- Generate monthly revenue reports
- View reservations filtered by customer or transit line
- Identify top-performing customers and transit lines

---

## 🧾 Key Data Entities

| Entity         | Attributes                                  |
|----------------|---------------------------------------------|
| **Train**      | ID, transit line, stops, schedule           |
| **Station**    | ID, name, city, state                       |
| **Schedule**   | Train ID, origin, destination, fare, times  |
| **Reservation**| Reservation ID, fare, date, customer, train |
| **Customer**   | Name, Email, Username, Password             |
| **Employee**   | SSN, Name, Role, Username, Password         |

---

## 🧠 Features

- Search train schedules by **origin/destination/date**
- Real-time reservation with fare computation (including discounts for children, seniors, and disabled)
- Role-based access control for **Admins**, **Representatives**, and **Customers**
- Multi-tier fare logic with round-trip fare calculation
- Monthly revenue analytics and usage reports
- Built-in forum for customer support communication

---

## 🔐 User Credentials

### Customer
- **Username**: `john`  
- **Password**: `john`

### Admin
- **Username**: `bob`  
- **Password**: `bob`

### Representative
- **Username**: `mark`  
- **Password**: `mark`

---

## ⚙️ Configuration

### 🔑 MySQL Database Credentials

1. Navigate to the file:

