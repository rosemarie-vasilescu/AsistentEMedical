import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ContNou extends JFrame {

    JTextField tUser, tParola;

    JComboBox cbTip;

    JLabel lUser, lParola, lTip;

    JButton bAdauga;

    JPanel p1, p2;

    Container cp;

    ManagerCont mcont;

    ArrayList<Cont> co;

    ContNou(ManagerCont mcont) {
        tUser = new JTextField(15);
        tParola = new JTextField(15);
        bAdauga = new JButton("Adauga");
        lUser = new JLabel("Nume de utilizator: ");
        lParola = new JLabel("Parola: ");
        lTip = new JLabel("Tipul de utilizator: ");
        cbTip = new JComboBox(new String[] { "Administrator", "Personal" });
        AscultatorButton b = new AscultatorButton();
        bAdauga.addActionListener(b);
        cbTip.addActionListener(b);
        cp = getContentPane();
        this.mcont = mcont;
        co = new ArrayList<Cont>();
        p1 = new JPanel();
        p1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        p1.add(lUser, c);
        c.gridx = 1;
        c.gridy = 0;
        p1.add(tUser, c);
        c.gridx = 0;
        c.gridy = 1;
        p1.add(lParola, c);
        c.gridx = 1;
        c.gridy = 1;
        p1.add(tParola, c);
        c.gridx = 0;
        c.gridy = 2;
        p1.add(lTip, c);
        c.gridx = 1;
        c.gridy = 2;
        p1.add(cbTip, c);
        p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        p2.add(bAdauga, c);
        cp.add(p1, BorderLayout.CENTER);
        cp.add(p2, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean verifica() throws IOException {
        boolean ok = true;
        if (tUser.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campul nume trebuie completat!", "Error", JOptionPane.ERROR_MESSAGE);
            ok = false;
        } else if (tParola.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campul nume trebuie completat!", "Error", JOptionPane.ERROR_MESSAGE);
            ok = false;
        }
        return ok;
    }

    class AscultatorButton implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == bAdauga) {
                try {
                    if (verifica() == true) {
                        mcont.salveazaCont(new Cont(tUser.getText(), tParola.getText(), (String) cbTip.getSelectedItem()), tUser.getText());
                        System.out.println(mcont.toString());
                        new Autentificare();
                        dispose();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
