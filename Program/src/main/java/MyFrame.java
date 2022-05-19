import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class MyFrame extends JFrame {

    private String[] medicamente, compozitie;

    private ArrayList<Medicament> medicament;

    private ManagerMedicament med;

    private String mod;

    JButton bCauta, bAdauga, bListare, bActualizeaza, bCumpara, bDeconectare;

    JLabel lInit, lAfiseazaStoc, lCauta;

    JComboBox cbFiltru, cbOptiune, cbNume, cbCompozitie;

    Container cp;

    Autentificare a;

    JPanel p1, p2, p3, p4, p5;

    MyFrame(String mod) {
        super(mod);
        this.mod = mod;
        this.med = ManagerMedicament.getInstanta();
        compozitie = new String[med.fisiere().size()];
        for (int i = 0; i < med.fisiere().size(); i++) {
            compozitie[i] = med.getMed().get(i).getCompozitie();
        }
        medicamente = med.fisiere().toArray(new String[0]);
        AscultatorButton b = new AscultatorButton();
        cp = getContentPane();
        Font font = new Font("Verdana", Font.PLAIN, 12);
        Font font1 = new Font("Verdana", Font.BOLD + Font.ITALIC, 10);
        bCauta = new JButton(" Cauta  ");
        bCauta.setFont(font);
        bAdauga = new JButton(" Adauga ");
        bAdauga.setFont(font);
        bListare = new JButton(" Afisare ");
        bListare.setFont(font);
        bCumpara = new JButton("Cumpara");
        bCumpara.setFont(font);
        bActualizeaza = new JButton("Editeaza");
        bActualizeaza.setFont(font);
        bDeconectare = new JButton("Deconectare");
        bDeconectare.setFont(font);
        cbFiltru = new JComboBox(new String[] { "Nume", "Indicatii", "Pret" });
        cbOptiune = new JComboBox(new String[] { "Nume", "Compozitie" });
        cbNume = new JComboBox(medicamente);
        cbCompozitie = new JComboBox(compozitie);
        cbNume.setVisible(false);
        cbCompozitie.setVisible(false);
        AscultatorLista al = new AscultatorLista();
        cbFiltru.addItemListener(al);
        cbOptiune.addItemListener(al);
        cbOptiune.addActionListener(b);
        cbNume.addItemListener(al);
        cbCompozitie.addItemListener(al);
        lAfiseazaStoc = new JLabel("Afiseaza dupa: ");
        lAfiseazaStoc.setFont(font1);
        lCauta = new JLabel("Cauta dupa: ");
        lCauta.setFont(font1);
        bAdauga.addActionListener(b);
        bCauta.addActionListener(b);
        bListare.addActionListener(b);
        bActualizeaza.addActionListener(b);
        bCumpara.addActionListener(b);
        bDeconectare.addActionListener(b);
        if (mod.equalsIgnoreCase("Personal")) {
            bActualizeaza.setVisible(false);
            bAdauga.setVisible(false);
        }
        if (mod.equalsIgnoreCase("Administrator")) {
            bCumpara.setVisible(false);
        }
        p1 = new JPanel();
        p1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        p1.add(lCauta, c);
        c.gridx = 1;
        c.gridy = 0;
        p1.add(cbOptiune, c);
        c.gridx = 2;
        c.gridy = 0;
        p1.add(cbNume, c);
        p1.add(cbCompozitie, c);
        c.gridx = 4;
        c.gridy = 0;
        p1.add(bCauta, c);
        c.gridx = 0;
        c.gridy = 1;
        p1.add(lAfiseazaStoc, c);
        c.gridx = 1;
        c.gridy = 1;
        p1.add(cbFiltru, c);
        c.gridx = 4;
        c.gridy = 1;
        p1.add(bListare, c);
        c.gridx = 4;
        c.gridy = 2;
        p1.add(bActualizeaza, c);
        c.gridx = 4;
        c.gridy = 3;
        p1.add(bCumpara, c);
        c.gridx = 4;
        c.gridy = 4;
        p1.add(bAdauga, c);
        c.gridx = 4;
        c.gridy = 5;
        p1.add(bDeconectare, c);
        p1.setBorder(BorderFactory.createTitledBorder("Bine ati venit!"));
        cp.add(p1, BorderLayout.PAGE_START);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class AscultatorLista implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            String optiune = new String((String) cbFiltru.getSelectedItem());
        }
    }

    class AscultatorButton implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == bAdauga) {
                new Adauga(med, mod);
                dispose();
            } else if (ev.getSource() == cbOptiune) {
                if (((String) cbOptiune.getSelectedItem()).equals("Nume")) {
                    cbCompozitie.setVisible(false);
                    cbNume.setVisible(true);
                    pack();
                    if (ev.getSource() == bCauta) {
                        new Afisare((String) cbNume.getSelectedItem(), med, mod);
                        dispose();
                    }
                } else if (((String) cbOptiune.getSelectedItem()).equals("Compozitie")) {
                    cbNume.setVisible(false);
                    cbCompozitie.setVisible(true);
                    pack();
                    if (ev.getSource() == bCauta) {
                        new Afisare((String) cbCompozitie.getSelectedItem(), med, mod);
                        dispose();
                    }
                }
            } else if (ev.getSource() == bCauta) {
                if (((String) cbOptiune.getSelectedItem()).equals("Nume")) {
                    new Afisare((String) cbNume.getSelectedItem(), med, mod);
                    dispose();
                } else if (((String) cbOptiune.getSelectedItem()).equals("Compozitie")) {
                    new Afisare((String) cbCompozitie.getSelectedItem(), med, mod);
                    dispose();
                }
            } else if (ev.getSource() == bListare) {
                new Listare((String) cbFiltru.getSelectedItem(), med, mod);
                dispose();
            } else if (ev.getSource() == bActualizeaza) {
                new Editare(med, mod);
                dispose();
            } else if (ev.getSource() == bCumpara) {
                new Vanzare(med, mod);
                dispose();
            } else if (ev.getSource() == bDeconectare) {
                new Autentificare();
                dispose();
            } else
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
