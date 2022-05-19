import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Autentificare extends JFrame {

    JTextField tUser;

    JLabel lUser, lParola, lTip;

    JPasswordField tParola;

    JButton bLogin, bSingin;

    JComboBox cbTip;

    ButtonGroup g;

    JPanel p1, p2;

    Container cp;

    private String tip;

    ManagerCont mcont;

    Autentificare() {
        this.mcont = ManagerCont.getInstanta();
        tUser = new JTextField(15);
        tParola = new JPasswordField(15);
        bLogin = new JButton("Conecteaza-te");
        bSingin = new JButton("Creaza un cont nou");
        cbTip = new JComboBox(new String[] { "Administrator", "Personal" });
        lUser = new JLabel("Nume de utilizator: ");
        lParola = new JLabel("Parola: ");
        lTip = new JLabel("Tipul de cont: ");
        cp = getContentPane();
        p1 = new JPanel();
        p1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        AscultatorButton b = new AscultatorButton();
        bLogin.addActionListener(b);
        bSingin.addActionListener(b);
        cbTip.addActionListener(b);
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
        p1.setBorder(BorderFactory.createTitledBorder("Bine ai venit!"));
        p2 = new JPanel();
        c.gridx = 0;
        c.gridy = 0;
        p2.add(bSingin, c);
        c.gridx = 1;
        c.gridy = 0;
        p2.add(bLogin, c);
        cp.add(p1, BorderLayout.CENTER);
        cp.add(p2, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Map<String, String> getMap() {
        Map<String, String> users = new HashMap();
        for (int i = 0; i < mcont.fisiere().size(); i++) {
            users.put(mcont.getCont().get(i).getUser(), mcont.getCont().get(i).getParola());
        }
        return users;
    }

    class AscultatorButton implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == bLogin) {
                if (tUser.getText().equals("") || tParola.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Nu ati introdus numele sau parola");
                } else {
                    if (getMap().get(tUser.getText()) != null && getMap().get(tUser.getText()).equals(tParola.getText())) {
                        JOptionPane.showMessageDialog(null, "Autenficare reusita!");
                        new MyFrame(mcont.gaseste(tUser.getText()));
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Numele sau parola nu se potrivesc.Te rog incearca din nou!");
                        tUser.setText("");
                        tParola.setText("");
                    }
                }
            } else if (ev.getSource() == bSingin) {
                new ContNou(mcont);
            }
        }
    }

    public static void main(String[] args) {
        Autentificare a = new Autentificare();
    }
}
