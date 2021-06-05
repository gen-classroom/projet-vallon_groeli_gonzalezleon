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

public class VersionProviderWithVariables implements CommandLine.IVersionProvider {
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
