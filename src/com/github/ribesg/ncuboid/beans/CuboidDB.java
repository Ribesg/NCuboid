package com.github.ribesg.ncuboid.beans;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.bukkit.Location;

import com.github.ribesg.ncuboid.NCuboid;

public class CuboidDB extends com.github.ribesg.ncore.nodes.cuboid.beans.CuboidDB {

    private static CuboidDB instance;

    public static CuboidDB getInstance() {
        return instance;
    }

    private final Map<String, GeneralCuboid>        byName;    // CuboidName ; Cuboid
    private final Map<String, Set<GeneralCuboid>>   byOwner;   // OwnerName ; Cuboids of this owner
    private final Map<String, GeneralCuboid>        tmpCuboids; // OwnerName ; Temporary Cuboid (Owner's Selection)
    private final Map<ChunkKey, Set<GeneralCuboid>> byChunks;  // Chunk ; Cuboids in this chunk

    public CuboidDB(final NCuboid instance) {
        byName = new HashMap<String, GeneralCuboid>();
        byOwner = new HashMap<String, Set<GeneralCuboid>>();
        tmpCuboids = new HashMap<String, GeneralCuboid>();
        byChunks = new HashMap<ChunkKey, Set<GeneralCuboid>>();
        CuboidDB.instance = this;
    }

    public void add(final GeneralCuboid cuboid) {
        addByName(cuboid);
        addByOwner(cuboid);
        addByChunks(cuboid);
    }

    public void addByName(final GeneralCuboid cuboid) {
        byName.put(cuboid.getCuboidName(), cuboid);
    }

    public void addByOwner(final GeneralCuboid cuboid) {
        if (byOwner.containsKey(cuboid.getOwnerName())) {
            byOwner.get(cuboid.getOwnerName()).add(cuboid);
        } else {
            final Set<GeneralCuboid> newSet = new HashSet<GeneralCuboid>();
            newSet.add(cuboid);
            byOwner.put(cuboid.getOwnerName(), newSet);
        }
    }

    public void addTmp(final GeneralCuboid cuboid) {
        tmpCuboids.put(cuboid.getOwnerName(), cuboid);
    }

    public void addByChunks(final GeneralCuboid cuboid) {
        for (final ChunkKey k : cuboid.getChunks()) {
            if (byChunks.containsKey(k)) {
                byChunks.get(k).add(cuboid);
            } else {
                final Set<GeneralCuboid> newSet = new HashSet<GeneralCuboid>();
                newSet.add(cuboid);
                byChunks.put(k, newSet);
            }
        }
    }

    public void del(final GeneralCuboid cuboid) {
        delByName(cuboid);
        delByOwner(cuboid);
        delByChunks(cuboid);
    }

    public void delByName(final GeneralCuboid cuboid) {
        if (byName.containsKey(cuboid.getCuboidName())) {
            byName.remove(cuboid.getCuboidName());
        }
    }

    public void delByOwner(final GeneralCuboid cuboid) {
        if (byOwner.containsKey(cuboid.getOwnerName())) {
            final Set<GeneralCuboid> set = byOwner.get(cuboid.getOwnerName());
            if (set.contains(cuboid)) {
                set.remove(cuboid);
            }
            if (set.isEmpty()) {
                byOwner.remove(cuboid.getOwnerName());
            }
        }
    }

    public void delByChunks(final GeneralCuboid cuboid) {
        for (final ChunkKey k : cuboid.getChunks()) {
            if (byChunks.containsKey(k)) {
                final Set<GeneralCuboid> set = byChunks.get(k);
                if (set.contains(cuboid)) {
                    set.remove(cuboid);
                }
                if (set.isEmpty()) {
                    byChunks.remove(k);
                }
            }
        }
    }

    public void delTmp(final GeneralCuboid cuboid) {
        if (tmpCuboids.containsKey(cuboid.getOwnerName())) {
            tmpCuboids.remove(cuboid.getOwnerName());
        }
    }

    public GeneralCuboid get(final Location loc) {
        final ChunkKey k = new ChunkKey(loc);
        if (!byChunks.containsKey(k)) {
            return null;
        } else {
            final Set<GeneralCuboid> cuboids = byChunks.get(k);
            if (cuboids.size() > 0) {
                final int maxPriority = 0;
                final TreeMap<Long, GeneralCuboid> sizeMap = new TreeMap<Long, GeneralCuboid>(); // TotalSize ; Cuboid
                for (final GeneralCuboid c : cuboids) {
                    if (c.getPriority() > maxPriority) {
                        sizeMap.clear();
                        sizeMap.put(c.getTotalSize(), c);
                    } else if (c.getPriority() == maxPriority) {
                        sizeMap.put(c.getTotalSize(), c);
                    }
                }
                return sizeMap.get(sizeMap.firstKey());
            } else {
                return null;
            }
        }
    }
}
