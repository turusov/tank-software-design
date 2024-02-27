package ru.mipt.bit.platformer.Adapter;

import com.badlogic.gdx.Input;
import org.awesome.ai.AI;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import org.awesome.ai.state.movable.Actor;
import org.awesome.ai.state.movable.Orientation;
import org.awesome.ai.strategy.NotRecommendingAI;
import org.mockito.internal.matchers.Or;
import ru.mipt.bit.platformer.Actions.Action;
import ru.mipt.bit.platformer.Controllers.CollisionHandler;
import ru.mipt.bit.platformer.Controllers.Controller;
import ru.mipt.bit.platformer.Controllers.Direction;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.LevelShape;
import ru.mipt.bit.platformer.Entities.MovingEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AIAdapterController implements Controller {
    private AdapterGameState adapterGameState;
    private final AI recommendingAI;
    private Map<org.awesome.ai.Action, Action> actionMap;

    public AIAdapterController(List<GameEntity> entities, MovingEntity playerEntity, LevelShape levelShape, CollisionHandler collisionHandler) {
        this.recommendingAI = new NotRecommendingAI();
        this.adapterGameState = new AdapterGameState(entities, playerEntity, levelShape, createMapper());
        this.actionMap = new HashMap<>();
    }

    private Map<Direction, Orientation> createMapper(){
        Map<Direction, Orientation> directionOrientationMap = new HashMap<>();
        directionOrientationMap.put(Direction.UP, Orientation.N);
        directionOrientationMap.put(Direction.DOWN, Orientation.S);
        directionOrientationMap.put(Direction.LEFT, Orientation.W);
        directionOrientationMap.put(Direction.RIGHT, Orientation.E);
        return directionOrientationMap;
    }
    @Override
    public void execute() {
        GameState gameState = adapterGameState.buildGameState();
        Map<Actor, GameEntity> actorGameEntityMap = adapterGameState.getActors();
        for(Recommendation recommendation : recommendingAI.recommend(gameState)){
            if(recommendation.getActor() != gameState.getPlayer()){
                GameEntity gameEntity = actorGameEntityMap.get(recommendation.getActor());
                Action action = actionMap.get(recommendation.getAction());
                action.apply(gameEntity);
            }
        }
    }
}
