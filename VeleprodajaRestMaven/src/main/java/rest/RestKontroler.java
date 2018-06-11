/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;


import db.DBBroker;
import domen.Narudzbenica;
import domen.Pravnolice;
import domen.Proizvod;
import domen.Racun;
import domen.Radnik;
import domen.Stopapdv;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Nikola
 */
@Path("/")
public class RestKontroler {

    DBBroker db = new DBBroker();

    @GET
    @Path("proizvodi")
    @Produces(MediaType.APPLICATION_XML)
    public Response vratiProizvode() {
        try {
            List<Proizvod> proizvodi = db.ucitajProizvode();
            GenericEntity<List<Proizvod>> ge = new GenericEntity<List<Proizvod>>(proizvodi) {
            };

            return Response.ok(ge).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Greška u učitavanju proizvoda.").build();
        }

    }

    @POST
    @Path("proizvodi")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response sacuvajProizvod(Proizvod proizvod) {
        try {
            db.sacuvajProizvod(proizvod);

            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(e.getMessage()).build();
        }

    }
    
    
    @GET
    @Path("radnik/{kIme}/{kSifra}")
    @Produces(MediaType.APPLICATION_XML)
    public Response proveriRadnika(@PathParam("kIme") String kIme, @PathParam("kSifra") String kSifra) {
        try {
            Radnik r = db.proveriRadnika(kIme,kSifra);

            return Response.ok(r).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Greška u proveri radnika.").build();
        }

    }

    @PUT
    @Path("proizvodi")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response izmeniProizvod(Proizvod proizvod) {
        try {
            db.izmeniProizvod(proizvod);

            return Response.ok().build();

        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(e.getMessage()).build();
        }

    }
    
    
    
    @DELETE
    @Path("proizvodi/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response izbrisiProizvod(@PathParam("id") int id) {
        try {
            db.obrisiProizvod(id);

            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Sistem ne može da obriše proizvod.").build();
        }

    }
    
    @POST
    @Path("narudzbenica")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response sacuvajNarudzbenicu(Narudzbenica narudzbenica) {
        try {
            db.sacuvajNarudzbenicu(narudzbenica);

            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(e.getMessage()).build();
        }

    }
    
    
    @GET
    @Path("stope")
    @Produces(MediaType.APPLICATION_XML)
    public Response vratiStope() {
        try {
            List<Stopapdv> stope = db.ucitajStope();
            GenericEntity<List<Stopapdv>> ge = new GenericEntity<List<Stopapdv>>(stope) {
            };

            return Response.ok(ge).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Greška u učitavanju proizvoda.").build();
        }

    }
    
    
    @GET
    @Path("stopaPDV/{sifra}")
    @Produces(MediaType.APPLICATION_XML)
    public Response vratiStopuPDV(@PathParam("sifra") int sifra) {
        try {
            Stopapdv stopa = (Stopapdv) db.pronadjiStopuPDV(sifra); 

            return Response.ok(stopa).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Greška u nalaženju stopePDV.").build();
        }

    }
    
    @GET
    @Path("pravnoLice/{pib}")
    @Produces(MediaType.APPLICATION_XML)
    public Response vratiPravnoLice(@PathParam("pib") int pib) {
        try {
            Pravnolice pravnoLice = (Pravnolice) db.pronadjiPravnoLice(pib);

            return Response.ok(pravnoLice).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Greška u nalaženju pib-a.").build();
        }

    }
    
    
     @GET
    @Path("pravnaLica")
    @Produces(MediaType.APPLICATION_XML)
    public Response vratiPravnaLica() {
        try {
            List<Pravnolice> pl = db.ucitajPravnaLica();
            GenericEntity<List<Pravnolice>> ge = new GenericEntity<List<Pravnolice>>(pl) {
            };

            return Response.ok(ge).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Greška u učitavanju pravnih lica.").build();
        }

    }
    
    @POST
    @Path("racun")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response sacuvajRacun(Racun racun) {
        try {
            db.sacuvajRacun(racun);

            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(e.getMessage()).build();
        }

    }
    
     @GET
    @Path("racuni")
    @Produces(MediaType.APPLICATION_XML)
    public Response vratiRacune() {
        try {
            List<Racun> racuni = db.ucitajRacune();
            GenericEntity<List<Racun>> ge = new GenericEntity<List<Racun>>(racuni) {
            };

            return Response.ok(ge).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Greška u učitavanju računa").build();
        }

    }
    
     @GET
    @Path("narudzbenice")
    @Produces(MediaType.APPLICATION_XML)
    public Response vratiNarudzbine() {
        try {
            List<Narudzbenica> n = db.ucitajNarudzbenice();
            GenericEntity<List<Narudzbenica>> ge = new GenericEntity<List<Narudzbenica>>(n) {
            };

            return Response.ok(ge).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Greška u učitavanju narudžbenica").build();
        }

    }
    
    
     @DELETE
    @Path("narudzbenice/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response izbrisiNarudzbenicu(@PathParam("id") int id) {
        try {
            db.obrisiNarudzbenicu(id);

            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Sistem ne može da obriše narudžbenicu.").build();
        }

    }
    

}
