# Order & Monitoring App - Project Report
## Overview
This project is a client-server application that manages product orders and monitors their status in real-time. The system consists of two main applications:

1 - Order Application: Allows employees to input new orders.
2 - Monitoring Application: Allows the store owner to monitor orders, view order history, and export data.

## Key Features

  - Order Creation: Employees can input product details such as name, quantity, unit price, and date. Each order is assigned a unique ID and total price is calculated 
   automatically.
  - Order Monitoring: The owner can view the current and past orders, with real-time updates provided by the server.
  - Export Functionality: Order data can be exported in CSV or PDF format, allowing the owner to generate reports.

## Design Patterns Used

1. Model-View-Controller (MVC)
The MVC design pattern separates the concerns of the application into three interconnected components:

  Model: The ```Order``` class represents the data structure for orders, containing attributes like ```Product Name```, ```Unit Price```, ```Quantity```, and methods to calculate the ```Total Price```.
View:
  - ```OrderAppGUI``` handles the user interface of the order input application, displaying text fields and labels.
  - ```MonitoringAppGUI``` provides the graphical interface for the monitoring system, using a ```JTable``` to display orders in real-time.
Controller:
  - ```OrderAppController``` processes order data and sends it to the monitoring application.
  - ```MonitoringController``` manages the real-time update of orders and provides server functionality to synchronize data between the order and monitoring applications.

2. Template Method
The ```ExportToFile``` class is an abstract class that defines the template method ```saveToFile```, which provides the steps for exporting data. The concrete subclasses ```CsvExporter``` and ```PdfExporter``` implement the specific steps for exporting to CSV and PDF, respectively.

Why Template Method?

Code Reusability: Common steps such as opening a file dialog and handling the ```DefaultTableModel``` are shared, reducing code duplication.
Extensibility: New file formats (e.g., XML, Excel) can be added by creating new subclasses of ```ExportToFile``` without modifying the existing code.


## Class Diagram (UML)

The UML diagram illustrates the structure of the project, showing relationships between key components like the Order, OrderAppController, MonitoringController, and the export functionality (CsvExporter and PdfExporter).

![Capture d'écran 2024-09-19 002204](https://github.com/user-attachments/assets/06208c0c-b1fb-4582-aa17-04bdb742d9b4)

OrderAppGUI: Represents the GUI for inputting orders.
OrderAppController: Manages order processing and communication with the monitoring system.
MonitoringAppGUI: Displays the list of orders.
MonitoringController: Manages order updates and real-time server communication.
Order: Stores details about each order.
ExportToFile: Abstract class for exporting orders, with concrete subclasses CsvExporter and PdfExporter.

## Final look
![Capture d'écran 2024-08-29 162538](https://github.com/user-attachments/assets/720a4eb7-a2df-4289-a367-893da3c0b9bd)

Conclusion
This project effectively demonstrates the use of the MVC and Template Method design patterns to build a modular, scalable, and extensible application for order management and monitoring. By separating the concerns and reusing code through abstract classes, the system is both flexible and easy to maintain.
