import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class ManagerCont {

    private ObjectOutputStream oos;

    private ObjectInputStream ois;

    private static ManagerCont instanta;

    private ArrayList<Cont> cont;

    private ManagerCont() {
        this.cont = new ArrayList();
    }

    public static ManagerCont getInstanta() {
        if (instanta == null) {
            instanta = new ManagerCont();
        }
        return instanta;
    }

    public void salveazaCont(Cont a, String file) {
        try {
            if (fisiere().contains(a.getUser()))
                JOptionPane.showMessageDialog(null, "Numele exista deja!");
            else {
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(a);
                oos.close();
                FileWriter myWriter = new FileWriter("nume.txt", true);
                myWriter.write(a.getUser() + "#");
                myWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cont citesteCont(String file) {
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            return (Cont) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Cont> getCont() {
        for (int i = 0; i < fisiere().size(); i++) {
            this.cont.add(citesteCont(fisiere().get(i)));
        }
        return cont;
    }

    public String gaseste(String nume) {
        int nr = 0;
        for (int i = 0; i < fisiere().size(); i++) {
            if (nume.equals(getCont().get(i).getUser())) {
                nr = i;
                break;
            }
        }
        return getCont().get(nr).getTip();
    }

    public ArrayList<String> fisiere() {
        ArrayList<String> nume = new ArrayList<String>();
        try {
            String tmp[];
            BufferedReader buf = new BufferedReader(new FileReader("nume.txt"));
            tmp = buf.readLine().split("#");
            nume = new ArrayList<String>(Arrays.asList(tmp));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nume;
    }
}
