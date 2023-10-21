# JAVA_Utility

## Overview

**JAVA_Utility** is a Java application for managing utility bills and services. It provides features for both customers and administrators to interact with utility bill data and service information.

## Features

- **User Authentication:** Customers can log in securely using their username and password.
- **Customer Dashboard:** After logging in, customers can view their utility bills and perform various actions.
- **Add New Bill:** Customers can add new utility bills, specifying utility type, meter measurement, and date.
- **Edit Bill:** Edit existing utility bills by providing the new meter measurement.
- **Delete Bill:** Remove a utility bill from the customer's account.
- **Customer Registration:** New users can register for an account by providing a username, email, and password.
- **Admin Features:** Administrators can log in to access additional functionalities.
- **Edit Services:** Administrators can edit service information, including service charges and unit charges.

## Usage

1. **Running the Application:**
   - Compile the project.
   - Execute the `Main` class to start the application.

2. **User Selection:**
   - On startup, users can choose to log in as a customer or an administrator.

3. **Customer Actions:**
   - Customers can log in, view their bills, add new bills, edit bills, and delete bills.
   - Registration is available for new customers.

4. **Administrator Actions:**
   - Administrators can log in, access service-related features, and edit service charges and unit charges.

## Project Structure

The project is organized into the following packages:

- `Controller`: Contains the application's controllers.
- `Model`: Includes model classes for customers, services, and utility bills.
- `View`: Contains the graphical user interface components.

## Documentation

To generate JavaDocs for the project, you can use the following command:

```bash
javadoc -d <output_directory> -sourcepath <source_directory> Controller Model View
```
or 
```bash
javadoc -d html -sourcepath src -subpackages Controller Model View
```

To view the documentation, please visit: https://java-utility-docs.vercel.app/index.html

## Authors

- [Muhammad Ashhub Ali](https://github.com/NightWalker7558)
- [Adeel Ahmed](https://github.com/itsAdee)
- [Ahmad Zafar](https://github.com/Arch-Frost)
