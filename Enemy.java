import java.util.*;
/**
 *Ahmad Beldo - Mini
 */
public class Enemy
{
    private String name;
    public int HP, dmg;
    
    
    public Enemy(String ename, int ehp) {
        name = ename;
        HP = ehp;
    }
    
    public void setHP(int hploss) {
        HP = HP - hploss;
    }
    
    public int getHP() {
        return HP;
    }  
    
    public String getName() {
        return name;
    }
    
    public int enemyDmg(int max, int min) {
        Random rand = new Random();
        dmg = rand.nextInt(max - min + 1) + min;
        return dmg;
    }
    
}
