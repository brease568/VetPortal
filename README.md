# Vet Portal

![GitHub repo size](https://img.shields.io/github/repo-size/brease568/VetPortal) ![Lines of code](https://img.shields.io/tokei/lines/github.com/brease568/VetPortal)

The Vet Portal allows veterinarian office staff to replace their current paper system with a faster, easier to use, and more flexible computerized system. The Vet Portal is a desktop application that allows staff to add and manage clients, add and manage client pets, and add and manage appointments.

## Prerequisites

- Java version 8+

## Dependencies

- SQLite JDBC (used for the back-end database)
- Apache commons codec (used for authentication)
- LGoodDatePicker (used for the calendar function)

## Usage

The application is meant to be run as a jar file from the command line:

```bash
java -jar VetPortal.jar
```

Two files, located in the jar and database folders, are required to run the Vet Portal. The first is the VetPortal.jar file, which is the application itself. The second is the VetPortal.db file, which is the application’s database. When running the application the jar file and database file must be in the same folder location.

Staff members granted access to the application will be able to access and manipulate client, pet, and appointment information. The user will enter in a username and password combination and, upon successful login, be shown three Dashboards that will be used to navigate the application: Appointments, Pets, and Clients. Each Dashboard will then have its own set of functions allowing the user to either view, search, add, edit, or delete relevant data.

Once the application is launched, the user will be presented with a login screen:

![ScreenShot](/images/login.PNG)

After a successful login the user will be presented the appointments dashboard:

![ScreenShot](/images/appointments.PNG)

The user can then navigate to the other two dashboards; clients and pets dashboards:

![ScreenShot](/images/clients.PNG)

![ScreenShot](/images/pets.PNG)

Other example actions available with the Vet Portal application (not all inclusive):

-  Add Appointments:

![ScreenShot](/images/add_appointment.PNG)

- View Client Info:

![ScreenShot](/images/view_client_info.PNG)

- Search Pets:

![ScreenShot](/images/search_pets.PNG)


Finally, the Vet Portal application will create an audit log when any significant action is performed with the application. The audit log will be created in the local directory and will be written to the file named 'auditLog.txt'. The audit log will resemble the following:

![ScreenShot](/images/audit_log.PNG)


## Contributing

The main contributors to this project are:

- Brian Rease
- Nour Debiat
- Rebekah Qu

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
