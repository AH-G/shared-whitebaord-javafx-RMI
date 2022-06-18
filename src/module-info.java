module Assignment2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.rmi;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.swing;
	requires java.desktop;
	exports server;
	exports client;
	opens client to javafx.graphics, javafx.fxml,javafx.swing;
}
