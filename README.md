# 🏨 Hostel Management System

A **Hostel Management System** developed using **Java, JDBC, and MySQL** to manage hostel student records efficiently. The application allows users to perform database operations such as inserting, updating, retrieving, and managing student accommodation details through a menu-driven Java application.

## 🚀 Features

- **Add Student:** Insert new student records into the database.
- **View Students:** Display all registered hostel students.
- **Update Student:** Update student details using their Student ID.
- **Search Records:** Retrieve student information from the database.
- **Fee Management:** Retrieve students whose hostel fees exceed ₹50,000.
- **Sorting:** Display student records according to hostel fees in descending order.
- **Database Connectivity:** Establish a connection between Java and MySQL using JDBC.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Database:** MySQL
- **Connectivity:** JDBC (Java Database Connectivity)
- **IDE:** VS Code
- **Version Control:** Git & GitHub

## 🗄️ Database Structure

**Database Name:** `hostel_db`

**Table Name:** `HostelStudent`

| Column | Data Type | Description |
|---|---|---|
| student_id | INT | Primary key |
| student_name | VARCHAR(100) | Student name |
| roll_number | VARCHAR(50) | University roll number |
| hostel_name | VARCHAR(100) | Hostel name |
| room_number | INT | Allocated room number |
| fee | DOUBLE | Hostel fee |

## 📂 Project Structure

```text
HostelManagementSystem/
│
├── lib/
│   └── mysql-connector-j.jar
│
├── HostelManagement.java
├── README.md
└── .gitignore
```

## ⚙️ Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/shritijjaiswal27-collab/HostelManagementSystem.git
```

### 2. Navigate to the Project Directory

```bash
cd HostelManagementSystem
```

### 3. Configure MySQL

Create the database:

```sql
CREATE DATABASE hostel_db;
USE hostel_db;
```

Create the student table:

```sql
CREATE TABLE HostelStudent (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100),
    roll_number VARCHAR(50),
    hostel_name VARCHAR(100),
    room_number INT,
    fee DOUBLE
);
```

### 4. Configure JDBC

- Download the MySQL Connector/J driver.
- Place the connector JAR file inside the `lib` folder.
- Configure your MySQL username and password in the Java source code.

### 5. Compile and Run

On macOS or Linux:

```bash
javac -cp ".:lib/*" HostelManagement.java
```

Run the application:

```bash
java -cp ".:lib/*" HostelManagement
```

## 🎯 Learning Outcomes

Through this project, I gained practical experience in:

- Java Database Connectivity (JDBC).
- Connecting Java applications with MySQL.
- Performing CRUD operations.
- Writing SQL queries for filtering and sorting.
- Handling database records using Java.
- Managing database connections and exceptions.

## 👨‍💻 Author

**Shritij Jaiswal**

GitHub: [@shritijjaiswal27-collab](https://github.com/shritijjaiswal27-collab)

---

⭐ If you find this project useful, consider giving it a star!
