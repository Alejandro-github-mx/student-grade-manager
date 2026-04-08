package com.grades;
 
public class Main {
 
    // This is the entry point — Java looks for this exact signature
    public static void main(String[] args) {
        Menu menu = new Menu();  // Create the UI
        menu.run();              // Hand over control to the menu loop
    }
}
