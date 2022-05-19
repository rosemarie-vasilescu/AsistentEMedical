import java.io.*;
import java.util.*;

public class ManagerMedicament {

    private ObjectOutputStream oos;

    private ObjectInputStream ois;

    private static ManagerMedicament instanta;

    private ArrayList<Medicament> med;

    private ManagerMedicament() {
        this.med = new ArrayList();
    }

    public static ManagerMedicament getInstanta() {
        if (instanta == null) {
            instanta = new ManagerMedicament();
        }
        return instanta;
    }

    public void salveaza(Medicament a, String numeFisier) {
        try {
            oos = new ObjectOutputStream(new FileOutputStream(numeFisier));
            oos.writeObject(a);
            int ok = 0;
            oos.close();
            FileWriter myWriter = new FileWriter("lista", true);
            for (int i = 0; i < fisiere().size(); i++) {
                if ((a.getNume().equalsIgnoreCase(fisiere().get(i)))) {
                    ok = 1;
                }
            }
            if (ok == 0) {
                myWriter.write(a.getNume() + " ");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Medicament citeste(String numeFisier) {
        try {
            ois = new ObjectInputStream(new FileInputStream(numeFisier));
            return (Medicament) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Medicament> getMed() {
        for (int i = 0; i < fisiere().size(); i++) {
            this.med.add(citeste(fisiere().get(i)));
        }
        return med;
    }

    public ArrayList<String> fisiere() {
        ArrayList<String> nume = new ArrayList<String>();
        try {
            String tmp[];
            BufferedReader buf = new BufferedReader(new FileReader("lista"));
            tmp = buf.readLine().split(" ");
            nume = new ArrayList<String>(Arrays.asList(tmp));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nume;
    }
}
