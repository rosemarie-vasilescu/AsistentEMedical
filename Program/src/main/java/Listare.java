
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList; 
import java.util.Collections;

public class Listare extends JFrame {
    JPanel p1;
    JButton bback;
    Container cp;
    JLabel[] lLista;
    String[] criteriu;
    private ManagerMedicament m;
   private  ArrayList<Medicament> medicament;
String mod;
    Listare(String optiune,ManagerMedicament m,String mod)
    {  super("Afisare stoc");
        this.medicament = new ArrayList<>();
        this.m=m;
        this.mod=mod;
        bback=new JButton("Back");
       
        bback.setSize(40, 40);
     AscultatorB ab=new AscultatorB();
     bback.addActionListener(ab);
     JScrollPane pane = new JScrollPane();
     pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
       pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     p1=new JPanel();
        p1.setLayout(new GridLayout(m.fisiere().size()+1,1));
        cp = getContentPane();
        String caz = optiune.toLowerCase();
        
      for (int i=0;i<m.fisiere().size();i++)
    {
        medicament.add(m.citeste(m.fisiere().get(i)));
    }
      switch(caz){
               case "nume" -> Collections.sort(medicament,Medicament.SortbyName);
               case "indicatii" -> Collections.sort(medicament,Medicament.SortbyDisease);
               case "pret" -> Collections.sort(medicament,Medicament.SortbyPrice);
           }
       
    

    
   
       lLista=new JLabel[m.fisiere().size()];
   
        for (int i=0;i<m.fisiere().size();i++){
//System.out.println(med.toString());
lLista[i]=new JLabel(medicament.get(i).toString()+"\n\r");
lLista[i].setFont(new Font("Verdana",Font.PLAIN,12));
lLista[i].setBorder(BorderFactory.createEtchedBorder());

p1.add(lLista[i]);
p1.add(bback);
pane.setViewportView(p1);


cp.add(pane,BorderLayout.CENTER);

}

        
       
 
          pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    class  AscultatorB implements ActionListener{ 
public void actionPerformed(ActionEvent ev){
    if(ev.getSource()==bback){
        new MyFrame(mod);
        dispose();
    }
}
}
}
