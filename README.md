# Doctor Booking Application (Spring Boot)

Welcome to the Doctor Booking Application! This Spring Boot application allows patients to book appointments with doctors. It employs token-based authentication for user access. Patients can sign up, sign in, book appointments, and cancel appointments if they have any. Admin users have the privilege to add doctors and access lists of doctors and patients.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [User Roles](#user-roles)
- [API Endpoints](#api-endpoints)
- [Security](#security)

## Introduction

This Spring Boot application serves as a backend for the Doctor Booking Application. It provides RESTful API endpoints for user registration, authentication, doctor management, and patient appointments. The application is secure, efficient, and extensible.

## Features

- **User Registration**: Patients can sign up for an account.
- **User Authentication**: Token-based authentication for secure user access.
- **User Roles**: Three user roles - Patient, Doctor, and Admin.
- **Appointment Booking**: Signed-in patients can book appointments.
- **Appointment Cancellation**: Patients can cancel their appointments.
- **Doctor Management**: Admins can add doctors and access doctor lists.
- **Patient Lists**: Admins can access patient lists.

## User Roles

- **Admin**: Can add doctors and access lists of doctors and patients.
- **Doctor**: Can access their own appointments and patient details.
- **Patient**: Can register, sign in, book appointments, and cancel appointments.

## API Endpoints

- **Patient Signup**:
  - Endpoint: `POST /patient/signUp`
  - Description: This endpoint allows a patient to sign up for an account.

- **Patient Sign In**:
  - Endpoint: `POST /patient/signIn`
  - Description: This endpoint allows a patient to sign in after signing up and receive a token to access other restricted APIs.

- **Patient Sign Out**:
  - Endpoint: `DELETE /patient/signOut`
  - Description: This endpoint allows a patient to sign out.

- **Book Appointment**:
  - Endpoint: `POST /patient/appointment/schedule`
  - Description: This endpoint is used by patients to schedule appointments. It expects a `ScheduleAppointmentDto` in the request body.

- **Cancel Appointment**:
  - Endpoint: `DELETE /patient/appointment/cancel/{appointmentId}`
  - Description: This endpoint is used to cancel an appointment by its ID. The appointment ID is part of the URL, and it expects an `AuthenticationInputDto` in the request body.

- **Get All Doctors**:
  - Endpoint: `GET /doctors`
  - Description: This endpoint allows doctors to retrieve a list of all doctors.

- **Get Doctor by ID**:
  - Endpoint: `GET /doctor/id/{id}`
  - Description: This endpoint allows doctors to retrieve their own data by their ID. The `id` is part of the URL.

- **Get Doctor Appointments**:
  - Endpoint: `GET /doctor/appointments/{doctorId}`
  - Description: This endpoint allows doctors to retrieve their scheduled appointments. The `doctorId` is part of the URL.

- **Get Patient Appointments**:
  - Endpoint: `GET /patient/appointments/{patientId}`
  - Description: This endpoint allows patients to retrieve their scheduled appointments. The `patientId` is part of the URL.

- **Update Doctor Information**:
  - Endpoint: `PUT /doctors/update/{doctorId}`
  - Description: This endpoint allows admins to update information for a specific doctor. The `doctorId` is part of the URL.

- **Delete Doctor**:
  - Endpoint: `DELETE /doctors/delete/{doctorId}`
  - Description: This endpoint allows admins to delete a specific doctor. The `doctorId` is part of the URL.

- **Update Patient Information**:
  - Endpoint: `PUT /patients/update/{patientId}`
  - Description: This endpoint allows admins to update information for a specific patient. The `patientId` is part of the URL.

- **Delete Patient**:
  - Endpoint: `DELETE /patients/delete/{patientId}`
  - Description: This endpoint allows admins to delete a specific patient. The `patientId` is part of the URL.

- **Add Doctor**:
  - Endpoint: `POST /addDoctor`
  - Description: This endpoint allows admins to add a new doctor.

- **Get All Patients**:
  - Endpoint: `GET /patients`
  - Description: This endpoint allows admins to retrieve a list of all patients.

## Security

- The application uses token-based authentication for user security.
- Ensure that environment variables, especially the JWT secret, are kept secure.





