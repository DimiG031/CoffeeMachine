package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class JavaFXApp extends Application {

    public static void startJavaFXApp() {
        launch(); // This launches the JavaFX application
    }
    private ComboBox<String> drinkComboBox;
    private ComboBox<Integer> sugarComboBox;
    private ProgressBar progressBar;
    private Label statusLabel;
    private Label descriptionLabel;  // Label to display the drink description

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drink Chooser");

        // Drink ComboBox
        Label drinkLabel = new Label("Choose your drink:");
        drinkComboBox = new ComboBox<>();
        drinkComboBox.getItems().addAll(
                "Espresso",
                "Latte",
                "Cappuccino",
                "Mocha",
                "Americano",
                "Macchiato"
        );
        drinkComboBox.setPromptText("Select a drink");

        // Description Label
        descriptionLabel = new Label("Drink description will appear here.");
        descriptionLabel.setWrapText(true);
        descriptionLabel.setPrefWidth(200); // Set preferred width for description

        // Add a listener to update the description when a drink is selected
        drinkComboBox.setOnAction(e -> updateDrinkDescription(drinkComboBox.getValue()));

        // Sugar ComboBox
        Label sugarLabel = new Label("How much sugar? (0-6 teaspoons):");
        sugarComboBox = new ComboBox<>();
        for (int i = 0; i <= 6; i++) {
            sugarComboBox.getItems().add(i);
        }
        sugarComboBox.setPromptText("Select sugar amount");

        // Prepare button
        Button prepareButton = new Button("Prepare Drink");
        prepareButton.setOnAction(e -> prepareDrink());

        // Progress Bar
        progressBar = new ProgressBar(0);

        // Status Label
        statusLabel = new Label();

        // Layout
        VBox leftLayout = new VBox(10);
        leftLayout.setPadding(new Insets(20));
        leftLayout.getChildren().addAll(drinkLabel, drinkComboBox, sugarLabel, sugarComboBox, prepareButton, progressBar, statusLabel);

        HBox mainLayout = new HBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getChildren().addAll(leftLayout, descriptionLabel);
        mainLayout.setAlignment(Pos.CENTER_LEFT);

        Scene scene = new Scene(mainLayout, 800, 400); // Adjusted width for description
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to update the drink description based on the selection
    private void updateDrinkDescription(String drink) {
        switch (drink) {
            case "Espresso" -> descriptionLabel.setText("Espresso: A strong, concentrated coffee drink prepared under high pressure.");
            case "Latte" -> descriptionLabel.setText("Latte: A coffee drink with a large amount of milk, known for its smooth texture.");
            case "Cappuccino" -> descriptionLabel.setText("Cappuccino: Espresso with equal parts milk and milk foam.");
            case "Mocha" -> descriptionLabel.setText("Mocha: A chocolate latte, a combination of espresso, milk, and cocoa.");
            case "Americano" -> descriptionLabel.setText("Americano: Espresso diluted with water, providing a lighter taste.");
            case "Macchiato" -> descriptionLabel.setText("Macchiato: Espresso with a small amount of milk foam.");
            default -> descriptionLabel.setText("Drink description will appear here.");
        }
    }

    private void prepareDrink() {
        String chosenDrink = drinkComboBox.getValue();
        Integer sugar = sugarComboBox.getValue();

        if (chosenDrink == null || sugar == null) {
            statusLabel.setText("Please select both a drink and a sugar amount!");
            return;
        }

        statusLabel.setText("Preparing " + chosenDrink + " with " + sugar + " teaspoons of sugar...");

        // Simulate preparation with a background thread
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                final int progress = i;
                try {
                    Thread.sleep(50); // Simulate the preparation time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Update the progress bar in the JavaFX Application Thread
                javafx.application.Platform.runLater(() -> {
                    progressBar.setProgress(progress / 100.0);
                    if (progress == 100) {
                        statusLabel.setText(chosenDrink + " with " + sugar + " teaspoons of sugar is ready! Enjoy your drink!");
                    }
                });
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


