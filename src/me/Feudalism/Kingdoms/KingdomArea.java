package me.Feudalism.Kingdoms;

import org.bukkit.Location;
import org.bukkit.World;

public class KingdomArea {
	//makes a "cube" of which's corners are the 8 locations here
	
	//locations at highest point of map
	private Location topLeftCornerHigh;
	private Location topRightCornerHigh;
	private Location bottomLeftCornerHigh;
	private Location bottomRightCornerHigh;
	
	private Location topLeftCornerLow;
	private Location topRightCornerLow;
	private Location bottomLeftCornerLow;
	private Location bottomRightCornerLow;
	
	Main plugin;
	public KingdomArea(Main instance){
		plugin = instance;
	}
	
	public KingdomArea(World world, double tlX, double trZ, double brX, double blZ, double low, double high){
		setTopLeftCornerHigh(new Location(world, tlX, high, trZ));
		setTopRightCornerHigh(new Location(world, brX, high, trZ));
		setBottomRightCornerHigh(new Location(world, brX, high, blZ));
		setBottomLeftCornerHigh(new Location(world, tlX, high, blZ));
		
		setTopLeftCornerLow(new Location(world, tlX, low, trZ));
		setTopRightCornerLow(new Location(world, brX, low, trZ));
		setBottomRightCornerLow(new Location(world, brX, low, blZ));
		setBottomLeftCornerLow(new Location(world, tlX, low, blZ));
	}
	
	
	//GETTERS AND SETTERS FOR ALL 8 LOCATIONS
	public Location getTopLeftCornerHigh() {
		return topLeftCornerHigh;
	}

	public void setTopLeftCornerHigh(Location topLeftCornerHigh) {
		this.topLeftCornerHigh = topLeftCornerHigh;
	}

	public Location getTopRightCornerHigh() {
		return topRightCornerHigh;
	}

	public void setTopRightCornerHigh(Location topRightCornerHigh) {
		this.topRightCornerHigh = topRightCornerHigh;
	}

	public Location getBottomLeftCornerHigh() {
		return bottomLeftCornerHigh;
	}

	public void setBottomLeftCornerHigh(Location bottomLeftCornerHigh) {
		this.bottomLeftCornerHigh = bottomLeftCornerHigh;
	}

	public Location getBottomRightCornerHigh() {
		return bottomRightCornerHigh;
	}

	public void setBottomRightCornerHigh(Location bottomRightCornerHigh) {
		this.bottomRightCornerHigh = bottomRightCornerHigh;
	}

	public Location getTopLeftCornerLow() {
		return topLeftCornerLow;
	}

	public void setTopLeftCornerLow(Location topLeftCornerLow) {
		this.topLeftCornerLow = topLeftCornerLow;
	}

	public Location getTopRightCornerLow() {
		return topRightCornerLow;
	}

	public void setTopRightCornerLow(Location topRightCornerLow) {
		this.topRightCornerLow = topRightCornerLow;
	}

	public Location getBottomLeftCornerLow() {
		return bottomLeftCornerLow;
	}

	public void setBottomLeftCornerLow(Location bottomLeftCornerLow) {
		this.bottomLeftCornerLow = bottomLeftCornerLow;
	}

	public Location getBottomRightCornerLow() {
		return bottomRightCornerLow;
	}

	public void setBottomRightCornerLow(Location bottomRightCornerLow) {
		this.bottomRightCornerLow = bottomRightCornerLow;
	}

}