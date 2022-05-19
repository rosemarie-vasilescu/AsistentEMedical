import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Afisare extends JFrame {

    private ManagerMedicament m;

    private String mod;

    JButton bBack, bCauta;

    JLabel lText[];

    JComboBox cbNume, cbCompozitie;

    Container cp;

    JPanel p1, p2, p3;

    Afisare(String nume, ManagerMedicament m, String mod) {
        super(nume);
        this.m = m;
        this.mod = mod;
        AscultatorB b = new AscultatorB();
        cp = getContentPane();
        bBack = new JButton("Back");
        bBack.addActionListener(b);
        AscultatorLista al = new AscultatorLista();
        p1 = new JPanel();
        lText = new JLabel[m.fisiere().size()];
        int ok = 0;
        for (int i = 0; i < m.fisiere().size(); i++) {
            if (nume.equalsIgnoreCase(m.getMed().get(i).getNume()) || m.getMed().get(i).getNume().contains(nume)) {
                lText[i] = new JLabel(m.getMed().get(i).toString() + " ");
                lText[i].setFont(new Font("Verdana", Font.PLAIN, 12));
                p1.add(lText[i]);
                pack();
                ok = 1;
            } else if (nume.equalsIgnoreCase(m.getMed().get(i).getCompozitie()) || m.getMed().get(i).getCompozitie().contains(nume)) {
                lText[i] = new JLabel(m.getMed().get(i).toString() + " ");
                lText[i].setFont(new Font("Verdana", Font.PLAIN, 12));
                p1.add(lText[i]);
                pack();
                ok = 1;
            }
        }
        if (ok == 0) {
            JOptionPane.showMessageDialog(null, "Articolul nu exista");
        }
        p2 = new JPanel();
        p2 = new JPanel();
        p2.add(bBack);
        cp.add(p1, BorderLayout.CENTER);
        cp.add(p2, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class AscultatorLista implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            String optiune = new String((String) cbNume.getSelectedItem());
            String optiune2 = new String((String) cbCompozitie.getSelectedItem());
        }
    }

    class AscultatorB implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == bBack) {
                new MyFrame(mod);
                dispose();
            }
        }
    }
}
