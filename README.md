# 📊 Smart Resource Booking & Management System (SRBMS)

## 📌 Project Overview
SRBMS is a console-based Java application designed to manage the efficient booking and allocation of shared resources. It supports multiple user roles (Admin, Manager, and Regular User) with role-based access control, booking conflict checks, and detailed reporting features.

This project demonstrates key concepts of Core Java such as Object-Oriented Programming (OOP), Collections, Exception Handling, Regex, and Java Time API, and is suitable for academic and training purposes.

## 🚀 Features
### 🔐 User Authentication & Role Management
Secure login and registration system

Role-based access for Admin, Manager, and Regular User

### 🏢 Resource Management
Add, view, edit, and delete resources

Support for resource types (rooms, books, etc.)

### 📅 Booking System
Book available resources with time duration input

Prevents double-booking through conflict checks


### 📊 Reporting & Usage Insights
View all bookings by resource or user

Generate usage statistics (optional enhancement)

### 📁 Project Structure
```pgsql

│
├── SRBMS/
│ ├── Driver/ # Main application driver
│ │ └── DriverClass.java
| |
│ ├── Entity/ 
│ │ └── Admin.java
│ │ └── Booking.java
│ │ └── RegularUser.java
│ │ └── ResourceMan.java
│ │ └── Room.java
│ │ └── User.java
| |
│ ├── Repository/ # In-memory database
│ │ └── Database.java
| |
│ └── Service/ 
│ └── BookingService.java
│ └── ReportService.java
│ └── ResourceService.java
│ └── UserService.java
```

## 🧰 Technologies Used
Language: Java (Core Java)

Concepts: OOP, Java Collections, Exception Handling, Java Time API

IDE: IntelliJ IDEA / Eclipse

Storage: In-memory (can be extended to file-based)

## 📸 Sample Output
![image](https://github.com/user-attachments/assets/bf3f92ba-cff4-4528-818d-479f1e698acc)


## 📚 What You’ll Learn

This project is a great way to practice and learn core Java concepts. By exploring or contributing to this project, you will:

- ✅ **Core Java Programming:** Learn the fundamentals of OOP, exception handling, and collections in Java.
- ✅ **Role-Based Access Control:** Implement user roles (Admin, Manager, User) with specific permissions.
- ✅ **File Handling:** Understand file I/O and serialization for storing and retrieving data.
- ✅ **System Design:** Build modular systems using service classes and design patterns like Singleton.
- ✅ **Booking System Implementation:** Develop a resource booking system that handles scheduling and conflicts.
- ✅ **Reporting and Analytics:** Implement reporting features to generate insights on bookings and resource usage.
- ✅ **Console-based UI:** Design an intuitive console-based user interface for different types of users.


## 🛠 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/SRBMS.git
   
2. Import the project into IntelliJ IDEA, Eclipse, or any Java IDE.

3. Compile and Run: Run the App.java file to start the application.

## 📌 User Roles Overview

Admin->	Full access: Manage users, resources, bookings, and reports
Manager->	Manage resources and booking approvals
User->	View and book available resources

## 📈 Future Enhancements (Optional)
File-based or database storage

GUI interface using JavaFX or Swing

Email notification simulation

Advanced analytics and charts

## 📜 License
This project is open-source and free to use for educational purposes.

## 🙋‍♂️ Author
Devansh
devanshdmp15@gmail.com

