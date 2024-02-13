package ru.mipt.bit.platformer.Controllers;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.MovingEntity;

public class CollisionDetector {
    public static boolean ifCollides(GameEntity object1, MovingEntity object2, Direction direction){
        if(direction!=null && !object2.isMoving()) {
            GridPoint2 targetCoordinates = direction.add(object2.getCoordinates());
            object2.rotate(direction);
            if (collides(object1.getCoordinates(), targetCoordinates)) {
                return true;
            }
            return false;
        }
        return false;
    }
    private static boolean collides(GridPoint2 object1, GridPoint2 object2) {
        return object1.equals(object2);
    }
}
