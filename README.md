# Korera Project – Resource Management System for Construction Projects  

Welcome to **Korera**, a resource management system designed to help construction companies efficiently manage their project resources, including allocation, tracking, and reporting. This project leverages **Angular**, **Spring Boot**, and **REST APIs** to deliver a scalable and user-friendly experience for both project managers and team members.

---

## **Features**

### **Frontend (Angular)**  
- **Dynamic Resource Catalog**: View and manage available resources across multiple projects.
- **Project-Specific Templates**: Display and edit resources and related details based on selected projects.
- **CRUD Operations**: Create, update, and delete projects, resources, and templates.
- **User Authentication**: Secure login and registration with JWT-based access control.
- **Multi-Page Navigation**: Smooth navigation between pages using Angular Router with stateful data handling.
- **Responsive UI**: Built with **HTML**, **CSS**, and **TypeScript** for a clean and user-friendly interface.

### **Backend (Spring Boot)**  
- **Entity Relationships**: Efficient data modeling with entities such as `Template`, `Project`, and `ResourceDetail`.
- **REST API Services**: Robust endpoints to handle project and resource management operations.
- **Database Integration**: Uses **PostgreSQL** to store and manage resource, project, and template data.
- **Authentication & Authorization**: Secure API with role-based access using Spring Security and JWT tokens.

---

## **Project Structure**

### **Frontend**
```
src/
  ├── app/
  │   ├── components/
  │   │   ├── project/         # Project management component
  │   │   ├── formula/         # Formula component for displaying selected resources
  │   │   └── shared/          # Reusable components (modals, tables, dropdowns)
  │   ├── services/
  │   │   ├── auth.service.ts  # Authentication service
  │   │   ├── project.service.ts  # Project data handling
  │   │   └── template.service.ts  # Resource and template data handling
  └── main.ts
```

### **Backend**
```
src/
  ├── main/
  │   ├── java/com/itlize/korera/
  │   │   ├── model/         # Entity classes (Project, Template, ResourceDetail)
  │   │   ├── controller/    # REST API endpoints
  │   │   └── service/       # Business logic and service layer
  └── resources/
      └── application.yml     # Configuration (database, security, etc.)
```

---

## **Getting Started**

### **Prerequisites**
- **Node.js** (v18+)
- **Angular CLI** (v15+)
- **Java** (JDK 17+)
- **Maven** (for backend)
- **PostgreSQL** (v14+)

### **Setup Steps**

#### **1. Clone the Repository**
```bash
git clone https://github.com/your-repo-name/korera.git
cd korera
```

#### **2. Frontend Setup**
```bash
cd frontend
npm install
ng serve
```
The Angular app will be available at [http://localhost:4200](http://localhost:4200).

#### **3. Backend Setup**
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
The backend API will be available at [http://localhost:8080](http://localhost:8080).

#### **4. Database Setup**
- Create a PostgreSQL database named `korera`.
- Update the `application.yml` file with your database credentials.

---

## **Usage**

1. **Login** or **Register** a new account.
2. Create a project and define its resources.
3. Manage resources and templates through the UI.
4. Submit and export data for different projects.

---

## **Technologies Used**

### **Frontend**
- **Angular** (HTML, CSS, TypeScript)
- **Bootstrap** (Responsive design)
- **RxJS** (State management and asynchronous operations)

### **Backend**
- **Spring Boot** (Java-based web framework)
- **Spring Data JPA** (ORM for database interactions)
- **Spring Security** (Authentication and authorization)
- **PostgreSQL** (Relational database)

---

## **Future Improvements**

- **Real-Time Collaboration**: Enable simultaneous multi-user editing and real-time data synchronization.
- **Enhanced Reporting**: Add custom reports and data visualization for better insights.
- **Email Notifications**: Implement notifications for project updates and deadlines.
- **Audit Logging**: Track all changes made to resources and projects.

---

## **Contributors**

- **Sherry** (Frontend & Backend Development)

---

## **License**

This project is licensed under the [MIT License](LICENSE).

---

## **Screenshots**

### **Project Management Page**
![Project Page Screenshot](path/to/project-page.png)

### **Formula Page**
![Formula Page Screenshot](path/to/formula-page.png)

---

Thank you for checking out **Korera**! Contributions and feedback are welcome! 😊  
Feel free to submit issues and pull requests to improve the system.
