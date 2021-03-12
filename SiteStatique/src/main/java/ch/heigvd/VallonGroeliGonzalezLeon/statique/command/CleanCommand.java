package com.mycompany.app.command;

import picocli.CommandLine;

@CommandLine.Command(
        name = "clean",
        description = "Picocli clean command"
)

public class CleanCommand implements Runnable {
    public static void main(String[] args) {
        CommandLine.run(new CleanCommand(), args);
    }

    @Override
    public void run() {
        System.out.println("clean");
    }
}
