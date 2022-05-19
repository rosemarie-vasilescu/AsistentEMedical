import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Cont implements Serializable {

    private String user;

    private String parola;

    private String tip;

    private ArrayList<Cont> cont;

    Cont(String user, String parola, String tip) throws IOException {
        this.user = user;
        this.parola = parola;
        this.tip = tip;
    }

    public String getUser() {
        return user;
    }

    public String getParola() {
        return parola;
    }

    public String getTip() {
        return tip;
    }

    public String toString() {
        return "Nume: " + this.user + "Parola: " + this.parola + "Tip: " + this.tip;
    }
}
