package net.landgrafhomyak.BronzaW_Lobby;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class LobbyDimension extends WorldCreator {
    public LobbyDimension() {
        super("lobby");
        environment(World.Environment.NORMAL);
        type(WorldType.FLAT);
        seed(0);
        hardcore(false);
        generateStructures(false);
        generator(new VoidGenerator());

    }
}
