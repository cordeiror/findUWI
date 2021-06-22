package com.utilscords;

/**
 * Finder in zip files helper tool
 *
 */
public class App {
    public static void main( String[] args )
    {
        ZipFinder zipFinder = new ZipFinder();
        Screen screen = new Screen();

        screen.createUIComponents(zipFinder);


        String directory;
        String textToFind = args[0];

        if (args.length > 1) {
            directory = args[1];
        } else {
            directory = "C:\\";
        }
    }
}
