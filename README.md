Hangout Finder

The HangoutFinder is an app that lets friends pick a hangout spot in a fun and convienient way. It lets users login or signup, then lets them choose the city they are in, then it shows a list of fun activites that the user and their friends can do. You can filter the activites by number of people and price, then you can choose whether to pick an activty yourself or have the app choose it randomly for you. The app displays all the places in your city that match with your activity, it does this by connection to the Google Map API.

How it works:

Main Method:
Entry point of the program.
Establishes a connection with a server.
Provides options for user login or signup.
Retrieves user input for name, city, and filtering preferences.
Fetches a list of hangout activities based on user preferences from the Google Maps API.
Displays place names and addresses corresponding to the activities.
Sends user data to the server.
Database Method:
Handles user authentication and registration using MySQL database.
Performs login or signup based on user input.
HangoutActivitiesCategories Method:
Initializes an array of HangoutActivities objects representing various hangout activities along with their details.

Filter Methods:
noOfPeopleFilter and priceFilter methods filter activities based on the number of people and price range, respectively.
ApplyFilters method manages the application of filters based on user preferences.
Choice Methods:
chooseActivity, randomActivity, and CategoryCheck let user choose whether to pick the activity or let the app make a random choice.

Server: 
Defines Server class with main method.

Creates ServerSocket on port 1234 to listen for connections.

Prints message to console when server starts.

 Accepts client connections in an infinite loop.
 
 Creates ClientHandler for each client connection.
 
 Prints client's IP address to console.
 
Creates and starts new thread to run ClientHandler.

2. ClientHandler:
Defines ClientHandler class implementing Runnable.

Reads User object from client using ObjectInputStream.

Saves user data in users.txt using PrintWriter.

Prints confirmation message to console.

Handles exceptions and prints details.



<img width="456" alt="image" src="https://github.com/user-attachments/assets/f695de38-0649-442d-b70e-2f612790757e" />

This is the database structure. It contains three attributes: ID int(10) as a primary key, Name varchar(25), and Password varchar(10). The database will check if the entered data matches any existing data when the user logs in. Additionally, it will add new data or records if the user signs up.



