import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

public class Vanzare extends JFrame {

    private static double TVA = 0.1;

    private static double compensatBoliCronice = 0.9;

    private static double compensatBoliPsihice = 1;

    private String pretTotal;

    private String[] nr;

    double total = 0;

    ArrayList<Double> temp = new ArrayList();

    ArrayList<Factura> bon = new ArrayList();

    ArrayList<Medicament> medicament;

    ArrayList<String> nume = new ArrayList();

    ManagerMedicament m;

    JLabel lS, lNumeMed, lTotalCost, lCantitate;

    JTextArea taSubtotal;

    JComboBox cbMedicamente;

    JComboBox cbCantitate;

    JTextField tNumeMed, tTotalCost;

    JButton bSubmit, bPlateste;

    String[] medicamente;

    Container cp;

    JPanel p1, p2;

    String mod;

    Vanzare(ManagerMedicament m, String mod) {
        super("Vanzare");
        this.m = m;
        this.mod = mod;
        tTotalCost = new JTextField(7);
        tTotalCost.setFont(new Font("Verdana", Font.BOLD, 12));
        tTotalCost.setHorizontalAlignment(tTotalCost.CENTER);
        tTotalCost.setEditable(false);
        tNumeMed = new JTextField(15);
        AscultatorB b = new AscultatorB();
        lS = new JLabel("Subtotal: ");
        lNumeMed = new JLabel("Introduceti numele medicamentului: ");
        lTotalCost = new JLabel("Total: ");
        lCantitate = new JLabel("Cantitate:");
        taSubtotal = new JTextArea();
        taSubtotal.setEditable(false);
        medicamente = m.fisiere().toArray(new String[0]);
        cbMedicamente = new JComboBox(medicamente);
        bSubmit = new JButton("Adauga");
        bPlateste = new JButton("Plateste");
        bSubmit.addActionListener(b);
        bPlateste.addActionListener(b);
        nr = new String[100];
        for (int i = 0; i < 100; i++) {
            nr[i] = String.valueOf(i + 1);
        }
        cbCantitate = new JComboBox(nr);
        AscultatorLista al = new AscultatorLista();
        cbCantitate.addItemListener(al);
        cbMedicamente.addItemListener(al);
        cp = getContentPane();
        p1 = new JPanel();
        p1.add(lNumeMed);
        p1.add(cbMedicamente);
        p1.add(lCantitate);
        p1.add(cbCantitate);
        p1.add(bSubmit);
        p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        p2.add(lS, c);
        c.gridx = 1;
        c.gridy = 0;
        p2.add(taSubtotal, c);
        c.gridx = 0;
        c.gridy = 1;
        p2.add(lTotalCost, c);
        c.gridx = 1;
        c.gridy = 1;
        p2.add(tTotalCost, c);
        c.gridx = 2;
        c.gridy = 1;
        p2.add(bPlateste, c);
        cp.add(p1, BorderLayout.CENTER);
        cp.add(p2, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class AscultatorLista implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            String optiune = new String((String) cbCantitate.getSelectedItem());
            String optiune2 = new String((String) cbMedicamente.getSelectedItem());
        }
    }

    class AscultatorB implements ActionListener {

        public String tipCompensare(String nume) {
            String tip = null;
            for (int i = 0; i < m.fisiere().size(); i++) {
                if (nume.equalsIgnoreCase(m.getMed().get(i).getNume())) {
                    tip = m.getMed().get(i).getCompensat();
                }
            }
            return tip;
        }

        public String retetaCompensata(String nume, String pretTotal) {
            String tip = null;
            for (int i = 0; i < m.fisiere().size(); i++) {
                if (nume.equalsIgnoreCase(m.getMed().get(i).getNume())) {
                    tip = m.getMed().get(i).getCompensat();
                }
            }
            if (tip.equalsIgnoreCase("Afectiuni cronice"))
                pretTotal = String.valueOf(Double.parseDouble(pretTotal) - (Double.parseDouble(pretTotal) * compensatBoliCronice));
            else if (tip.equalsIgnoreCase("Afectiuni psihice"))
                pretTotal = String.valueOf(Double.parseDouble(pretTotal) - (Double.parseDouble(pretTotal) * compensatBoliPsihice));
            else if (tip.equalsIgnoreCase("Fara"))
                ;
            pretTotal = String.valueOf(Double.parseDouble(pretTotal));
            return pretTotal;
        }

        public double adaugaProdus(String nume, int cantitate) {
            int ok = 0;
            double pret = 0;
            {
                for (int i = 0; i < m.fisiere().size(); i++) {
                    if (Integer.parseInt(m.getMed().get(i).getCantitate()) > 0) {
                        if (nume.equalsIgnoreCase(m.getMed().get(i).getNume())) {
                            ok = 1;
                            pret = (Double.parseDouble(m.getMed().get(i).getPret())) * cantitate;
                            m.getMed().get(i).setCantitate(String.valueOf(Integer.parseInt(m.getMed().get(i).getCantitate()) - cantitate));
                            m.salveaza(m.getMed().get(i), nume);
                        }
                    }
                }
                if (ok == 0) {
                    JOptionPane.showMessageDialog(null, "Articolul nu mai este in stoc");
                    return -1;
                } else {
                    pret = Double.parseDouble(retetaCompensata(nume, String.valueOf(pret)));
                    return pret;
                }
            }
        }

        public void afiseaza(ArrayList list) {
            for (Object list1 : list) {
                taSubtotal.setText(list.toString().replace("[", "").replace("]", "").replace(",", " "));
                taSubtotal.setFont(new Font("Verdana", Font.BOLD, 12));
                taSubtotal.setBackground(Color.LIGHT_GRAY);
            }
        }

        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == bSubmit) {
                System.out.println((String) cbMedicamente.getSelectedItem());
                System.out.println((String) cbCantitate.getSelectedItem());
                if (adaugaProdus((String) cbMedicamente.getSelectedItem(), Integer.parseInt((String) cbCantitate.getSelectedItem())) == -1) {
                    tNumeMed.setText("");
                } else {
                    total = total + adaugaProdus((String) cbMedicamente.getSelectedItem(), Integer.parseInt((String) cbCantitate.getSelectedItem()));
                    bon.add(new Factura((String) cbMedicamente.getSelectedItem(), adaugaProdus((String) cbMedicamente.getSelectedItem(), Integer.parseInt((String) cbCantitate.getSelectedItem())), tipCompensare((String) cbMedicamente.getSelectedItem()), Integer.parseInt((String) cbCantitate.getSelectedItem())));
                    afiseaza(bon);
                    pack();
                    String result = String.format("%.2f", total);
                    tTotalCost.setText(String.valueOf(result));
                    JOptionPane.showMessageDialog(null, "Produsul a fost adaugat in cos");
                }
            } else if (ev.getSource() == bPlateste) {
                if (tTotalCost.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Nu exista articole in cos");
                else {
                    try {
                        FileWriter stream = new FileWriter("Bont.txt");
                        BufferedWriter output = new BufferedWriter(stream);
                        String newline = System.getProperty("line.separator");
                        output.write(newline);
                        output.write("             Farmacia Sensiblu");
                        output.write(newline);
                        output.write("               Bon fiscal");
                        output.write(newline);
                        output.write(newline);
                        output.write("____________________________________________________________________________________");
                        output.write(newline);
                        for (int i = 0; i < bon.size(); i++) {
                            String nume = bon.get(i).getNume();
                            double pret = bon.get(i).getPret();
                            String tip = bon.get(i).getTip();
                            int cantitate = bon.get(i).getCantitate();
                            String result = String.format("%.2f", pret);
                            output.write(nume);
                            output.write(newline);
                            output.write(cantitate + " x " + pret / cantitate);
                            output.write(newline);
                            output.write("Total lei: " + result);
                            output.write(newline);
                            output.write("Tip reducere: " + tip);
                            output.write(newline);
                            output.write(newline);
                            output.write("____________________________________________________________________________________");
                            output.write(newline);
                        }
                        output.write(newline);
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());
                        output.write(newline);
                        output.write(formatter.format(date));
                        output.write(newline);
                        output.close();
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "Bonul va fi printat in bon.txt");
                    new MyFrame(mod);
                    dispose();
                }
            }
        }
    }
}
