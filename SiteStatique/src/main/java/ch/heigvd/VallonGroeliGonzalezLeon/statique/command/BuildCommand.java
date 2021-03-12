package ch.heigvd.VallonGroeliGonzalezLeon.statique.command;


import picocli.CommandLine;

@CommandLine.Command(
        name = "build",
        description = "build command"
)

public class BuildCommand implements Runnable {
    public static void main(String[] args) {
        CommandLine.run(new BuildCommand(), args);
    }

    @Override
    public void run() {
        System.out.println("Build command");
    }
}