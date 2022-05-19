import java.util.*;
import javax.swing.*;

public class Factura {

    private double pret;

    private String tip;

    private String nume;

    private int cantitate;

    public Factura(String nume, double pret, String tip, int cantitate) {
        this.pret = pret;
        this.tip = tip;
        this.nume = nume;
        this.cantitate = cantitate;
    }

    public double getPret() {
        return pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public String getTip() {
        return tip;
    }

    public String getNume() {
        return nume;
    }

    public String toString() {
        return this.nume + "  ...      " + this.cantitate + " x " + this.pret / this.cantitate + " ... " + this.pret + " ... Reducere:         *" + this.tip + "* " + "\n";
    }

    public String toString1() {
        return "<html>Nume: " + this.nume + "<br>" + this.cantitate + " x " + this.pret / this.cantitate + "<br>" + "Total lei: " + this.pret + "<br>" + "Tip reducere: " + this.tip + "<br>";
    }
}
