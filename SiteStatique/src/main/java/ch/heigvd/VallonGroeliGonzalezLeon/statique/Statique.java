/*
 * @File Statique.java
 * @Authors : David González León
 * @Date 5 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.Build;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.Clean;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.NewCommand;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.ServeCommand;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.*;
import java.util.concurrent.Callable;

@Command(name = "Statique", mixinStandardHelpOptions = true,
         description = "Creates and handles the generation of a statique site generator",
        versionProvider = VersionProviderWithVariables.class,
         subcommands = {Build.class, Clean.class, NewCommand.class, ServeCommand.class})
public class Statique implements Callable<Integer> {

   public static void main(String... args) {
      int exitCode = new CommandLine(new Statique()).execute(args);
      System.exit(exitCode);
   }

   @Override
   public Integer call() throws Exception { // your business logic goes here...
      CommandLine.usage(this, System.out);
      return 0;
   }
}

class VersionProviderWithVariables implements CommandLine.IVersionProvider {
   public String[] getVersion() throws IOException, XmlPullParserException {
      MavenXpp3Reader reader = new MavenXpp3Reader();
      Model model;
      if ((new File("pom.xml")).exists())
         model = reader.read(new FileReader("pom.xml"));
      else
         model = reader.read(
                 new InputStreamReader(
                         Statique.class.getResourceAsStream(
                                 "/META-INF/maven/ch.heigvd.VallonGroeliGonzalezLeon/SiteStatique/pom.xml"
                         )
                 )
         );
      return new String[] {model.getVersion()};
   }
}
