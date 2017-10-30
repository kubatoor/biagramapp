package com.demo;

public class Main {
    /**
     * Starts the application by calling the
     * main method of the application.
     * Accepts file path as a command line argument
     * and passes it to main method for further execution
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        System.out.println("The file path being used is "+args[0]);
        new BiagramApp().run(args[0]);
    }
}
