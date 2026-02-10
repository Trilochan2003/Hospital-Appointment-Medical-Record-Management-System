# Hospital-Appointment-Medical-Record-Management-System'
🏥 Hospital Appointment & Medical Record Management System

A Spring Boot REST API for managing hospital operations such as Departments, Doctors, Patients, Appointments, Medical Records, and Prescriptions.
The system enforces real-world constraints like appointment validation, record creation only after completion, and controlled updates.

📌 Features

Department management

Doctor management with availability and specialization

Patient registration and lookup

Appointment booking with business rules

Medical records creation after appointment completion

Prescription generation linked to medical records

Centralized exception handling

Clean layered architecture (Controller → Service → DAO → Repository)

🏗️ Project Architecture
controller
   ↓
service
   ↓
dao
   ↓
repository
   ↓
database

Layer Responsibilities
Layer	Responsibility
Controller	Handles HTTP requests & responses
Service	Builds response structure & status
DAO	Business logic & validations
Repository	Database operations (JPA)
Entity	Database mapping
Exception	Global & custom error handling
🛠️ Technologies Used

Java 17+

Spring Boot

Spring Data JPA

Hibernate

MySQL

Maven

Postman (API testing)

📂 Modules Overview
1️⃣ Department

Create, update, delete departments

Fetch by ID or name

2️⃣ Doctor

Add doctors with department mapping

Fetch by specialization, department, patient, appointment, availability days

Restricts invalid updates

3️⃣ Patient

Register patients

Fetch by phone, age, appointment, medical record

Safe update and delete

4️⃣ Appointment

Book appointment with uniqueness validation
(patient cannot book multiple appointments on the same day)

Cancel appointment with rules

Status controlled using Enum

Prevents doctor/patient change during update

5️⃣ Medical Record

Created only after appointment is COMPLETED

Automatically mapped to doctor & patient

Fetch by patient, doctor, visit date, appointment

6️⃣ Prescription

Created only if medical record exists

Fetch by patient or medical record

One-to-one relationship with medical record

🔐 Business Rules Implemented

❌ Appointment cannot be booked twice on the same day for a patient

❌ Completed appointments cannot be cancelled

❌ Medical record cannot be created unless appointment is COMPLETED

❌ Doctor & patient cannot be changed during appointment update

❌ Prescription cannot exist without medical record

✅ Enum validation for appointment status

🚨 Exception Handling

Custom exceptions used:

ResourceNotFoundException

IdNotFoundException

InvalidOperationException

DuplicateResourceException

Handled globally using @ControllerAdvice.

🌐 API Endpoints Summary
Department
POST   /hospital/dept
POST   /hospital/dept/all
GET    /hospital/dept/all
GET    /hospital/dept/id/{id}
GET    /hospital/dept/name/{name}
PUT    /hospital/dept/update
DELETE /hospital/dept/delete/{id}

Doctor
POST   /hospital/doctor/add
GET    /hospital/doctor/all
GET    /hospital/doctor/id/{id}
GET    /hospital/doctor/specialization/{specialization}
GET    /hospital/doctor/department/{name}
GET    /hospital/doctor/patient/{patientId}
GET    /hospital/doctor/appointment/{appointmentId}
GET    /hospital/doctor/day/{day}
PUT    /hospital/doctor/update
DELETE /hospital/doctor/delete/{id}

Patient
POST   /hospital/patient/register
GET    /hospital/patient/all
GET    /hospital/patient/id/{id}
GET    /hospital/patient/phone/{phone}
GET    /hospital/patient/age/{age}
GET    /hospital/patient/appointment/{appointmentId}
GET    /hospital/patient/medical-record/{recordId}
PUT    /hospital/patient/update
DELETE /hospital/patient/delete/{id}

Appointment
POST   /hospital/appointment/book
GET    /hospital/appointment/all
GET    /hospital/appointment/id/{id}
GET    /hospital/appointment/date/{date}
GET    /hospital/appointment/doctor/{doctorId}
GET    /hospital/appointment/patient/{patientId}
GET    /hospital/appointment/status/{status}
PUT    /hospital/appointment/cancel/{id}
PUT    /hospital/appointment/update

Medical Record
POST   /hospital/report/create/appointment/{appointmentId}/medical-record
GET    /hospital/report/all
GET    /hospital/report/id/{id}
GET    /hospital/report/patient/{patientId}
GET    /hospital/report/doctor/{doctorId}
GET    /hospital/report/date/{date}
GET    /hospital/report/appointment/id/{appointmentId}

Prescription
POST   /hospital/prescription/generate/id/{medicalRecordId}
GET    /hospital/prescription/all
GET    /hospital/prescription/id/{id}
GET    /hospital/prescription/medical-record/{medicalRecordId}
GET    /hospital/prescription/patient/{patientId}

🧪 Testing

APIs tested using Postman

JSON validation for Enum values

Edge cases handled via custom exceptions

🎯 Learning Outcomes

Real-world REST API design

Proper entity relationships

Enum handling & validation

Layered architecture best practices

Exception handling strategy

Business-rule-driven coding

🚀 Future Enhancements

Authentication & authorization (Spring Security)

Pagination & sorting

Swagger/OpenAPI documentation

Role-based access

Reporting & analytics

👨‍💻 Author

Trilochan Sai
Java | Spring Boot | REST APIs
