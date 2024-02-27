package ru.mipt.bit.platformer.Adapter;

import com.badlogic.gdx.Input;
import org.awesome.ai.state.GameState;
import org.awesome.ai.state.immovable.Obstacle;
import org.awesome.ai.state.movable.Actor;
import org.awesome.ai.state.movable.Bot;
import org.awesome.ai.state.movable.Orientation;
import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.Controllers.Direction;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.LevelShape;
import ru.mipt.bit.platformer.Entities.MovingEntity;
import ru.mipt.bit.platformer.Entities.TreeModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdapterGameState {
    private final List<Bot> bots;
    private final List<Obstacle> obstacles;
    private final Player player;
    private final int levelWidth;
    private final int levelHeight;
    private final List<GameEntity> entities;

    private final Map<Direction, Orientation> directionToOrientationMap;

    private final Map<Actor, GameEntity> bondActorEntity;


    public AdapterGameState(List<GameEntity> entities,
                            MovingEntity playerEntity,
                            LevelShape levelShape,
                            Map<Direction, Orientation> directionToOrientationMap) {
        this.bondActorEntity = new HashMap<>();
        this.directionToOrientationMap = directionToOrientationMap;

        this.entities = entities;
        this.bots = createBots(playerEntity);
        this.obstacles = createObstacles();
        this.player = createPlayer(playerEntity);
        this.levelWidth = levelShape.get_width();
        this.levelHeight = levelShape.get_height();
    }
    public GameState buildGameState() {
        return GameState.builder()
                .obstacles(obstacles)
                .bots(bots)
                .player(player)
                .levelHeight(levelHeight)
                .levelWidth(levelWidth)
                .build();
    }

    public Map<Actor, GameEntity> getActors(){
        return bondActorEntity;
    }

    private List<Bot> createBots(MovingEntity playerEntity) {
        return entities.stream()
                .filter((el) -> el instanceof MovingEntity && el != playerEntity)
                .map((el) -> (MovingEntity) playerEntity)
                .map(this::createBot)
                .collect(Collectors.toList());
    }

    private Player createPlayer(MovingEntity playerEntity) {
        Player player = Player.builder()
                .source(playerEntity)
                .x(playerEntity.getCoordinates().x)
                .y(playerEntity.getCoordinates().y)
                .destX(playerEntity.getDestinationCoordinates().x)
                .destY(playerEntity.getDestinationCoordinates().y)
                .orientation(directionToOrientationMap.get(playerEntity.getDirection()))
//                .orientation(directionToOrientationMap.get(Direction.UP))
                .build();
        bondActorEntity.put(player, playerEntity);
        return player;
    }

    private List<Obstacle> createObstacles() {
        return entities.stream()
                .filter((el) -> el instanceof TreeModel)
                .map((el) -> (TreeModel) el)
                .map(this::createObstacle)
                .collect(Collectors.toList());
    }

    private Obstacle createObstacle(TreeModel treeModel) {
        return new Obstacle(treeModel.getCoordinates().x, treeModel.getCoordinates().y);
    }

    private Bot createBot(MovingEntity entity) {
        Bot bot = Bot.builder()
                .source(entity)
                .x(entity.getCoordinates().x)
                .y(entity.getCoordinates().y)
                .destX(entity.getDestinationCoordinates().x)
                .destY(entity.getDestinationCoordinates().y)
                .orientation(directionToOrientationMap.get(entity.getDirection()))
                .build();
        bondActorEntity.put(bot, entity);
        return bot;
    }
}