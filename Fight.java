import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Fight extends Frame implements ActionListener {
    private Button a,r;
    private ImageIcon enemypic;
    private JLabel lbl;
    private Label enemyname, basehp;
    Frame fight; 
    int position, edmg, udmg;
    Race userrace;
      
    Enemy enemy1 = new Monkey();
    Enemy enemy2 = new Scorpion();
    Play gameplay = new Play(null);
    
    int e1currenthp = enemy1.getHP();
    int e2currenthp = enemy2.getHP();
    int userhp, kills;

 //Method will create a GUI for the fights
 public Fight(int e, Race u) {
  position = e;
  userrace = u;
  
  fight = new Frame();
  Panel Fight = new Panel();
  Panel info = new Panel();
  
  fight.setTitle("Battle!");
  fight.setSize(300,400);
  fight.setVisible(true);
  
  fight.setLayout(new GridLayout(2,2));
  
  //Depending on where the user is, the picture of the enemy is displayed on the GUI
  if(position == 2) {
      enemypic = new ImageIcon (getClass().getResource("monkey.png"));
 
      enemyname = new Label("Name: " + enemy1.getName());
      basehp = new Label("Base HP: " + String.valueOf(enemy1.getHP()));
  }
  else if(position == 4) {
      enemypic = new ImageIcon (getClass().getResource("scorpion.png"));
 
      enemyname = new Label("Name: " + enemy2.getName());
      basehp = new Label("Base HP: " + String.valueOf(enemy2.getHP()));
    }

  lbl = new JLabel (enemypic);
  
  a = new Button("Attack");
  r = new Button("Run");
      
  a.addActionListener(this);
  r.addActionListener(this);
  
  info.add(enemyname);
  info.add(basehp);

  fight.add(lbl);
  fight.add(info);
  fight.add(a);
  fight.add(r);
  
  
  
  fight.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent ev) {
      System.out.println("You sucessfully run away");
      fight.dispose();}
  });
      
    }
 
  //Method will handle the fighting events selected by the user  
 public void actionPerformed(ActionEvent event){

      if(event.getSource() ==  a) {
          if(position == 2) {
          udmg = userrace.dmgdone();
          System.out.println("You attack the " + enemy1.getName() + ", dealing " + udmg + " damage!");
          e1currenthp = e1currenthp - udmg;
          System.out.println(enemy1.getName() +"'s HP: " + e1currenthp);
          edmg = ((Monkey)enemy1).damage();
            if(e1currenthp > 0) {
                System.out.println(enemy1.getName() + " attacks you, it deals " + edmg + " damage!");
                userhp = userrace.getHP() - edmg;
                userrace.setHP(userhp);
                System.out.println(userrace.getName()+ "'s HP: " + userhp);
                System.out.println("");
            }
        if(e1currenthp <= 0 ) {
             System.out.println("You killed the monkey!");
             System.out.println("You find some monkey remains!");
             System.out.println("");
             gameplay.SetStats(userhp, (userrace.getKills()+1));
             userrace.setmCount(1);
             gameplay.addMonkeyItem(userrace.getmCount());
             fight.dispose();
            }
          else if(userhp <= 0) {
              System.out.println("You have died!"); 
              System.exit(0);
            }
        }
        else if(position == 4) {
          udmg = userrace.dmgdone();
          System.out.println("You attack the " + enemy2.getName() + ", dealing " + udmg + " damage!");
          e2currenthp = e2currenthp - udmg;
          System.out.println(enemy2.getName() +"'s HP: " + e2currenthp);
          edmg = ((Scorpion)enemy2).damage();
            if(e2currenthp > 0) {
                System.out.println(enemy2.getName() + " attacks you, it deals " + edmg + " damage!");
                userhp = userrace.getHP() - edmg;
                userrace.setHP(userhp);
                System.out.println(userrace.getName()+ "'s HP: " + userhp);
                System.out.println("");
            }
          if(e2currenthp <= 0 ) {
             System.out.println("You killed the Scorpion!");
             System.out.println("You find some scorpion remains!");
             System.out.println("");
             gameplay.SetStats(userhp, (userrace.getKills()+1));
             userrace.setsCount(1);
             gameplay.addScorpionItem(userrace.getsCount());
             fight.dispose();
            }
          else if(userhp <= 0) {
              System.out.println("You have died!"); 
              System.out.println("***** GAME OVER *****");
              gameplay.Gameover();
              System.exit(0);
            }
        }
    }
    else if(event.getSource() == r) {
     System.out.println("You sucessfully run away");
     fight.dispose();
        }     
     }
   }


