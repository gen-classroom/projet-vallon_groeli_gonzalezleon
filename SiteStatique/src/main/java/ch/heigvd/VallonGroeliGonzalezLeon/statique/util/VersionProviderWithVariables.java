/*
 * @File VersionProviderWithVariables.java
 * @Authors : David González León, Jade Gröli, Axel Vallon
 * @Date 5 juin 2021
 */
package ch.heigvd.VallonGroeliGonzalezLeon.statique.util;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.Statique;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import picocli.CommandLine;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * A version provider class. This will provide the version located in the pom.xml file
 */
public class VersionProviderWithVariables implements CommandLine.IVersionProvider {
   public String[] getVersion() throws IOException, XmlPullParserException {
      MavenXpp3Reader reader = new MavenXpp3Reader();
      Model model;
      if ((new File("pom.xml")).exists()) {
         try (FileReader fileReader = new FileReader("pom.xml")) {
            model = reader.read(fileReader);
         }
      } else {
         try (InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(Statique.class
                                                                                                         .getResourceAsStream(
                                                                                                                 "/META-INF/maven/ch.heigvd.VallonGroeliGonzalezLeon/SiteStatique/pom.xml")))) {
            model = reader.read(inputStreamReader);
         }
      }
      return new String[]{model.getVersion()};
   }
}
