package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Entities.Tank;
import ru.mipt.bit.platformer.Entities.Tree;

public class CollisionDetector {
    public static boolean ifCollides(Tank tank, Tree tree, Direction direction){
        GridPoint2 tankTargetCoordinates = direction.add(tank.getCoordinates());
        if(collides(tree.getCoordinates(), tankTargetCoordinates)){
            return true;
        }
        return false;
    }
    private static boolean collides(GridPoint2 object1, GridPoint2 object2) {
        return object1.equals(object2);
    }
}
