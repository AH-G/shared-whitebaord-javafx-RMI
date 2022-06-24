# Realtime Shared MultiThreaded Whiteboard Application using JavaFX and RMI with management capabilities and chat features
## Context
The motivation behind this project is to develop a Distributed shared whiteboard Java based desktop application. The Application, being shared, enables multiple users to collaborate on a single drawing project. The application is focused on real time sharing of canvas so any action performed by a collaborator on the whiteboard is replicated to the screens of every other person in that project. This whiteboard also features different shapes to choose from, mainly triangle, circle, rectangle and line. Apart from that users can also scribble and write some texts on the canvas. This application also has a chat based system where different users, working on the same project can send texts to the other collaborators. A user management system is also designed which enables the application to track multiple projects at the same time with each project containing multiple collaborators. The multithreading and communication between the collaborators is done through Remote Method Invocation (RMI) which is a thread-safe implementation. Because of RMI, it can support multiple concurrent users and the server is able to handle multiple requests at the same time.

## Features
These are salient features of the whiteboard application. 
1. Whiteboard canvas application. 
2. shapes library. 
3. Chat features
4. User/Collboators management capabilities
5. Saving the project, opening the project. 
6. Export the canvas as images 

## Classes
My project is spread over 5 classes
1. Whiteboard Server Interface, an RMI based implementation to handle multithreading and communication between the users. It is basically a message router.
2. Whiteboard Server, which focuses on different whiteboard projects and user management.
3. Whiteboard Client Interface, a RMI based callback interface which enables the server to invoke the methods on client side to perform replication actions.
4. Whiteboard Client, a JavaFX based client application. Spanning over multiple files and multiple screens, this application uses FXML documents and controllers to create beautiful GUI which displays the paint canvas along with the chat window.
5. Shapes Library, which contains the implementation of different shapes required for this assignment.

## Application Walk-through
First, Whiteboard server application is run. That binds the interface to the RMI Registry. The default IP for the RMI Registry is localhost. After that the whiteboard client main application is run. That will first does the lookup for the RMI based whiteboard server. After finding that server, it starts a login page. This login page asks for different information to connect to a particular whiteboard instance. At first it will ask for your username with which the user wants to register. Once the user put in the username and click register, the client will invoke the server’s register function to register the user to the server. In
return, server will send client ID to the user

Along with clientID, a list of the whiteboard instances currently running is also returned to the user. This list is in the form of the whiteboard IDs. User now has the option to either choose from the running instances or create a whiteboard project instance himself. In order to create the new whiteboard project instance, the user will have to click the create button. Once that button is clicked, the server’s createwhiteboard function is invoked where the server creates the whiteboard instance object and set the user its manager, After the creation of the whiteboard instance project, the server will return the ID of the project and update the list. Now the user can either choose the project instance that he has created or connect with any other whiteboard instance that is already available there. In order to connect with the whiteboard project instance, the user will have to click one from the list view and then click connect. This will take the user to another window which is the window of whiteboard application. The user are given multiple operations to choose from which are mentioned below.
1. Choose the shape that he wants to draw. These shapes are
  - Rectangle
  - Circle
  - Triangle
  - Line
2. Choose to scribble on the canvas.
3. Choose to input a text on the canvas. This will open another window where user is asked to input a text. Once user has written the text on the window, he will have click on canvas to paste the text.
4. Unlimited color options to select any color the user wants for either shapes, scribbling and texts.
5. A chat area, to text the other users within the project.
6. Useronline area, where user can see username of different users along with their ID.
7. Information about the username, the whiteboard instance, and the role of the user within the whiteboard project is also displayed.

There are two roles that the user can have,
1. Manager
2. Collaborator

If the user creates the whiteboard project instance, then he becomes the manager of the project, all the other users who join afterwards are given the role of collaborator. A manager is given some privileges over the collaborators. These privileges are
1. Ability to clear the dashboard by clicking the option File -> New.
2. Ability to open an existing dashboard.
3. Ability to save the state of dashboard.
4. Ability to kick the users/peer out.

When the File-> save option is selected, the user is given an option choose the directory where he can save the files. The dashboard is saved in a PNG format. Similarly, once the File-> open option is selected, the user is given an option to choose the directory and the file which he wants to open. All the multithreading and concurrency is dealt with RMI because of which the server becomes the message passing router apart from user management and maintaining the state of multiple dashboards.
