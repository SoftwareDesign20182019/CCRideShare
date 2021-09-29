# CCRideShare
A service for connecting students in need of drives with those able to provide them.

### Running the Application
This application was developed in Eclipse, and running it in Eclipse is recommended. To do so, having downloaded Eclipse, follow these steps: 
1. Download the code, then import it as an existing project into your workspace (File > Import... > General). 
2. Make sure `application` and `ui` are packages within the `src` folder.
3. [Download the MySQL driver](https://dev.mysql.com/downloads/connector/j/) (choose Platform Independent, then download the .zip file). Extract the contents of the .zip file.
4. Right click on the project in the File Explorer and click on Properties. Under Java Build Path, go to the Libraries tab. Click on Classpath, then use the "Add External JARs..." button to add the .jar file located inside the folder extracted from the .zip file in the previous step.
5. Add JavaFX to the project, following the steps for non-modular IDE projects [in this tutorial](https://openjfx.io/openjfx-docs#IDE-Eclipse).
6. [Install MAMP](https://www.mamp.info/en/downloads/). Once it's installed, open it and start the servers if they don't start automatically. Then go to MAMP > Preferences > Ports and note the MySQL port. 
7. In the application source code, in `src/application/DatabaseHandler.java`, change the `PORT_NUMBER` constant to be the MySQL port from the previous step.
8. Run `src/application/LogInApplication.java`.
