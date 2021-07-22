package net.landgrafhomyak.BronzaW_Lobby;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import java.util.Random;

public class VoidGenerator extends ChunkGenerator {
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        chunk.setRegion(0, 0, 0, 16, 256, 16, Material.AIR);
//        if (chunkX == 0 && chunkZ == 0) {
//            chunk.setRegion(0, 0, 0, 1, 256, 1, Material.BEDROCK);
//        }
        return chunk;
    }
}