import java.util.ArrayList;
import java.util.Date;

abstract class Persoon {
    private String naam;
    private String email;

    public Persoon(String naam, String email) {
        this.naam = naam;
        this.email = email;
    }

    public String getNaam() {
        return naam;
    }

    public String getEmail() {
        return email;
    }

    public abstract void toonProfiel();
}

class PGBHouder extends Persoon {
    private int pgbHouderID;

    public PGBHouder(String naam, String email, int pgbHouderID) {
        super(naam, email);
        this.pgbHouderID = pgbHouderID;
    }

    public void bekijkOverzicht() {
        System.out.println("Overzicht voor PGB-houder: " + getNaam());
    }

    @Override
    public void toonProfiel() {
        System.out.println("PGB-houder: " + getNaam() + " - Email: " + getEmail());
    }
}

class Mantelzorger extends Persoon {
    private int mantelzorgerID;
    private String beschikbaarheid;
    private PGBHouder gekoppeldePGBHouder;
    private ArrayList<UurRegistratie> registratie;
    private ArrayList<Planning> planning;

    public Mantelzorger(String naam, String email, int mantelzorgerID, String beschikbaarheid, PGBHouder gekoppeldePGBHouder) {
        super(naam, email);
        this.mantelzorgerID = mantelzorgerID;
        this.beschikbaarheid = beschikbaarheid;
        this.gekoppeldePGBHouder = gekoppeldePGBHouder;
        this.registratie = new ArrayList<>();
        this.planning = new ArrayList<>();
    }

    public void registreerUren(UurRegistratie registratie) {
        this.registratie.add(registratie);
    }

    public void voegPlanningToe(Planning planning) {
        this.planning.add(planning);
    }

    public void toonBeschikbaarheid() {
        System.out.println("Beschikbaarheid van " + getNaam() + ": " + beschikbaarheid);
    }

    @Override
    public void toonProfiel() {
        System.out.println("Mantelzorger: " + getNaam() + " - Email: " + getEmail());
    }
}

class UurRegistratie {
    private int uurRegistratieID;
    private Date datum;
    private int aantalUren;
    private Mantelzorger mantelzorger;

    public UurRegistratie(int uurRegistratieID, Date datum, int aantalUren, Mantelzorger mantelzorger) {
        this.uurRegistratieID = uurRegistratieID;
        this.datum = datum;
        this.aantalUren = aantalUren;
        this.mantelzorger = mantelzorger;
    }

    public void voegUurToe(int uren) {
        this.aantalUren += uren;
    }
}

class Patient {
    private int patientID;
    private String naam;
    private PGBHouder gekoppeldePGBHouder;

    public String getNaam() {
        return naam;
    }

    public Patient(int patientID, String naam, PGBHouder gekoppeldePGBHouder) {
        this.patientID = patientID;
        this.naam = naam;
        this.gekoppeldePGBHouder = gekoppeldePGBHouder;
    }

    public void bekijkPGBHouder() {
        System.out.println("PGB-houder van patiënt " + naam + " is " + gekoppeldePGBHouder.getNaam());
    }
}

class Planning {
    private int planningID;
    private Date datum;
    private String tijdstip;
    private Mantelzorger mantelzorger;
    private Patient patient;

    public Planning(int planningID, Date datum, String tijdstip, Mantelzorger mantelzorger, Patient patient) {
        this.planningID = planningID;
        this.datum = datum;
        this.tijdstip = tijdstip;
        this.mantelzorger = mantelzorger;
        this.patient = patient;
    }

    public void planDienst() {
        System.out.println("Dienst gepland voor patiënt " + patient.getNaam() + " op " + datum + " om " + tijdstip);
    }
}

class Gezondheidsgegevens {
    private int gezondheidsgegevensID;
    private String medicatie;
    private double bloedsuikerwaarde;
    private double slaap;
    private Date datum;
    private Patient patient;

    public Gezondheidsgegevens(int gezondheidsgegevensID, String medicatie, double bloedsuikerwaarde, double slaap, Date datum, Patient patient) {
        this.gezondheidsgegevensID = gezondheidsgegevensID;
        this.medicatie = medicatie;
        this.bloedsuikerwaarde = bloedsuikerwaarde;
        this.slaap = slaap;
        this.datum = datum;
        this.patient = patient;
    }

    public void registreerGezondheid() {
        System.out.println("Gezondheidsgegevens geregistreerd voor patiënt " + patient.getNaam());
    }
}

public class Main {
    public static void main(String[] args) {
        PGBHouder pgbHouder = new PGBHouder("Hamza", "hamza@gmail.com", 1);
        Mantelzorger mantelzorger = new Mantelzorger("Robin", "robin@gmail.com", 1, "Maandag en Woensdag beschikbaar", pgbHouder);
        Patient patient = new Patient(1, "Jan", pgbHouder);
        Planning planning = new Planning(1, new Date(), "09:00", mantelzorger, patient);

        mantelzorger.voegPlanningToe(planning);

        mantelzorger.toonProfiel();
        mantelzorger.toonBeschikbaarheid();
        planning.planDienst();
        patient.bekijkPGBHouder();
        pgbHouder.bekijkOverzicht();
    }
}
