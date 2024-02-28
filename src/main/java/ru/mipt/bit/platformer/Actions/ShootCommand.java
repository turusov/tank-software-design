package ru.mipt.bit.platformer.Actions;

import ru.mipt.bit.platformer.Controllers.CollisionHandler;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.ShootableEntity;
import ru.mipt.bit.platformer.Observer.GraphicsObserver;

public class ShootCommand implements Command {
    private final GraphicsObserver graphicsObserver;
    private final CollisionHandler collisionHandler;

    public ShootCommand(GraphicsObserver graphicsObserver, CollisionHandler collisionHandler) {
        this.graphicsObserver = graphicsObserver;
        this.collisionHandler = collisionHandler;
    }

    @Override
    public void execute(GameEntity gameEntity) {
        /*
        Добавить новую команду (“стрелять”) – по аналогии с командой “двигаться”
        Команда должна применяться к игроку по кнопке “пробел” и к танкам-противникам случайным образом
         (т.е. там же где мы выбираем случайное направление движения для танка иногда нужно выбирать стрельбу)
         + по рекомендациям рекомендательного движка (там, где мы адаптер сделали)
         */
        ShootableEntity shootableEntity = (ShootableEntity) gameEntity;

    }
}
