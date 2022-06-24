# Realtime Shared MultiThreaded Whiteboard Application using JavaFX and RMI with management capabilities and chat features
## Context
The motivation behind this project is to develop a Distributed shared whiteboard Java based desktop application. The Application, being shared, enables multiple users to collaborate on a single drawing project. The application is focused on real time sharing of canvas so any action performed by a collaborator on the whiteboard is replicated to the screens of every other person in that project. This whiteboard also features different shapes to choose from, mainly triangle, circle, rectangle and line. Apart from that users can also scribble and write some texts on the canvas. This application also has a chat based system where different users, working on the same project can send texts to the other collaborators. A user management system is also designed which enables the application to track multiple projects at the same time with each project containing multiple collaborators. The multithreading and communication between the collaborators is done through Remote Method Invocation (RMI) which is a thread-safe implementation. Because of RMI, it can support multiple concurrent users and the server is able to handle multiple requests at the same time.

## Classes
My project has multiple components
1. Whiteboard Server Interface, an RMI based implementation to handle multithreading and
communication between the users. It is basically a message router.
2. Whiteboard Server, which focuses on different whiteboard projects and user management.
3. Whiteboard Client Interface, a RMI based callback interface which enables the server to invoke
the methods on client side to perform replication actions.
4. Whiteboard Client, a JavaFX based client application. Spanning over multiple files and multiple
screens, this application uses FXML documents and controllers to create beautiful GUI which
displays the paint canvas along with the chat window.
5. Shapes Library, which contains the implementation of different shapes required for this
assignment.
