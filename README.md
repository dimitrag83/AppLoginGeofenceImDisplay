# AppLoginGeofenceImDisplay
Android application that implements the following

1 - Splashscreen and Welcome Screen.
As soon as the application launches, the welcome message "Android Application Development" will be displayed. 
The message will disappear (with a delay of 2 seconds) and the application will continue on the second page where you will see the phrase 
"Welcome to my application" and below a large button labeled "Enter ". 
By clicking on it the user will be guided to the ones described in 2.

2 - Wireless Connection and Sign-In Form with Animation
The phrase "Welcome to my application," and tton labeled "Enter" will disappear. This change is done with animation.
Next, the app checks for an active network connection on the user's device. The following should be done:
• If there is no such connection, a message will appear on the screen asking the user to connect before proceeding.
• If there is any such connection, a form will appear on the screen where the user will enter the username and password he already has on Google.

3 - Authentication
The authentication of the user takes place.
• If authentication fails, a corresponding message should appear (eg, "Failed, try again") and the authentication form.
• If Authentication succeeds, then the form will disappear and the message "Authenticate Succeed" and a button labeled "Next" will appear in its place.

4 - Display Website Image without Browser Usage
By pressing the "Next" button from the previous query, this button will disappear, as well as the message "Authenticate Success". 
Instead, the content of the webpage will be displayed with the address "https://unsplash.it/200/300/?random". 
Below two buttons will appear:
• The first button will be labeled "Nextr Image" and if the user clicks it will appear the same (but now with a different image).
• The second button will be labeled "Next" and if it is pressed, the user will be directed to Question 5

5 - Use Positioning and Geofencing
The user arrives here by pressing the button "Next". In this case, the form and buttons from 4 disappear. 
The application will receive the current location (coordinates) from Google. 
It will then display a Google map along with Geofencing with a four (4) hour duration. 
Geofencing should be the center of the entrance to New Metro Station of the metro in Athens and 200 meters radius. 
If the current location is within the map area covered by the Geofencing, then Geofencing should disappear from the screen. 
