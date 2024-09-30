package org.example;

import javax.swing.*;

public class StartScreen {
    public static void Screen() {
        String[] option = {"Mode", "Close"};
        int choose = JOptionPane.showOptionDialog(null, "Choose option", "Option Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, option, "Mode");
        switch (choose) {
            case 0 -> openMode();
            case 1 -> closeApp();
            default -> {
            }
        }
    }
        private static void openMode() {
            String[] modeOption = {
                "Console App", "JavaFX App", "HTML App", "Full HTML App"
            } ;
            int chooseMode = JOptionPane.showOptionDialog(null, "Mode option", "Mode Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, modeOption, "Mode option");

            switch (chooseMode) {
                case 0 -> openConsoleApp();
                case 1 -> openJavaFXApp();
                case 2 -> openHTMLApp();
                case 3 -> openFullHTMLApp();
                case 4 -> closeApp();
                default -> {
                }
            }
        }

        private static void openConsoleApp () {
            org.example.ConsoleApp.startConsoleApp();
        }
        ;
        private static void openJavaFXApp () { org.example.JavaFXApp.startJavaFXApp();
        }
        private static void openHTMLApp () {
            org.example.HTMLApp.startHTMLApp();
        }
        private static void openFullHTMLApp () { org.example.FullHTMLApp.startFullHTMLApp();
        }
        private static void closeApp () {
            System.exit(0);
        }
    }

