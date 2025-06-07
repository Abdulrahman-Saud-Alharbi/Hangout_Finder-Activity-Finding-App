package the_hangout_society;

import java.util.Scanner;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.net.http.*;
import java.sql.*;
import org.json.*;
import sun.net.www.http.HttpClient;

public class The_Hangout_Society {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 1234); ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());) {
            Scanner input = new Scanner(System.in);
            boolean log_in = true;
            do {
                System.out.print("1-Log-in , 2-Sign-up :");
                int coohse = input.nextInt();
                input.nextLine();
                String name = null;
                String ID = null;
                String password = null;
                if (coohse == 1) {
                    System.out.println("Enter the ID : ");
                    ID = input.nextLine();
                    System.out.println("Enter the password : ");
                    password = input.nextLine();
                    log_in = Database(ID, password, "", "Log_In");
                } else {
                    System.out.println("Enter your Name : ");
                    name = input.nextLine();
                    System.out.println("Enter the ID : ");
                    ID = input.nextLine();
                    System.out.println("Enter the password : ");
                    password = input.nextLine();
                    Database(ID, password, name, "Sign_Up");
                }
            } while (true != log_in);

            String Username = "";

            String apiKey = "AIzaSyC1SAu3Lkd2zTgogSe_ZD9ZdzVMK51OYB0";
            HangoutActivities[] activityArray = HangoutActvitiesCategories();
            int code = 0;
            int userFlag = 0;
            String category = "";
           
            // Ask the user to input the city
            System.out.println("Enter a city:");
            String cityName = input.nextLine();
            //Ask if user wants filters
            System.out.println("Do you want to filter the number of people you want the activity to encompass (0 for yes, 1 for no)");
            int filter1Choice = input.nextInt();
            System.out.println("Do you want to filter the price of the activities (0 for yes, 1 for no)");
            int filter2Choice = input.nextInt();
            ApplyFilters(filter2Choice, filter1Choice, activityArray);

            //Choose an activity or get a random activity
            System.out.println("");
            System.out.println("Do you want to choose an activity or have a random selection? 0 for choose and 1 for random");
            int choice = input.nextInt();

            String category1 = categoryCheck(choice, code, activityArray, category);

            // Encode the category for URL
            String encodedCategory = URLEncoder.encode(category1, StandardCharsets.UTF_8);

            // Create the API request
            String apiUrl = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + encodedCategory + "+in+" + cityName + "&key=" + apiKey;
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(apiUrl))
                    .build();

            // Send the request and get the response
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if response status is OK
            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());

                // Check if the JSON response contains the expected structure
                if (jsonResponse.has("results")) {
                    JSONArray results = jsonResponse.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject result = results.getJSONObject(i);
                        String name = result.getString("name");
                        String address = result.getString("formatted_address");

                        // Print the place name and address
                        System.out.println("Place Name: " + name);
                        System.out.println("Address: " + address);
                       if (userFlag == 0) {
                            User user = new User(Username, cityName, filter1Choice, filter2Choice, choice, name + " " + address);
                            objectOutputStream.writeObject(user);
                            userFlag++;
                        }
                        System.out.println();
                        System.out.println();
                    }
                } else {
                    System.out.println("Unexpected JSON format: 'results' not found.");
                }
            } else {
                System.out.println("Failed to retrieve data. Status code: " + response.statusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean Database(String ID, String password, String name, String Opreation) {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";

        // Database credentials
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(url, "root", "");

            // Create a Statement object for SQL queries
            Statement stmt = conn.createStatement();

            // Execute a sample query
            if (Opreation.equals("Log_In")) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM user_information");
                boolean found = false;
                while (rs.next()) {
                    
                    if (rs.getString(1).equals(ID) && rs.getString(3).equals(password)) {
                        System.out.println("Hi " + rs.getString(2));
                        found = true;
                        break;
                    } 
             
                    

                }
                   if (!found){
                    System.out.println("The ID or password is not correct!");
                    return false;
                }
                rs.close();
                stmt.close();
                conn.close();
            } else if (Opreation.equals("Sign_Up")) {
                stmt.executeUpdate("INSERT INTO user_information (ID, Name, password) VALUES ('" + ID + "', '" + name + "', '" + password + "')");

            }

            // Close resources
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
    public static HangoutActivities[] HangoutActvitiesCategories() {

        HangoutActivities[] activitiesArray = new HangoutActivities[18];

        //Hangout Activities Categories
        activitiesArray[0] = new HangoutActivities(0, "Bowling", 30, 45, 2, 8);
        activitiesArray[1] = new HangoutActivities(1, "Escape Room", 150, 200, 2, 6);
        activitiesArray[2] = new HangoutActivities(2, "Padel", 160, 200, 4, 10);
        activitiesArray[3] = new HangoutActivities(3, "Football", 150, 200, 8, 30);
        activitiesArray[4] = new HangoutActivities(4, "Board Games Cafe", 30, 40, 2, 6);
        activitiesArray[5] = new HangoutActivities(5, "Billiardo", 30, 45, 2, 4);
        activitiesArray[6] = new HangoutActivities(6, "Table Tennis", 30, 45, 2, 4);
        activitiesArray[7] = new HangoutActivities(7, "Carting", 80, 150, 1, 8);
        activitiesArray[8] = new HangoutActivities(8, "Cinema", 45, 85, 1, 12);
        activitiesArray[9] = new HangoutActivities(9, "Lounge", 20, 100, 1, 10);
        activitiesArray[10] = new HangoutActivities(10, "Cafe", 20, 50, 1, 10);
        activitiesArray[11] = new HangoutActivities(11, "Arcade", 30, 200, 1, 15);
        activitiesArray[12] = new HangoutActivities(12, "Amusement Park", 100, 200, 1, 20);
        activitiesArray[13] = new HangoutActivities(13, "Bicycle Rent", 50, 75, 1, 20);
        activitiesArray[14] = new HangoutActivities(14, "Beach", 0, 300, 1, 30);
        activitiesArray[15] = new HangoutActivities(15, "Jetski Rent", 150, 300, 1, 10);
        activitiesArray[16] = new HangoutActivities(16, "Internet Cafe", 50, 75, 1, 8);
        activitiesArray[17] = new HangoutActivities(17, "Boat Rent", 250, 350, 5, 10);

        return activitiesArray;

    }

    public static int chooseActivity() {
        Scanner input = new Scanner(System.in);
        int code = 0;

        System.out.println("Choose an Activity: Pick the Activity Code");
        code = input.nextInt();

        return code;
    }

    public static int randomActivity() {
        int code = (int) (Math.random() * 18);

        return code;
    }

    public static HangoutActivities[] noOfPeopleFilter() {
        Scanner input = new Scanner(System.in);
        HangoutActivities[] peopleFilter = new HangoutActivities[18];
        HangoutActivities[] allActvities = HangoutActvitiesCategories();

        System.out.println("Enter Minimum Number of People");
        int choice1 = input.nextInt();
        System.out.println("Enter Maximum Number of People");
        int choice2 = input.nextInt();

        for (int i = 0; i < allActvities.length; i++) {

            if (allActvities[i].getNumberOfPeoplelow() >= choice1) {

                if (allActvities[i].getNumberOfPeoplehigh() <= choice2) {

                    peopleFilter[i] = allActvities[i];

                }
            }
        }

        for (int i = 0; i < peopleFilter.length; i++) {

            if (peopleFilter[i] == null) {

                continue;
            }
            System.out.println(peopleFilter[i]);
        }
        return peopleFilter;
    }

    public static HangoutActivities[] priceFilter(HangoutActivities[] allActvities) {

        Scanner input = new Scanner(System.in);
        HangoutActivities[] priceFilter = new HangoutActivities[18];

        System.out.println("Enter Minimum Price");
        int choice1 = input.nextInt();
        System.out.println("Enter Maximum Price");
        int choice2 = input.nextInt();

        for (int i = 0; i < allActvities.length; i++) {

            if (allActvities[i] == null) {

                continue;
            }
            if (allActvities[i].getAveragePricelow() >= choice1) {

                if (allActvities[i].getAveragePricehigh() <= choice2) {

                    priceFilter[i] = allActvities[i];

                }
            }
        }

        for (int i = 0; i < priceFilter.length; i++) {

            if (priceFilter[i] == null) {

                continue;
            }
            System.out.println(priceFilter[i]);
        }
        return priceFilter;

    }

    public static void ApplyFilters(int filter2Choice, int filter1Choice, HangoutActivities[] activityArray) {

        if (filter1Choice == 0) {

            HangoutActivities[] pFilter = noOfPeopleFilter();

            if (filter2Choice == 0) {

                priceFilter(pFilter);

            } else if (filter2Choice == 1) {

                //Show all activities
                for (int i = 0; i < pFilter.length; i++) {
                    if (pFilter[i] == null) {

                        continue;
                    }
                    System.out.println(pFilter[i]);
                }
            }

        } else if (filter1Choice == 1) {

            if (filter2Choice == 0) {

                priceFilter(activityArray);

            } else if (filter2Choice == 1) {

                //Show all activities
                for (int i = 0; i < activityArray.length; i++) {
                    System.out.println(activityArray[i]);
                }
            }
        }

    }

    public static String categoryCheck(int choice, int code, HangoutActivities[] activityArray, String category) {
        Scanner input = new Scanner(System.in);
        if (choice == 0) {

            code = chooseActivity();
            category = activityArray[code].getName();
            System.out.println(category);

            return category;
        } else if (choice == 1) {

            code = randomActivity();
            category = activityArray[code].getName();
            System.out.println(category);
            return category;

        } else {
            System.err.println("Error! Enter Again \nChoose between 0 and 1");
        }
        choice = input.nextInt();

        category = activityArray[code].getName();
        categoryCheck(choice, code, activityArray, category);

        return category;

    }

}
