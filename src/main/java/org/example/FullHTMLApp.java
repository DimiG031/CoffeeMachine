package org.example;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FullHTMLApp {

    public static void startFullHTMLApp() {
        // path to desktop
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
            <title>Choose coffee</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                    padding: 20px;
                    background-color: #f5f5f5;
                }
                .container {
                    max-width: 800px;
                    text-align: center;
                }
                h1 {
                    margin-bottom: 20px;
                }
                .image-menu {
                    display: flex;
                    justify-content: space-around;
                    flex-wrap: wrap;
                }
                .menu-item {
                    cursor: pointer;
                    margin: 10px;
                    border-radius: 10px;
                    overflow: hidden;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                }
                .menu-item img {
                    width: 150px;
                    height: 150px;
                    object-fit: cover;
                }
                .menu-item p {
                    margin: 0;
                    padding: 10px;
                }
                .description, .sugar-selection, .progress-container, .final-message {
                    margin-top: 20px;
                    padding: 20px;
                    border-radius: 10px;
                    background-color: #fff;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    display: none;
                }
                .show {
                    display: block;
                }
                .progress-container {
                    width: 100%;
                    background-color: #f5f5f5;
                    border-radius: 5px;
                    overflow: hidden;
                    margin-top: 20px;
                }
                .progress-bar {
                    width: 0%;
                    height: 20px;
                    background-color: #4caf50;
                    text-align: center;
                    color: white;
                    line-height: 20px;
                }
                .final-message img {
                    width: 150px;
                    height: 150px;
                    object-fit: cover;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <h1>Choose your coffee</h1>
                <div class="image-menu">
                    <div class="menu-item" onclick="showDescription('espresso')">
                        <img src="https://imgur.com/lwIFo1v.jpg" alt="Espresso">
                        <p>Espresso</p>
                    </div>
                    <div class="menu-item" onclick="showDescription('latte')">
                        <img src="https://imgur.com/m2bopcn.jpg" alt="Latte">
                        <p>Latte</p>
                    </div>
                    <div class="menu-item" onclick="showDescription('cappuccino')">
                        <img src="https://imgur.com/2Oro4MW.jpg" alt="Cappuccino">
                        <p>Cappuccino</p>
                    </div>
                    <div class="menu-item" onclick="showDescription('mocha')">
                        <img src="https://imgur.com/ZoUtAqX.jpg" alt="Mocha">
                        <p>Mocha</p>
                    </div>
                    <div class="menu-item" onclick="showDescription('americano')">
                        <img src="https://imgur.com/TUOCuZs.jpg" alt="Americano">
                        <p>Americano</p>
                    </div>
                    <div class="menu-item" onclick="showDescription('macchiato')">
                        <img src="https://imgur.com/1BGVEzR.jpg" alt="Macchiato">
                        <p>Macchiato</p>
                    </div>
                </div>
                <div id="description" class="description">
                    <h2 id="description-title">Content coffee</h2>
                    <p id="description-text">Choose coffee to see content.</p>
                    <button onclick="showSugarSelection()">Next</button>
                </div>
                <div id="sugar-selection" class="sugar-selection">
                    <h2>Select sugar amount</h2>
                    <p>How many teaspoons of sugar would you like? (0-6)</p>
                    <input type="number" id="sugar-amount" min="0" max="6" value="0">
                    <button onclick="startPreparation()">Confirm</button>
                </div>
                <div id="progress-container" class="progress-container">
                    <div id="progress-bar" class="progress-bar">0%</div>
                </div>
                <div id="final-message" class="final-message">
                    <img id="final-image" src="" alt="Final Coffee Image">
                    <p id="final-text">Your drink is ready, enjoy!</p>
                </div>
            </div>
            <script>
                let selectedCoffee = '';
                function showDescription(coffeeType) {
                    selectedCoffee = coffeeType;
                    const descriptions = {
                        espresso: 'Espresso is a strong, concentrated coffee drink prepared under high pressure.',
                        latte: 'Latte is a coffee drink with a large amount of milk, known for its smooth texture.',
                        cappuccino: 'Cappuccino is espresso with equal parts milk and milk foam.',
                        mocha: 'A mocha is a chocolate latte, a combination of espresso, milk, and cocoa.',
                        americano: 'Americano is espresso diluted with water, providing a lighter taste.',
                        macchiato: 'A macchiato is espresso with a small amount of milk foam.'
                    };
                    document.getElementById('description-title').textContent = coffeeType.charAt(0).toUpperCase() + coffeeType.slice(1);
                    document.getElementById('description-text').textContent = descriptions[coffeeType] || 'Unknown coffee.';
                    document.getElementById('description').classList.add('show');
                }

                function showSugarSelection() {
                    document.getElementById('description').classList.remove('show');
                    document.getElementById('sugar-selection').classList.add('show');
                }

                function startPreparation() {
                    document.getElementById('sugar-selection').classList.remove('show');
                    document.getElementById('progress-container').classList.add('show');
                    let progress = 0;
                    const interval = setInterval(() => {
                        progress += 10;
                        document.getElementById('progress-bar').style.width = progress + '%';
                        document.getElementById('progress-bar').textContent = progress + '%';
                        if (progress >= 100) {
                            clearInterval(interval);
                            showFinalMessage();
                        }
                    }, 500); // Change the speed as needed
                }

                function showFinalMessage() {
                    document.getElementById('progress-container').classList.remove('show');
                    const images = {
                        espresso: 'https://imgur.com/lwIFo1v.jpg',
                        latte: 'https://imgur.com/m2bopcn.jpg',
                        cappuccino: 'https://imgur.com/2Oro4MW.jpg',
                        mocha: 'https://imgur.com/ZoUtAqX.jpg',
                        americano: 'https://imgur.com/TUOCuZs.jpg',
                        macchiato: 'https://imgur.com/1BGVEzR.jpg'
                    };
                    document.getElementById('final-image').src = images[selectedCoffee];
                    document.getElementById('final-message').classList.add('show');
                }
            </script>
        </body>
        </html>
        """;

        // Write content in file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile))) {
            writer.write(htmlContent);
            System.out.println("The HTML file has been successfully created on the desktop.");

            // Open HTML file in default web browser
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
        startFullHTMLApp();
    }
}
