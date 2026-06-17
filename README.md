# B2B Invoice Management Platform

A web-based B2B Invoice Management System built using Spring Boot, Spring Security, JWT Authentication, PostgreSQL, and JPA.

The application allows vendors to submit invoices, finance reviewers to approve or reject invoices, and administrators to manage the overall system.

---

## Features

### Authentication & Authorization

* Vendor Registration
* User Login
* JWT Authentication
* Role-Based Access Control (RBAC)

Roles:

* ROLE_VENDOR
* ROLE_REVIEWER
* ROLE_ADMIN

---

## Vendor Features

### Submit Invoice

Vendor can submit invoices with:

* Invoice Number
* Amount
* Due Date
* Description

Status is automatically set to:

```text
SUBMITTED
```

### Track Invoice Status

Vendor can view all submitted invoices and track status:

* SUBMITTED
* APPROVED
* REJECTED
* PAID

If an invoice is rejected, the rejection reason is displayed.

---

## Reviewer Features

### Review Invoices

Reviewer can view all submitted invoices.

### Approve Invoice

Workflow:

```text
SUBMITTED
    ↓
APPROVED
```

Stores:

* Approved By
* Approved Date

### Reject Invoice

Workflow:

```text
SUBMITTED
    ↓
REJECTED
```

Stores:

* Rejection Reason
* Reviewer
* Rejected Date

---

## Admin Features

* Admin Dashboard
* Manage Users
* Monitor Invoices
* Create Reviewers (Upcoming)

---

## Tech Stack

### Backend

* Java 24
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* JWT Authentication
* Maven

### Database

* PostgreSQL

### Validation

* Jakarta Validation

### Utilities

* Lombok

---

## Project Structure

```text
src/main/java/com/example/demo

├── Controllers
│   ├── AuthController
│   ├── VendorController
│   ├── ReviewerController
│   ├── AdminController
│
├── Services
│   ├── AuthService
│   ├── InvoiceService
│   ├── JwtService
│   ├── CustomUserDetailsService
│
├── Repository
│   ├── UserRepository
│   ├── InvoiceRepository
│   ├── InvoiceRejectionRepository
│
├── Models
│   ├── User
│   ├── Invoice
│   ├── InvoiceRejection
│   ├── Payment
│   ├── UserRole
│   └── InvoiceStatus
│
├── Dto
│
├── Security
│   └── JwtAuthenticationFilter
│
├── Configuration
│   └── SecurityConfig
```

---

## Database Design

### Users

Stores:

* Vendors
* Reviewers
* Admins

### Invoices

Stores:

* Invoice Information
* Status
* Vendor Details

### Invoice Rejections

Stores:

* Rejection Reason
* Reviewer Information

### Payments

Stores payment details for approved invoices.

---

## Invoice Workflow

```text
Vendor Creates Invoice
          ↓
      SUBMITTED
          ↓
   Reviewer Review
      ↙       ↘
 APPROVED   REJECTED
      ↓
     PAID
```

---

## API Endpoints

### Authentication

```http
POST /api/auth/register
POST /api/auth/login
```

### Vendor

```http
GET  /api/vendor/dashboard
POST /api/vendor/invoices
GET  /api/vendor/invoices
```

### Reviewer

```http
GET /api/reviewer/dashboard
GET /api/reviewer/invoices

PUT /api/reviewer/invoices/{id}/approve

PUT /api/reviewer/invoices/{id}/reject
```

### Admin

```http
GET /api/admin/dashboard
```

---

## Current Progress

### Completed

* Project Setup
* Database Design
* JWT Authentication
* RBAC
* Vendor Registration
* Vendor Login
* Invoice Submission
* Invoice Tracking
* Reviewer Invoice Listing
* Invoice Approval
* Invoice Rejection

### Upcoming

* Payment Tracking
* Email Notifications
* Dashboard Statistics
* Reports & Charts
* User Management
* Invoice Monitoring
* Overdue Invoice Alerts

---

## Future Enhancements

* React Frontend
* File Upload Support (PDF/Image)
* Email Notifications
* Audit Logs
* Dashboard Analytics
* Chart.js Reports
* Docker Deployment

---

## Author

Apurva Sinha

B.Tech CSE (Applied Mathematics)

Spring Boot | Java | PostgreSQL | React | MERN Stack
