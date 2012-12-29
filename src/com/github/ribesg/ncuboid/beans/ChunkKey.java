package com.github.ribesg.ncuboid.beans;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Chunk;

public class ChunkKey {

    @Getter @Setter private int    x, z;
    @Getter @Setter private String world;

    public ChunkKey(String world, int x, int z) {
        this.x = x;
        this.z = z;
        this.world = world;
    }

    public ChunkKey(Chunk bukkitChunk) {
        this.x = bukkitChunk.getX();
        this.z = bukkitChunk.getZ();
        this.world = bukkitChunk.getWorld().getName();
    }
}
