package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

/** An implementation of a motile attacker.
 *  @FreecssX
 */
public class Clorus extends Creature {
    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;

    /** creates clorus with energy equal to E. */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /** creates a clorus with energy equal to 1. */
    public Clorus() {
        this(1);
    }

    /** Should return a color with red = 34, green =0, blue = 231. */
    public Color color() {
        g = 34;
        r = 0;
        b = 231;
        return color(r, g, b);
    }

    /** Attack c. Get the energy of c and then c dies. */
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    /** Cloruss should lose 0.03 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    public void move() {
        this.energy = this.energy - 0.03;
    }


    /** Cloruss loses 0.01 energy when staying. */
    public void stay() {
        this.energy = this.energy - 0.01;
    }

    /** Cloruss and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Clorus.
     */
    public Clorus replicate() {
        double halfEnergy = (this.energy / 2);
        this.energy = halfEnergy;
        Clorus babyClorus = new Clorus(halfEnergy);
        return babyClorus;
    }

    /** Cloruss take exactly the following actions based on NEIGHBORS:
     * If there are no empty squares the Clorus will STAY (even if 
     * there are Plips nearby they could attack).
     * Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * Otherwise, if the Clorus has energy greater than or equal to one, 
     * it will REPLICATE to a random empty square.
     * Otherwise, the Clorus will MOVE.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if(empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if(plips.size() >= 1) {
            Direction moveDir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, moveDir);
        } else if(this.energy >= 1.0) {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        } else {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.MOVE, moveDir);
        }
    }
}
