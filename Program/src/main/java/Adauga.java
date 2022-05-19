import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Adauga extends JFrame {

    private Medicament medicament, m1;

    private ManagerMedicament m;

    private String mod;

    JButton bSalveaza, bAnuleaza;

    JLabel lNume, lCompozitie, lIndicatii, lContraindicatii, lModAdministrare, lCompensat, lPret, lCantitate;

    JTextField tNume, tPret, tCantitate, tCompozitie, tIndicatii, tContraindicatii, tModAdministrare;

    JComboBox cbCompensat;

    Container cp;

    JPanel p1, p2;

    Adauga(ManagerMedicament m, String mod) {
        super("Adaugare medicament");
        this.mod = mod;
        this.m = m;
        AscultatorB ab = new AscultatorB();
        this.medicament = new Medicament();
        cp = getContentPane();
        bSalveaza = new JButton("Salveaza");
        bSalveaza.addActionListener(ab);
        bAnuleaza = new JButton("Anuleaza");
        bAnuleaza.addActionListener(ab);
        cbCompensat = new JComboBox(new String[] { "Fara", "Afectiuni psihice", "Afectiuni cronice" });
        AscultatorLista al = new AscultatorLista();
        cbCompensat.addItemListener(al);
        lNume = new JLabel("Nume: ");
        lCompozitie = new JLabel("Compozitie: ");
        lIndicatii = new JLabel("Indicatii: ");
        lContraindicatii = new JLabel("Contraindicatii: ");
        lModAdministrare = new JLabel("Mod de administrare: ");
        lCompensat = new JLabel("Compensat: ");
        lPret = new JLabel("Pret: ");
        lCantitate = new JLabel("In stoc: ");
        tNume = new JTextField(15);
        tPret = new JTextField(15);
        tCantitate = new JTextField(15);
        tCompozitie = new JTextField(30);
        tIndicatii = new JTextField(30);
        tContraindicatii = new JTextField(30);
        tModAdministrare = new JTextField(30);
        p1 = new JPanel();
        p1.setLayout(new GridLayout(8, 2));
        p1.add(lNume);
        p1.add(tNume);
        p1.add(lCompozitie);
        p1.add(tCompozitie);
        p1.add(lIndicatii);
        p1.add(tIndicatii);
        p1.add(lContraindicatii);
        p1.add(tContraindicatii);
        p1.add(lModAdministrare);
        p1.add(tModAdministrare);
        p1.add(lPret);
        p1.add(tPret);
        p1.add(lCantitate);
        p1.add(tCantitate);
        p1.add(lCompensat);
        p1.add(cbCompensat);
        p2 = new JPanel();
        p2.add(bSalveaza);
        p2.add(bAnuleaza);
        cp.add(p1, BorderLayout.CENTER);
        cp.add(p2, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class AscultatorLista implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            String optiune = new String((String) cbCompensat.getSelectedItem());
        }
    }

    class AscultatorB implements ActionListener {

        public boolean verifica() {
            double pret;
            int cantitate;
            boolean ok = true;
            if (tNume.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campul nume trebuie completat!", "Error", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (tIndicatii.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campul indicatii trebuie completat!", "Error", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (tCompozitie.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campul compozitie trebuie completat!", "Error", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (tModAdministrare.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campul mod de administrare trebuie completat!", "Error", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (tContraindicatii.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campul contraindicatii trebuie completat!", "Error", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (tPret.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campul pret trebuie completat!", "Error", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (tCantitate.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campul cantitate trebuie completat!", "Error", JOptionPane.ERROR_MESSAGE);
                ok = false;
            }
            try {
                pret = Double.parseDouble(tPret.getText());
                if (pret < 0) {
                    JOptionPane.showMessageDialog(null, "Pretul trebuie sa fie un numar pozitiv!", "Error", JOptionPane.ERROR_MESSAGE);
                    ok = false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Pretul trebuie sa fie un numar real!", "Error", JOptionPane.ERROR_MESSAGE);
                ok = false;
            }
            try {
                cantitate = Integer.parseInt(tCantitate.getText());
                if (cantitate < 0) {
                    JOptionPane.showMessageDialog(null, "Cantitatea trebuie sa fie un numar pozitiv!", "Error", JOptionPane.ERROR_MESSAGE);
                    ok = false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Cantitatea trebuie sa fie un numar intreg!", "Error", JOptionPane.ERROR_MESSAGE);
                ok = false;
                ;
            }
            return ok;
        }

        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == bSalveaza) {
                if (verifica() == true) {
                    medicament = new Medicament(tNume.getText(), tCompozitie.getText(), tIndicatii.getText(), tContraindicatii.getText(), tModAdministrare.getText(), (String) cbCompensat.getSelectedItem(), tPret.getText(), tCantitate.getText());
                    m.salveaza(medicament, tNume.getText());
                    JOptionPane.showMessageDialog(null, "Articolul a fost adaugat cu succes");
                    new MyFrame(mod);
                    dispose();
                }
            } else if (ev.getSource() == bAnuleaza) {
                new MyFrame(mod);
                dispose();
            }
        }
    }
}
