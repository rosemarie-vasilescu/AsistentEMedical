import java.util.*;
import java.io.*;

public class Medicament implements Serializable {

    private String nume;

    private String compozitie;

    private String indicatii;

    private String contraindicatii;

    private String modadministrare;

    private String pret;

    private String cantitate;

    private String compensat;

    private static double TVA = 0.1;

    static final long serialVersionUID = 7885127671967291744L;

    public Medicament() {
    }

    public Medicament(String nume, String compozitie, String indicatii, String contraindicatii, String modadministrare, String compensat, String pret, String cantitate) {
        this.nume = nume;
        this.compozitie = compozitie;
        this.indicatii = indicatii;
        this.contraindicatii = contraindicatii;
        this.modadministrare = modadministrare;
        this.pret = String.valueOf(Integer.parseInt(pret) + Integer.parseInt(pret) * TVA);
        this.cantitate = cantitate;
        this.compensat = compensat;
    }

    public String calculeazaPret() {
        return String.valueOf((Integer.parseInt(this.pret) + Integer.parseInt(this.pret) * TVA));
    }

    public static Comparator<Medicament> SortbyName = new Comparator<Medicament>() {

        @Override
        public int compare(Medicament a, Medicament b) {
            return a.getNume().compareTo(b.getNume());
        }
    };

    public static Comparator<Medicament> SortbyDisease = new Comparator<Medicament>() {

        @Override
        public int compare(Medicament a, Medicament b) {
            return a.getIndicatii().compareTo(b.getIndicatii());
        }
    };

    public static Comparator<Medicament> SortbyPrice = new Comparator<Medicament>() {

        @Override
        public int compare(Medicament a, Medicament b) {
            return (int) (Double.parseDouble(a.getPret()) - Double.parseDouble(b.getPret()));
        }
    };

    public String toString() {
        return "<html>Nume: " + this.nume + "<br> " + "Compozitie: " + this.compozitie + "<br> " + "Indicatii: " + this.indicatii + "<br>" + "<html>Contraindicatii: <html>" + this.contraindicatii + "<br>" + "Mod de administrare: " + this.modadministrare + "<br>" + "Compensat: " + this.compensat + "<br>" + "Pret: " + this.pret + "<br>" + "Cantitate: " + this.cantitate + "<html>";
    }

    public String getNume() {
        return nume;
    }

    public String getCompozitie() {
        return compozitie;
    }

    public String getIndicatii() {
        return indicatii;
    }

    public String getContraindicatii() {
        return contraindicatii;
    }

    public String getModadministrare() {
        return modadministrare;
    }

    public String getPret() {
        return pret;
    }

    public String getCantitate() {
        return cantitate;
    }

    public String getCompensat() {
        return compensat;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }

    public void setCompozitie(String compozitie) {
        this.compozitie = compozitie;
    }

    public void setCantitate(String cantitate) {
        this.cantitate = cantitate;
    }
}
