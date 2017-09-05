import java.util.Random;
/**
 *Ahmad Beldo - Mini Project
 */
public class Race
{
    public int hp, mana, energy, kills, mcount, scount; String gender, info, name;
    
    public Race(String nam, String sex, int uhp, double mana, String inf) {
        name = nam;
        gender = sex;
        hp = uhp;
        info =inf;
    }
    
    public String getName()
    {
        return name;
    }
    public void setName(String nme)
    {
         name = nme;
    }      
    public int getHP()
    {
        return hp;
    }
    public void setHP(int health)
    {
        hp = health;
    }    
    public double hploss(int loss)
    {
        return (hp - loss);
    }
    public int dmgdone()
    {
        Random rand = new Random();
        return rand.nextInt((20 - 0) + 1) + 0;
    }
    public String getInfo()
    {
        return info;
    }
    public int getKills()
    {
        return kills;
    }
    public void setKills(int ukills)
    {
        kills = ukills;
    }
    
    public int getmCount()
    {
        return mcount;
    }
    public void setmCount(int val)
    {
        mcount = mcount + val;
    }
    public int getsCount()
    {
        return scount;
    }
    public void setsCount(int val)
    {
        scount = scount + val;
    }   
}
