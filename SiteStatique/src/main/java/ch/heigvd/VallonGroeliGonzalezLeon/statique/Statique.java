/*
 * @File Statique.java
 * @Authors : David González León, Jade Gröli, Axel Vallon
 * @Date 5 mars 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.Build;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.Clean;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.Init;
import ch.heigvd.VallonGroeliGonzalezLeon.statique.command.Serve;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

@Command(name = "Statique", mixinStandardHelpOptions = true,
         description = "Creates and handles the generation of a statique site generator",
         versionProvider = Statique.VersionProviderWithVariables.class,
         subcommands = {Build.class, Clean.class, Init.class, Serve.class})
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

   class VersionProviderWithVariables implements CommandLine.IVersionProvider {
      public String[] getVersion() throws IOException, XmlPullParserException {
         MavenXpp3Reader reader = new MavenXpp3Reader();
         Model model;
         if ((new File("pom.xml")).exists()) {
            FileReader fileReader = new FileReader("pom.xml");
            try {
               model = reader.read(fileReader);
            } finally {
               fileReader.close();
            }
         } else {
            InputStreamReader inputStreamReader = new InputStreamReader(Statique.class.getResourceAsStream(
                    "/META-INF/maven/ch.heigvd.VallonGroeliGonzalezLeon/SiteStatique/pom.xml"));
            try {
               model = reader.read(inputStreamReader);
            } finally {
               inputStreamReader.close();
            }
         }
         return new String[]{model.getVersion()};
      }
   }

}
