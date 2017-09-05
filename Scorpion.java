
public class Scorpion extends Enemy
{
    
    public Scorpion() {
         super("Scorpion", 50);
     }
            
            
    public int damage () {
        return enemyDmg(20, 5);
      }
      
}
