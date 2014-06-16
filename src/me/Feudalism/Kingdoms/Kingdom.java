package me.Feudalism.Kingdoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Kingdom {
	
	Main plugin;
	
	private String kName;//kingdom name
	private Player ruler;//kingdom ruler
	private KingdomArea kArea;
	
	private Map<String, Boolean> occs = new HashMap<String, Boolean>();
	{
		occs.put("k1", plugin.getConfig().getBoolean("k1.occ"));
		occs.put("k2", plugin.getConfig().getBoolean("k2.occ"));
		occs.put("k3", plugin.getConfig().getBoolean("k3.occ"));
		occs.put("k4", plugin.getConfig().getBoolean("k4.occ"));
		occs.put("k5", plugin.getConfig().getBoolean("k5.occ"));
		occs.put("k6", plugin.getConfig().getBoolean("k6.occ"));
		occs.put("k7", plugin.getConfig().getBoolean("k7.occ"));
		occs.put("k8", plugin.getConfig().getBoolean("k8.occ"));
		occs.put("k9", plugin.getConfig().getBoolean("k9.occ"));
		occs.put("k10", plugin.getConfig().getBoolean("k10.occ"));
		occs.put("k11", plugin.getConfig().getBoolean("k11.occ"));
		occs.put("k12", plugin.getConfig().getBoolean("k12.occ"));
		occs.put("k13", plugin.getConfig().getBoolean("k13.occ"));
		occs.put("k14", plugin.getConfig().getBoolean("k14.occ"));
		occs.put("k15", plugin.getConfig().getBoolean("k15.occ"));
		occs.put("k16", plugin.getConfig().getBoolean("k16.occ"));
	}
	public Kingdom(Main instance){
		plugin = instance;
	}
	public Kingdom(String n, Player p){
		kName = n;
		ruler = p;
	}
	//checks if there are available kingdoms
	public boolean areAvailableKingdoms(){
		if(plugin.getConfig().getInt("unoccupiedKingdoms") != 0
				|| plugin.getConfig().get("unoccupiedKingdoms") == null)
			return true;
		return false;
	}
	//finds an available kingdom
	public String findAvailableKingdom(){
		String available = new String();
		
		for(Map.Entry<String, Boolean> kingdomMap : occs.entrySet()){
			if(kingdomMap.getValue() == false){
				available = kingdomMap.getKey();
			}
		}
		return available;
	}
	
	//creates a kingdom and saves properties to config
	public boolean createKingdom(String name, Player p, String kingdomKey){
		
		if(areAvailableKingdoms()){
			
			//Sets kingdom name value in config to name param
			String kingdomNameKey = kingdomKey + "." + "name";
			plugin.getConfig().set(kingdomNameKey, name);
			
			//Sets occupied boolean (occ) to true
			String kingdomOccKey = kingdomKey + "." + "occ";
			plugin.getConfig().set(kingdomOccKey, true);
			
			//Sets value of ruler in config to p
			String kingdomRulerKey = kingdomKey + "." + "ruler";
			plugin.getConfig().set(kingdomRulerKey, p);
			
			//Sets kingdomArea
			String kingdomAreaKey = kingdomKey + "." + "area";
			List<Double> coords = (List<Double>) plugin.getConfig().getDoubleList(kingdomAreaKey);
			//Change the world name to what's appropriate \/\/\/
			setkArea(new KingdomArea(Bukkit.getWorld("overworld"), coords.get(0), coords.get(1), 
					coords.get(2), coords.get(3), coords.get(4), coords.get(5)));
			
			new Kingdom(name, p);
			
			plugin.getConfig().set("unoccupiedKingdoms", plugin.getConfig().getInt("unoccupiedKingdoms")-1);
			plugin.saveConfig();
			
			return true;
		}
		return false;
	}
	
	
	//ADDING OR REMOVING PLAYERS
	//--------------------------
	//AND ADJUSTING RANKS
	
	//adds player to serf list
	public boolean addPlayerToKingdom(String kingdomKey, Player p){
		String serfsKey = kingdomKey + "." + "serfs";
		List<String> listOfSerfs = (List<String>) plugin.getConfig().getStringList(serfsKey);
		listOfSerfs.add(p.getName());
		plugin.getConfig().set(serfsKey, listOfSerfs);
		plugin.saveConfig();
		return true;
	}
	//removes player from serf list and adds to knight list
	public boolean changeToKnightFromSerf(String kingdomKey, Player p){
		
		String serfsKey = kingdomKey + "." + "serfs";
		List<String> listOfSerfs = plugin.getConfig().getStringList(serfsKey);
		for(String name : listOfSerfs){
			if(name.equals(p.getName())){
				listOfSerfs.remove(name);
			}
		}
		plugin.getConfig().set(serfsKey, listOfSerfs);
		
		String knightsKey = kingdomKey + "." + "knights";
		List<String> listOfKnights = plugin.getConfig().getStringList(knightsKey);
		listOfKnights.add(p.getName());
		plugin.getConfig().set(knightsKey, listOfKnights);
		plugin.saveConfig();
		
		return true;
	}
	
	//removes player from noble list and adds to knight list
	public boolean changeToKnightFromNoble(String kingdomKey, Player p){
			
		String noblesKey = kingdomKey + "." + "nobles";
		List<String> listOfNobles = plugin.getConfig().getStringList(noblesKey);
		for(String name : listOfNobles){
			if(name.equals(p.getName())){
				listOfNobles.remove(name);
			}
		}
		plugin.getConfig().set(noblesKey, listOfNobles);
			
		String knightsKey = kingdomKey + "." + "knights";
		List<String> listOfKnights = plugin.getConfig().getStringList(knightsKey);
		listOfKnights.add(p.getName());
		plugin.getConfig().set(knightsKey, listOfKnights);
		plugin.saveConfig();
			
		return true;
	}	
	
	//removes player from knight list and adds to noble list
	public boolean changeToNobleFromKnight(String kingdomKey, Player p){
			
		String knightsKey = kingdomKey + "." + "knights";
		List<String> listOfKnights = plugin.getConfig().getStringList(knightsKey);
		for(String name : listOfKnights){
			if(name.equals(p.getName())){
				listOfKnights.remove(name);
			}
		}
		plugin.getConfig().set(knightsKey, listOfKnights);
			
		String noblesKey = kingdomKey + "." + "knights";
		List<String> listOfNobles = plugin.getConfig().getStringList(noblesKey);
		listOfNobles.add(p.getName());
		plugin.getConfig().set(noblesKey, listOfNobles);
		plugin.saveConfig();
			
		return true;
	}
	
	//removes player from Ruler spot and adds to Noble list
	public boolean changeToNobleFromRuler(String kingdomKey, Player p){
		
		String rulerKey = kingdomKey + "." + "ruler";
		plugin.getConfig().set(rulerKey, "");
			
		String noblesKey = kingdomKey + "." + "knights";
		List<String> listOfNobles = plugin.getConfig().getStringList(noblesKey);
		listOfNobles.add(p.getName());
		plugin.getConfig().set(noblesKey, listOfNobles);
		plugin.saveConfig();
			
		return true;
	}
	
	//removes player from Noble list and adds to Ruler spot
	public boolean changeToRulerFromNoble(String kingdomKey, Player p){
		
		String noblesKey = kingdomKey + "." + "knights";
		List<String> listOfNobles = plugin.getConfig().getStringList(noblesKey);
		for(String name : listOfNobles){
			if(name.equals(p.getName())){
				listOfNobles.remove(name);
			}
		}
		plugin.getConfig().set(noblesKey, listOfNobles);
		
		String rulerKey = kingdomKey + "." + "ruler";
		plugin.getConfig().set(rulerKey, p.getName());
		plugin.saveConfig();

		return true;
	}
	
	//gets a list of players in kingdom
	@SuppressWarnings("deprecation")
	public List<Player> getKingdomPlayers(String kingdomKey){
		List<Player> players = new ArrayList<Player>();
		players.add(Bukkit.getPlayer(plugin.getConfig().getString("ruler")));
		for(String name : plugin.getConfig().getStringList("nobles")){
			Player p = Bukkit.getPlayer(name);
			players.add(p);
		}
		for(String name : plugin.getConfig().getStringList("knights")){
			Player p = Bukkit.getPlayer(name);
			players.add(p);
		}
		for(String name : plugin.getConfig().getStringList("serfs")){
			Player p = Bukkit.getPlayer(name);
			players.add(p);
		}
		return players;
	}
	//returns none if player doesn't belong to a kingdom, returns kingdom of player otherwise
	public String getKingdomOfPlayer(Player p){
		String kingdomOfPlayer = "none";
		for(Map.Entry<String, Boolean> kingdomMap : occs.entrySet()){
			String kingdomKey = kingdomMap.getKey();
			List<Player> players = getKingdomPlayers(kingdomKey);
				for(Player pl : players){
					if(p.equals(pl)){
						kingdomOfPlayer = kingdomKey;
					}
				}
		}
		return kingdomOfPlayer;
	}
	
	//returns whether or not player is in kingdom
	public boolean isInKingdomArea(Player p, String kingdomKey){
		String kingdomAreaKey = kingdomKey + "." + "area";
		List<Double> coords = (List<Double>) plugin.getConfig().getDoubleList(kingdomAreaKey);
		
		//coords.get(0) and (2) are the x values (0 being smaller) 
		//&& coords.get(1) and (3) are the z values (1 being smaller)
		//so I can do this:
		Location loc = p.getLocation();
		if(loc.getX() >= coords.get(0) && loc.getX() <= coords.get(2)
			&& loc.getZ() >= coords.get(1) && loc.getZ() <= coords.get(3))
			return true;
		return false;
	}
	
	//returns whether or not player is in a kingdom that they do not belong to
	public boolean isInOtherKingdom(Player p, String kingdomKeyOfPlayer){
		boolean bool = false;
		for(Map.Entry<String, Boolean> kingdomMap : occs.entrySet()){
			if(isInKingdomArea(p, kingdomMap.getKey()) && kingdomMap.getKey() != kingdomKeyOfPlayer){
				bool = true;
			}
		}
		return bool;
	}
	
	//returns whether or not player is in badlands by verifying whether or not they're in a kingdom
	public boolean isInBadlands(Player p){
		boolean bool = true;
		for(Map.Entry<String, Boolean> kingdomMap : occs.entrySet()){
			if(isInKingdomArea(p, kingdomMap.getKey())){
				bool = false;
			}
		}
		return bool;
	}
	
	
	//GETTERS AND SETTERS
	public String getName() {
		return kName;
	}
	public void setName(String kName) {
		this.kName = kName;
	}
	public Player getRuler() {
		return ruler;
	}
	public void setRuler(Player ruler) {
		this.ruler = ruler;
	}
	public KingdomArea getkArea() {
		return kArea;
	}
	public void setkArea(KingdomArea kArea) {
		this.kArea = kArea;
	}
}
