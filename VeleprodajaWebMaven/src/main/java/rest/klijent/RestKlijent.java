/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.klijent;

import domen.Narudzbenica;
import domen.Proizvod;
import domen.Racun;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:RestKontroler [/]<br>
 * USAGE:
 * <pre>
 *        RestKlijent client = new RestKlijent();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Nikola
 */
@Named("RestKlijent")
@ApplicationScoped
public class RestKlijent {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/VeleprodajaRest/veleprodaja";

    public RestKlijent() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI);
    }

    public <T> T vratiProizvode(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("proizvodi");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public Response sacuvajProizvod(Object requestEntity) throws ClientErrorException {
        return webTarget.path("proizvodi").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public <T> T proveriRadnika(Class<T> responseType, String kIme, String kSifra) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("radnik/{0}/{1}", new Object[]{kIme, kSifra}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.close();
    }

    public Response izmeniProizvod(Object requestEntity) {
        return webTarget.path("proizvodi").request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public Response izbrisiProizvod(String id) {
        return webTarget.path(java.text.MessageFormat.format("proizvodi/{0}", new Object[]{id})).request().delete(Response.class);

    }

    public Response sacuvajNarudzbenicu(Object requestEntity) {
        return webTarget.path("narudzbenica").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);

    }

    public <T> T vratiStope(Class<T> responseType) {
        WebTarget resource = webTarget;
        resource = resource.path("stope");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T vratiStopuPDV(Class<T> responseType, String sifra) {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("stopaPDV/{0}", new Object[]{sifra}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T vratiPravnoLice(Class<T> responseType, String pib) {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("pravnoLice/{0}", new Object[]{pib}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T vratiPravnaLica(Class<T> responseType) {
        WebTarget resource = webTarget;
        resource = resource.path("pravnaLica");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public Response sacuvajRacun(Object requestEntity) {
        return webTarget.path("racun").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);

    }

    public <T> T vratiRacune(Class<T> responseType) {
        WebTarget resource = webTarget;
        resource = resource.path("racuni");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T vratiNarudzbine(Class<T> responseType) {
        WebTarget resource = webTarget;
        resource = resource.path("narudzbenice");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public Response izbrisiNarudzbenicu(String id) {
        return webTarget.path(java.text.MessageFormat.format("narudzbenice/{0}", new Object[]{id})).request().delete(Response.class);

    }

}
