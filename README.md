Microservices Project

This project is a microservices-based course registration portal. It allows students to register for courses, mark them as complete or ongoing, edit their status, and receive notifications about upcoming or ongoing courses. This helps users keep track of their courses effectively.

A video demonstration of this project is included in the repository as video-demo.mp4.

Prerequisites

To run this project, ensure you have the following installed on your system:

- Java Development Kit (JDK) (version 17 or later)
- IntelliJ IDEA (or any IDE supporting Spring Boot projects)
- Node.js (version 16 or later) and npm
- XAMPP (or any setup for MySQL and Apache server)
- Git

Project Structure

Services:

1. Eureka Server: Acts as the service registry for microservices.
2. Course Service: Handles course-related data and interactions.
3. Notification Service: Sends notifications about courses starting soon or already started.
4. User Service: Manages student-related data and course registrations.
5. Frontend (my-app): A React-based user interface.

Setup Instructions

1. Clone the Repository:
   git clone https://github.com/Nimai31/Microservices-Project.git  
   cd Microservices-Project

2. Set Up the Database:
   - Open XAMPP and start MySQL and Apache servers.
   - Navigate to http://localhost/phpmyadmin.
   - Create a database named coursedb.
   - Note: The project uses MySQL on port 3307 by default. If your MySQL server runs on port 3306, update the port in the YAML configuration files of the services:  
     courseservice/src/main/resources/application.yml  
     notificationservice/src/main/resources/application.yml  
     userservice/src/main/resources/application.yml  
     Change 3307 to 3306.

3. Run the Backend Services:
   - Open the project in IntelliJ IDEA.
   - Start the Eureka Server first by running the `EurekaServerApplication` class located in the eurekaserver directory. Once started, you can view the Eureka Server dashboard at http://localhost:8761. As you start each service, they will appear in the Eureka dashboard.
   - Start the other services:  
     Course Service: Run `CourseServiceApplication` from the courseservice directory.  
     Notification Service: Run `NotificationServiceApplication` from the notificationservice directory.  
     User Service: Run `UserServiceApplication` from the userservice directory.

4. Set Up and Run the Frontend:
   - Navigate to the `my-app` directory: cd my-app
   - Install the required dependencies:  
     npm install react-bootstrap bootstrap  
     npm install react-router-dom  
     npm install axios
   - Start the React application: npm start
   - Open the application in your browser at http://localhost:3000.

Key Notes

- Database Configuration:
  Ensure the database `coursedb` is created.  
  Update the MySQL port in the application.yml files if necessary.

- Service Dependency:
  The Eureka Server must be running before starting the other services.  
  Services will be registered in the Eureka Server dashboard at http://localhost:8761.

- Frontend:
  Ensure all dependencies are installed in my-app before running the frontend.

Demo Video

A video demonstration of this project is included in the repository as video-demo.mp4.

Troubleshooting

- Database Connection Issues:  
  Verify MySQL is running and the port is correctly configured.  
  Ensure coursedb exists and is accessible.

- Eureka Server Not Running:  
  Check if the Eureka Server application is started first.

- Frontend Issues:  
  Ensure npm install has been executed in the my-app directory.

