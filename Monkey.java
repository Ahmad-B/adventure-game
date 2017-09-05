
public class Monkey extends Enemy
{
    private int hp;
    public Monkey() {
         super("Monkey", 40);
     }
            
            
    public int damage() {
        return enemyDmg(12, 1);
      }
}
