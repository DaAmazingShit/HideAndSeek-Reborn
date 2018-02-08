package pl.amazingshit.has.game;

import java.util.HashMap;

import org.bukkit.Material;

public enum BlockRepresenter {

	STONE(1),

	WOOD(2),

	DIRT(3),

	LOG(4),

	SAND(5),

	GRAVEL(6),

	WORKBENCH(7),

	BOOKSHELF(8),

	CHEST(9),

	JUKEBOX(0),

	LEAVES(10),

	NOTE_BLOCK(11),

	GLASS(12),

	ICE(13),

	GLOWSTONE(14),

	FURNACE(15);

	private int value;
	private static final HashMap<Integer, BlockRepresenter> lookup = new HashMap<Integer, BlockRepresenter>();

	private BlockRepresenter(int num) {
		this.value = num;
	}

	public int getValue() {
		return this.value;
	}

	public Material getMaterial() {
		return Material.getMaterial(this.name());
	}

	public static BlockRepresenter getBlockRepresenter(int i) {
		return lookup.get(i);
	}

	static {
		for (BlockRepresenter br: BlockRepresenter.values()) {
			lookup.put(br.getValue(), br);
		}
	}
}