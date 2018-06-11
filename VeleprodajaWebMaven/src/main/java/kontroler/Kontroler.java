/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Narudzbenica;
import domen.Pravnolice;
import domen.Proizvod;
import domen.Racun;
import domen.Radnik;
import domen.Stopapdv;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import rest.klijent.RestKlijent;

/**
 *
 * @author Nikola
 */
@Named("Kontroler")
@ApplicationScoped
public class Kontroler {

    RestKlijent rest = new RestKlijent();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Veleprodaja_WebPU");

    public Kontroler() {
    }

    public void sacuvajProizvod(Proizvod proizvod) throws Exception {
        Response r = rest.sacuvajProizvod(proizvod);

        if (r.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new Exception(r.readEntity(String.class));
        }
    }

    public List<Proizvod> ucitajProizvode() throws Exception {
        Response r = rest.vratiProizvode(Response.class);

        if (r.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<List<Proizvod>> jedinice = new GenericType<List<Proizvod>>() {
            };
            return r.readEntity(jedinice);
        } else {
            throw new Exception(r.readEntity(String.class));
        }
    }

    public Radnik proveriRadnika(Radnik radnik) {
        Response r = rest.proveriRadnika(Response.class, radnik.getKorisnickoIme(), radnik.getLozinka());

        if (r.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<Radnik> r1 = new GenericType<Radnik>() {
            };

            return r.readEntity(r1);
        }

        return null;
    }

    

   

    public  void izbrisiProizvod(Proizvod proizvodIzmena) throws Exception {
        Response r = rest.izbrisiProizvod(String.valueOf(proizvodIzmena.getProizvodID()));
        
        if (r.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new Exception(r.readEntity(String.class));
        }
    }




    public void izmeniProizvod(Proizvod proizvodIzmena) throws Exception {
        Response r = rest.izmeniProizvod(proizvodIzmena);
        
        
        if (r.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new Exception(r.readEntity(String.class));
        }
        
    }

    public void sacuvajNarudzbenicu(Narudzbenica narudzbenicaUnos) throws Exception {
        Response r = rest.sacuvajNarudzbenicu(narudzbenicaUnos);
        
        if (r.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new Exception(r.readEntity(String.class));
        }
    }

    public List<Stopapdv> ucitajStope() throws Exception {
        Response r = rest.vratiStope(Response.class);

        if (r.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<List<Stopapdv>> stope = new GenericType<List<Stopapdv>>() {
            };
            return r.readEntity(stope);
        } else {
            throw new Exception(r.readEntity(String.class));
        }
    }

    public Object pronadjiStopu(int sifraStopaPDV) {
         Response r = rest.vratiStopuPDV(Response.class, String.valueOf(sifraStopaPDV));

        if (r.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<Stopapdv> stopa = new GenericType<Stopapdv>() {
            };

            return r.readEntity(stopa);
        } else {
            return null;
        }
    }

    public Object pronadjiPravnoLice(int pib) {
        Response r = rest.vratiPravnoLice(Response.class, String.valueOf(pib));

        if (r.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<Pravnolice> pravnoLice = new GenericType<Pravnolice>() {
            };

            return r.readEntity(pravnoLice);
        } else {
            return null;
        }
    }

    public List<Pravnolice> ucitajPravnaLica() throws Exception {
        Response r = rest.vratiPravnaLica(Response.class);

        if (r.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<List<Pravnolice>> p = new GenericType<List<Pravnolice>>() {
            };
            return r.readEntity(p);
        } else {
            throw new Exception(r.readEntity(String.class));
        }
    }

    public void sacuvajRacun(Racun racunUnos) throws Exception {
        Response r = rest.sacuvajRacun(racunUnos);
        
        if (r.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new Exception(r.readEntity(String.class));
        }
    }
    
    public List<Racun> ucitajRacune() throws Exception {
        Response r = rest.vratiRacune(Response.class);

        if (r.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<List<Racun>> racuni = new GenericType<List<Racun>>() {
            };
            return r.readEntity(racuni);
        } else {
            throw new Exception(r.readEntity(String.class));
        }
    }
    
    public List<Narudzbenica> ucitajNarudzbine() throws Exception {
        Response r = rest.vratiNarudzbine(Response.class);

        if (r.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<List<Narudzbenica>> n = new GenericType<List<Narudzbenica>>() {
            };
            return r.readEntity(n);
        } else {
            throw new Exception(r.readEntity(String.class));
        }
    }

    public void izbrisiNarudzbenicu(Narudzbenica narudzbenicaIzmena) throws Exception {
        Response r = rest.izbrisiNarudzbenicu(String.valueOf(narudzbenicaIzmena.getSifraNarudzbenice()));
        
        if (r.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new Exception(r.readEntity(String.class));
        }
    }

    
}
