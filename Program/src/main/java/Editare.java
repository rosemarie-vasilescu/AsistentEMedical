
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Editare extends JFrame {
  private ManagerMedicament m;
    private String criteriu;
    private String[] nume;
    private int ok;
    JComboBox cbNume,cbActualizeaza;
    JLabel lMedicament;
    JTextField tModifica;
    JButton bActualizeaza,bBack;
    Container cp;
    JPanel p1,p2;
    String mod;
    Editare(ManagerMedicament m,String mod)
            
    {  
        this.ok = 0;
       this.m=m;
       this.mod=mod;
        cbActualizeaza=new JComboBox(new String[]{"Cantitate", "Pret"});
      nume = m.fisiere().toArray(new String[0]);
        cbNume=new JComboBox(nume);
        lMedicament=new JLabel("");
        lMedicament.setVisible(false);
    tModifica=new JTextField(20);
    bActualizeaza=new JButton("Actualizeaza");
    bBack=new JButton("Back");
    AscultatorButton b=new AscultatorButton();
    bActualizeaza.addActionListener(b);
    bBack.addActionListener(b);
     AscultatorLista al=new AscultatorLista();
        cbNume.addItemListener(al);
        cbNume.addActionListener(b);
        cbActualizeaza.addItemListener(al);
        cp=getContentPane();
        p1=new JPanel(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
  c.insets=new Insets(10,10,10,10);
  c.gridx=0;
  c.gridy=0;
        p1.add(cbNume,c);
  c.gridx=0;
  c.gridy=1;
        p1.add(lMedicament,c);
  c.gridx=1;
  c.gridy=0;
        p1.add(cbActualizeaza,c);
  c.gridx=2;
  c.gridy=0;
        p1.add(tModifica,c);
  c.gridx=3;
  c.gridy=0;
        p1.add(bActualizeaza,c);
  
    p1.setBorder(BorderFactory.createTitledBorder("Editare"));
    p2=new JPanel();
    p2.setLayout(new GridBagLayout());
    c.gridx=2;
    c.gridy=3;
    p2.add(bBack,c);
    // cp.setLayout(new FlowLayout());
     cp.add(p1,BorderLayout.NORTH);
     cp.add(p2,BorderLayout.SOUTH);
      
        
       
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
   
         private class AscultatorLista implements ItemListener{
       
        
        public void itemStateChanged(ItemEvent e){
          
               String optiune=new String((String)cbNume.getSelectedItem());
               String optiune2=new String((String)cbActualizeaza.getSelectedItem());
            
                
            }
          
        }
        class  AscultatorButton implements ActionListener{ 
             public void afiseaza(){
        for(int i=0;i<m.fisiere().size();i++){
        if(String.valueOf(cbNume.getSelectedItem()).equalsIgnoreCase(m.getMed().get(i).getNume()) || m.getMed().get(i).getNume().contains(String.valueOf(cbNume.getSelectedItem())))
        {   lMedicament.setVisible(true);
        lMedicament.setFont(new Font("Verdana",Font.PLAIN,12));
            lMedicament.setText(m.getMed().get(i).toString()+" \n");
            pack();
          
        }
        
     }
    }
        public boolean verifica(){
            boolean ok=true;
            double modifica;
            if(tModifica.getText().isEmpty()){
       JOptionPane.showMessageDialog(null,"Campul trebuie completat!","Error",JOptionPane.ERROR_MESSAGE);
       ok=false;
   }
    try{
modifica= Double.parseDouble(tModifica.getText());
if(modifica<0){
    JOptionPane.showMessageDialog(null,"Valoarea introdusa trebuie sa fie un numar pozitiv!","Error",JOptionPane.ERROR_MESSAGE);
    ok=false;
}
} catch (NumberFormatException e) { JOptionPane.showMessageDialog(null,"Valoarea introdusa trebuie sa fie un numar!","Error",JOptionPane.ERROR_MESSAGE);ok=false; }
   return ok;
    }
        
       public void alege(String nume,String caz,String criteriu){
            String optiune=caz.toLowerCase();
            String schimb=criteriu.trim();
    switch(optiune){
               case "cantitate" -> schimbaCantitate(nume,schimb);
               case "pret" -> schimbaPret(nume,schimb);
               
           }
       }
        
    
    public void schimbaPret(String nume,String pret)
    { int ok=0;
          for(int i=0;i<m.fisiere().size();i++){
        if(nume.equalsIgnoreCase(m.getMed().get(i).getNume()) )
        {
           m.getMed().get(i).setPret(String.valueOf(Integer.parseInt(pret)+0.1*Integer.parseInt(pret)));
           m.salveaza(m.getMed().get(i), nume);
           ok=1;
            JOptionPane.showMessageDialog(null, "Articolul a fost actualizat");
             System.out.println(m.getMed().get(i));
            
        }
    }
         
            if(ok==0)
        {
            JOptionPane.showMessageDialog(null, "Articolul nu exista");
        }
          
         
      
    }
     public void schimbaCantitate(String nume,String cantitate)
    { int ok=0;
          for(int i=0;i<m.fisiere().size();i++){
        if(nume.equalsIgnoreCase(m.getMed().get(i).getNume()) )
        {
            m.getMed().get(i).setCantitate(cantitate);
           m.salveaza(m.getMed().get(i), nume);
           ok=1;
           JOptionPane.showMessageDialog(null, "Articolul a fost actualizat");
             System.out.println(m.getMed().get(i));
        }
    }
            if(ok==0)
        {
            JOptionPane.showMessageDialog(null, "Articolul nu exista");
        }
         
      
    }
     
public void actionPerformed(ActionEvent ev){
     if(ev.getSource()==cbNume)
     {  afiseaza();}
     else if(ev.getSource()==bActualizeaza)
     {   if(verifica()==true){
         alege((String)cbNume.getSelectedItem(),(String)cbActualizeaza.getSelectedItem(),tModifica.getText());
        afiseaza();}
     tModifica.setText("");
      //   JOptionPane.showMessageDialog(null,"Articolul a fost actualizat cu succes!");
         //mf.setVisible(true);
         //dispose();
    
}
     else if(ev.getSource()==bBack)
     {
        new MyFrame(mod);
         dispose();
     }
        }
}
}

    
