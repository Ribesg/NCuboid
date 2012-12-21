package com.github.ribesg.ncuboid.beans;

import java.util.EnumMap;

import lombok.Getter;
import lombok.Setter;

import com.github.ribesg.ncore.nodes.cuboid.Cuboid;
import com.github.ribesg.ncore.nodes.cuboid.Flag;

public class CuboidImpl implements Cuboid {

	public static enum CuboidState {
		NORMAL, TMPSTATE1, TMPSTATE2
	}
	
	@Getter @Setter private String name;
	@Getter @Setter private String ownerName;

	private EnumMap<Flag, Boolean> flags;

	public CuboidImpl() {
		this.flags = Flag.getDefaultFlagMap();
	}

	public CuboidImpl(EnumMap<Flag, Boolean> flags) {
		this.flags = flags;
	}
}
