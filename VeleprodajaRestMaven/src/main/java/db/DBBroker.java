/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Narudzbenica;
import domen.Pravnolice;
import domen.Proizvod;
import domen.Racun;
import domen.Radnik;
import domen.Stopapdv;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nikola
 */
@Named("DBBroker")
@ApplicationScoped
public class DBBroker {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("VeleprodajaRestPU");

    public DBBroker() {
    }

    public Radnik prijaviRadnika(Radnik radnik) throws Exception {

        EntityManager em = emf.createEntityManager();
        List<Radnik> lr = em.createQuery("SELECT r FROM Radnik r WHERE r.korisnickoIme=:u AND r.lozinka=:pass").setParameter("u", radnik.getKorisnickoIme()).setParameter("pass", radnik.getLozinka()).getResultList();

        if (lr.size()
                != 1) {
            throw new Exception("Korisnik nije autentifikovan. Pogresno korisnicko ime i/ili sifra");
        }

        em.close();

        emf.close();

        return lr.get(0);
    }

    public void sacuvajProizvod(Proizvod proizvodUnos) throws Exception {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(proizvodUnos);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            e.printStackTrace();
            throw e;
        }
    }

    public Proizvod pronadjiProizvod(int proizvodID) {
        EntityManager em = emf.createEntityManager();
        Proizvod p = em.find(Proizvod.class, proizvodID);
        return p;
    }

    public List<Proizvod> ucitajProizvode() {
        EntityManager em = emf.createEntityManager();
        List<Proizvod> proizvodi = em.createNamedQuery("Proizvod.findAll").getResultList();

        return proizvodi;
    }

    public Radnik proveriRadnika(String kIme, String kSifra) {
        EntityManager em = emf.createEntityManager();

        try {
            Radnik r = (Radnik) em.createQuery("SELECT r FROM Radnik r WHERE r.korisnickoIme = :kIme AND r.lozinka = :kSifra")
                    .setParameter("kIme", kIme).setParameter("kSifra", kSifra).getSingleResult();

            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void izmeniProizvod(Proizvod proizvodIzmena) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(proizvodIzmena);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            e.printStackTrace();
            throw e;
        }
    }

    public void obrisiProizvod(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Proizvod p = em.find(Proizvod.class, id);
            em.remove(p);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            e.printStackTrace();
            throw e;
        }
    }

    public void sacuvajNarudzbenicu(Narudzbenica narudzbenica) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(narudzbenica);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            e.printStackTrace();
            throw e;
        }
    }

    public List<Stopapdv> ucitajStope() {
        EntityManager em = emf.createEntityManager();

        List<Stopapdv> stope = em.createNamedQuery("Stopapdv.findAll").getResultList();

        return stope;
    }
    
    public Stopapdv pronadjiStopuPDV(int sifra) {
        EntityManager em = emf.createEntityManager();
        Stopapdv s = em.find(Stopapdv.class, sifra);
        return s;
    }
    
     public Pravnolice pronadjiPravnoLice(int pib) {
        EntityManager em = emf.createEntityManager();
        Pravnolice pravno = em.find(Pravnolice.class, pib);
        return pravno;
    }

    public List<Pravnolice> ucitajPravnaLica() {
        EntityManager em = emf.createEntityManager();

        List<Pravnolice> pravnaLica = em.createNamedQuery("Pravnolice.findAll").getResultList();

        return pravnaLica;
    }

    public void sacuvajRacun(Racun racun) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(racun);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            e.printStackTrace();
            throw e;
        }
    }

    public List<Racun> ucitajRacune() {
        EntityManager em = emf.createEntityManager();
        List<Racun> racuni = em.createNamedQuery("Racun.findAll").getResultList();

        return racuni;
    }

    public List<Narudzbenica> ucitajNarudzbenice() {
        EntityManager em = emf.createEntityManager();
        List<Narudzbenica> narudzbenice = em.createNamedQuery("Narudzbenica.findAll").getResultList();

        return narudzbenice;
    }

    public void obrisiNarudzbenicu(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Narudzbenica n = em.find(Narudzbenica.class, id);
            em.remove(n);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            e.printStackTrace();
            throw e;
        }
    }
    
}
