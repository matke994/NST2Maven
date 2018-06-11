/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flyway;

import org.flywaydb.core.Flyway;

/**
 *
 * @author Nikola
 */
public class App {
    
        public void migrate(){
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:mysql://localhost:3306/veleprodaja", "root", "root");
        flyway.setLocations("filesystem:C:/Users/nikola.matic/Documents/NetBeansProjects/VeleprodajaRestMaven/src/main/java/migration");
        flyway.migrate();
        }
}
