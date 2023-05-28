package engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.hud.Button;
import engine.physics.Player;
import engine.view.Sprite;

public class AddAttackButton extends Button {

    Player player;

    public AddAttackButton(Player player, int x, int y, int width, int height) throws IOException{
        super(
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/coin.png")
                )
                
            ),
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/coin.png")
                )
                
            ), x, y, width, height
        );
        this.player = player;
    }

    @Override
    public void onClick() {
        int curr = (int)(player.getAttackMultiplicator()+1.5);
        if(curr <= 10 && player.getgems() >= 5) {
            player.setAttackMultiplicator(curr);
            player.addgems(-5);
        }
    }
}
