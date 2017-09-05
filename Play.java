import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Play extends Frame implements ActionListener {
    private TextField dr, lm;
    private Button n, e, s, w,fiht, bag, save, load, use, exitbag;
    private ImageIcon map;
    private JLabel maplbl;
    private Frame viewBag;
    private Panel BagPanel;
    public Label uname, namelbl, racelbl, hplbl, killslbl;
    static List items = new List();
    private Label uhp, ukills;
    private ArrayList userinfo, getinfo;
    private int nc, ec, sc, wc;
    private int pos = 0, dir;
    private String compass, inp = "y";
    static Race userrace;
    public int currenthp, kills = 0, scount = 0, mcount;

    
   public static void main(String [] args) {
      String name, race, gender = "Male";
      //Asks the user for their name and race
      name = input("What is your name?");
      race = input("Hello there " + name + "." + " What is your prefered race?"
                                                + System.getProperty("line.separator")
                                                + "Mage, Warrior or Archer");
      if(race.equalsIgnoreCase("Mage") || race.equalsIgnoreCase("Warrior")
      || race.equalsIgnoreCase("Archer")){
          gender = input("Would you like to play as a male or female?");
            if(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
                print("You have chosen to be a " + gender + ".");}                
                else { print("Invalid gender"); System.exit(0);}}          
       else {print("Incorrect Race"); System.exit(0);}
      
      
      if(race.equalsIgnoreCase("Mage")) {
          userrace = new Mage(gender, name);
          print("You have selected a " + gender + " " + race + ": " + userrace.getInfo());
        }
      else if(race.equalsIgnoreCase("Warrior")) {
          userrace = new Warrior(gender, name);
          print("You have selected a " + gender + " " + race + ": " + userrace.getInfo());
        }
      else if(race.equalsIgnoreCase("Archer")) {
          userrace = new Archer(gender, name);
          print("You have selected a " + gender + " " + race + ": " + userrace.getInfo());
        }
      else{print("Incorrect selection");}
      
      
      Play g = new Play("Adventure Game");
      g.setSize(500,500);
      g.setVisible(true);
      
      g.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent ev) {
        System.exit(0);}
      });
      
      items.add("Health Potion");
    }
    
    //Main GUI for the game
  Play(String title) {
      super(title);
      setLayout(new GridLayout(2,2));
      
      
      map = new ImageIcon (getClass().getResource("map.png"));
      maplbl = new JLabel(map);
      add(maplbl);
      
      Panel move = new Panel();
      move.setLayout(new GridLayout(3,2));
      add(move);
      
      Panel extras = new Panel();
      extras.setLayout(new GridLayout(2,2));
      add(extras);
      
      Panel stats = new Panel();
      stats.setLayout(new GridLayout(3,2));
      add(stats);
      
      namelbl = new Label("Name: ");
      uname = new Label(userrace.getName());
      hplbl = new Label("HP: ");
      uhp = new Label("100");
      killslbl = new Label("Kills: ");
      ukills = new Label("0");
      stats.add(namelbl);
      stats.add(uname);
      stats.add(hplbl);
      stats.add(uhp);
      stats.add(killslbl);
      stats.add(ukills);
      
      n = new Button("North");
      e = new Button("East");
      s = new Button("South");
      w = new Button("West");
      fiht = new Button("Fight");
      bag = new Button("Bag");
      save = new Button("Save");
      load = new Button("Load");
 
      n.addActionListener(this);
      e.addActionListener(this);
      s.addActionListener(this);
      w.addActionListener(this);
      fiht.addActionListener(this);
      bag.addActionListener(this);
      save.addActionListener(this);
      load.addActionListener(this);
      
      

      move.add(n);
      move.add(e);
      move.add(s);
      move.add(w);
      extras.add(fiht);
      extras.add(bag);
      extras.add(save);
      extras.add(load);
      
      
      lm = new TextField("You moved: ");
      lm.setEditable(false);
      move.add(lm);
      
      dr = new TextField("");
      dr.setEditable(false);
      move.add(dr);
      
    }
  
    //Method that will handle what happens when each button is pressed
  public void actionPerformed(ActionEvent event){
      if(event.getSource() ==  n) {
          if(nc >= 5) {
           System.out.println("You can no longer move north, please move in another direction");
          }
          else{
              dr.setText("North");
              nc += 1;
              sc -= 1;
              UserStats();
            }
        }
      else if(event.getSource() == e) {
          if(ec >= 5) {
           System.out.println("You can no longer move east, please move in another direction");
          }
          else{
              dr.setText("East");
              ec += 1;
              wc -= 1;
              UserStats();
            }
        }
      else if(event.getSource() == s) {
          if(sc >= 5) {
           System.out.println("You can no longer move south, please move in another direction");
          }
          else{
              dr.setText("South");
              sc += 1;
              nc -= 1;
              UserStats();
            }
        }
      else if(event.getSource() == w) {
          if(wc >= 5) {
           System.out.println("You can no longer move west, please move in another direction");
          }
          else{
              dr.setText("West");
              wc += 1;
              ec -= 1;
              UserStats();
            }
        }
      else if(event.getSource() == fiht) {
          UserStats();
          findFight();
        }
      else if(event.getSource() == bag) {
          UserStats();
         //items.add("Health Potion");
          
         viewBag = new Frame();
         viewBag.setSize(200,200);
         viewBag.setVisible(true);
         
         BagPanel = new Panel();
         BagPanel.setLayout(new GridLayout(3,0));
         use = new Button("Use Item");
         exitbag = new Button("Exit Bag");
         
         use.addActionListener(this);
         exitbag.addActionListener(this);
         
         BagPanel.add(items);
         BagPanel.add(use);
         BagPanel.add(exitbag);
         
         viewBag.add(BagPanel);
         
         
        }
      else if(event.getSource() == save) {
          UserStats();
          ArrayList<String> userinfo = new ArrayList<String>();
          userinfo.add(userrace.getName());
          userinfo.add(String.valueOf(userrace.getHP()));
          userinfo.add(String.valueOf(userrace.getKills()));
          
          FileOutput(userinfo);
        }
      else if(event.getSource() == load) {
          try {
          FileInput();}
          catch (IOException ex) {}
        }
      else if(event.getSource() == use) {
          int item = items.getSelectedIndex();
          if(item == 0 ) {
           if(userrace.getHP() >= 100) {
             System.out.println("You are already at maximum hp!");
           }
           else {
              userrace.setHP(userrace.getHP() + 25);
              System.out.println("You used a health potion, healing 25 HP!");
            }
           }
          else if(item == 1 || item == 2) {
              System.out.println("I need to find somewhere to bury these");
            }
        }
      else if(event.getSource() == exitbag) {
          viewBag.dispose();
        }
  }
  
      //Method which will handle looking fights where the character is standing
  public void findFight() {
      Fight f; 
      if(ec > 0 && wc < 0 ) {
          pos = 2;
          print("You encounter a monkey!");
          new Fight(pos, userrace);
        }
      else if(wc > 0 && ec < 0 ) {
          pos = 4;
          print("You encounter a Scorpion!");
          new Fight(pos, userrace);
        }
      else{
          print("You find nothing to fight");
        }
    }
  
  //Methods which will add items to the bag depending on what the user has killed
 public void addMonkeyItem(int count)
 {
     if(count == 1){
     items.add("Monkey remains");
    }
    else {
      System.out.println("As you already have Monkey remains, you leave the remains");
    }
 }
  public void addScorpionItem(int count)
 {
     if(count == 1){
     items.add("Scorpion remains");
    }
    else {
      System.out.println("As you already have Scorpion remains, you leave the remains");
    }
 }
    
    //Method that will set the users stats
  public void SetStats(int hp, int ukills)
  {
      currenthp = hp;
      kills = ukills;
      userrace.setHP(currenthp);
      userrace.setKills(kills);
  }
  
  //Method will update the GUI labels of the users HP and kills
  public void UserStats()
  {
    uhp.setText(String.valueOf(userrace.getHP()));
    ukills.setText(String.valueOf(userrace.getKills()));
  }
  
  //Method that will print the final end game messages
  public void Gameover()
  {
      System.out.println("Player Info:");
      System.out.println("Name: " + userrace.getName());
      System.out.println("Kills: " + userrace.getKills());
      System.out.println("");
      System.out.println("***** CREDITS *****");
      System.out.println("Creator: Ahmad Beldo (130228947)");
  }
   
  //Method will print the current users name, hp and amount of kills to a file
  public void FileOutput(ArrayList info) {
        String filename = "Score";
        try {
            PrintWriter outputStream = null;
            outputStream = new PrintWriter(new FileWriter(filename + ".txt"));
            
            outputStream.println("Name: " + info.get(0));
            outputStream.println("HP: " + info.get(1));
            outputStream.println("Kills: " + info.get(2));
            
            outputStream.flush();
            outputStream.close();
            print("The results have successfully been saved to the text document called "
                    + filename);
        } catch (IOException ex) {
            print("Error - The results could not be written to the file.");
            ex.printStackTrace();
        }
        
    }
  
   //Method will read the previous users name, hp and amount of kills from a file
  public void FileInput() throws IOException {
        ArrayList<String> getinfo = new ArrayList<String>();
        String filename = "Score";
        String line = null;
        try {
            BufferedReader inStream = new BufferedReader(new FileReader(filename + ".txt"));
            while ((line = inStream.readLine()) != null) {
                String[] info= line.split(" ");
                getinfo.add(info[1]);
            }
            userrace.setName(getinfo.get(0));
            uname.setText(getinfo.get(0));
            userrace.setHP(Integer.parseInt(getinfo.get(1)));
            uhp.setText(getinfo.get(1));
            userrace.setKills(Integer.parseInt(getinfo.get(2)));
            ukills.setText(getinfo.get(2));
            
            inStream.close();
        } catch (IOException ex) {
            print("Error - The results could not be read from the file.");
            print("If this is the first time running, please play the game first!");
        }
    }
  
  //Method allow print and input to be used globally
  public static String input(String a)
  {
      return JOptionPane.showInputDialog(a);
  }
  public static void print(String a)
  {
     JOptionPane.showMessageDialog(null, a);
  }
}
