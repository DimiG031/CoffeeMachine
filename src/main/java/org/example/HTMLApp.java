package org.example;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLApp {

    public static void startHTMLApp() {
        // Path to the desktop
        String userHome = System.getProperty("user.home");
        File desktop = new File(userHome, "Desktop");
        File htmlFile = new File(desktop, "index.html");

        // HTML content
        String htmlContent = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Choose Drink</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    padding: 20px;
                    max-width: 600px;
                    margin: auto;
                }
                .hidden {
                    display: none;
                }
                .answer-button {
                    margin: 5px 0;
                    padding: 10px;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    background-color: #f0f0f0;
                    cursor: pointer;
                }
                .answer-button:hover {
                    background-color: #ddd;
                }
                #sugar-prompt, #preparation {
                    margin-top: 20px;
                }
            </style>
        </head>
        <body>
            <h1>Welcome User</h1>
            <p>Choose your drink:</p>
            <div id="answers">
                <div class="answer-button" onclick="chooseDrink('Espresso')">Espresso</div>
                <div class="answer-button" onclick="chooseDrink('Latte')">Latte</div>
                <div class="answer-button" onclick="chooseDrink('Cappuccino')">Cappuccino</div>
                <div class="answer-button" onclick="chooseDrink('Mocha')">Mocha</div>
                <div class="answer-button" onclick="chooseDrink('Americano')">Americano</div>
                <div class="answer-button" onclick="chooseDrink('Macchiato')">Macchiato</div>
            </div>
            <div id="sugar-prompt" class="hidden">
                <h2>You chose: <span id="chosen-drink"></span></h2>
                <p id="drink-description"></p>
                <p>How much sugar would you like? (0-6 teaspoons)</p>
                <input type="number" id="sugar-amount" min="0" max="6" value="0">
                <button onclick="confirmSugar()">Confirm</button>
            </div>
            <div id="preparation" class="hidden">
                <h2>Preparing your <span id="final-drink"></span> with <span id="final-sugar"></span> teaspoons of sugar...</h2>
                <progress id="preparation-bar" value="0" max="100"></progress>
                <p id="preparation-status">0%</p>
            </div>
        
            <script>
                let chosenDrink = "";

                function chooseDrink(drink) {
                    chosenDrink = drink;
                    let description = "";
                    
                    switch(drink) {
                        case 'Espresso':
                            description = 'Espresso is a strong, concentrated coffee drink prepared under high pressure.';
                            break;
                        case 'Latte':
                            description = 'Latte is a coffee drink with a large amount of milk, known for its smooth texture.';
                            break;
                        case 'Cappuccino':
                            description = 'Cappuccino is espresso with equal parts milk and milk foam.';
                            break;
                        case 'Mocha':
                            description = 'A mocha is a chocolate latte, a combination of espresso, milk, and cocoa.';
                            break;
                        case 'Americano':
                            description = 'Americano is espresso diluted with water, providing a lighter taste.';
                            break;
                        case 'Macchiato':
                            description = 'A macchiato is espresso with a small amount of milk foam.';
                            break;
                    }

                    document.getElementById('chosen-drink').textContent = chosenDrink;
                    document.getElementById('drink-description').textContent = description;
                    document.getElementById('sugar-prompt').classList.remove('hidden');
                    document.getElementById('answers').classList.add('hidden');
                }

                function confirmSugar() {
                    const sugarAmount = document.getElementById('sugar-amount').value;
                    document.getElementById('final-drink').textContent = chosenDrink;
                    document.getElementById('final-sugar').textContent = sugarAmount;
                    document.getElementById('sugar-prompt').classList.add('hidden');
                    document.getElementById('preparation').classList.remove('hidden');
                    simulatePreparation();
                }

                function simulatePreparation() {
                    const progressBar = document.getElementById('preparation-bar');
                    const statusText = document.getElementById('preparation-status');
                    let progress = 0;

                    const interval = setInterval(() => {
                        if (progress < 100) {
                            progress += 1;
                            progressBar.value = progress;
                            statusText.textContent = progress + "%";
                        } else {
                            clearInterval(interval);
                            statusText.textContent = "Your drink is ready! Enjoy!";
                        }
                    }, 50);
                }
            </script>
        </body>
        </html>
        """;

        // Writing content to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile))) {
            writer.write(htmlContent);
            System.out.println("The HTML file has been successfully created on the desktop.");

            // Open the HTML file in the default web browser
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } else {
                System.out.println("Open web browser is not supported.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startHTMLApp();
    }
}
