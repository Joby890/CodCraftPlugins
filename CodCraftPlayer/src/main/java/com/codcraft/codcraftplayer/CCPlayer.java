package com.codcraft.codcraftplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;


public class CCPlayer {
	
	/**
	 * Integer for amount of CaC a player can have
	 */
	protected Integer CaCint;
	/**
	 * List of current Classes
	 */
	protected Map<Integer, CCClass> classes = new HashMap<Integer, CCClass>();
	
	
	public Integer mysqlid;
	
	protected Integer CCPoints;
	
	protected Integer CCLevel;
	
	protected Integer Kills;
	
	protected Integer Deaths;
	
	protected Integer TDMKills = 0;
	
	protected Integer TDMDeaths = 0;
	
	protected Integer TDMWins = 0;
	
	protected Integer TDMLosses = 0;
	
	protected Integer FFAKills = 0;
	
	protected Integer FFADeaths = 0;
	
	protected Integer FFAWins = 0;
	
	protected Integer FFALosses = 0;
	
	protected Integer SSBKills = 0;
	
	protected Integer SSBDeaths = 0;
	
	protected Integer SSBWins = 0;
	
	protected Integer SSBLosses = 0;
	
	protected Integer UHCKills = 0;
	
	protected Integer UHCDeaths = 0;
	
	protected Integer UHCWins = 0;
	
	protected Integer UHCLosses = 0;
	
	protected Integer Wins;
	
	protected Integer Losses;

	protected Integer currentclass = 1;
	
	public Map<Integer, CCClass> getMapClasses() {
		return ImmutableMap.copyOf(classes);
	}
	public List<CCClass> getAllClasses() {
		ArrayList<CCClass> classesl = new ArrayList<>();
		for(Entry<Integer, CCClass> s : classes.entrySet()) {
			classesl.add(s.getValue());
		}
		return classesl;
	}
	
	public Integer getCurrentclass() {
		return currentclass;
	}
	public void setCurrentclass(Integer currentclass) {
		this.currentclass = currentclass;
	}
	public Integer getLosses() {
		return Losses;
	}
	public void setLosses(Integer losses) {
		Losses = losses;
	}
	public Integer getDeaths() {
		return Deaths;
	}
	public void setDeaths(Integer deaths) {
		Deaths = deaths;
	}
	public Integer getWins() {
		return Wins;
	}
	public void setWins(Integer wins) {
		Wins = wins;
	}
	public void setPoints(Integer points) {
		CCPoints = points;
	}
	public Integer getPoints() {
		return CCPoints;
	}
	
	public void setLevel(Integer Level) {
		CCLevel = Level;
	}
	public Integer getKills() {
		return Kills;
	}
	public void setKills(Integer kills) {
		Kills = kills;
	}
	public Integer getTDMKills() {
		return TDMKills;
	}
	public void setTDMKills(Integer tDMKills) {
		TDMKills = tDMKills;
	}
	public Integer getTDMDeaths() {
		return TDMDeaths;
	}
	public void setTDMDeaths(Integer tDMDeaths) {
		TDMDeaths = tDMDeaths;
	}
	public Integer getTDMWins() {
		return TDMWins;
	}
	public void setTDMWins(Integer tDMWins) {
		TDMWins = tDMWins;
	}
	public Integer getTDMLosses() {
		return TDMLosses;
	}
	public void setTDMLosses(Integer tDMLosses) {
		TDMLosses = tDMLosses;
	}
	public Integer getFFAKills() {
		return FFAKills;
	}
	public Integer getFFADeaths() {
		return FFADeaths;
	}
	public Integer getFFAWins() {
		return FFAWins;
	}
	public Integer getFFALosses() {
		return FFALosses;
	}
	public void setFFALosses(Integer fFALosses) {
		FFALosses = fFALosses;
	}
	public void setFFAWins(Integer fFAWins) {
		FFAWins = fFAWins;
	}
	public void setFFADeaths(Integer fFADeaths) {
		FFADeaths = fFADeaths;
	}
	public void setFFAKills(Integer fFAKills) {
		FFAKills = fFAKills;
	}
	public Integer getSSBKills() {
		return SSBKills;
	}
	public void setSSBKills(Integer sSBKills) {
		SSBKills = sSBKills;
	}
	public Integer getSSBDeaths() {
		return SSBDeaths;
	}
	public void setSSBDeaths(Integer sSBDeaths) {
		SSBDeaths = sSBDeaths;
	}
	public Integer getSSBWins() {
		return SSBWins;
	}
	public void setSSBWins(Integer sSBWins) {
		SSBWins = sSBWins;
	}
	public Integer getSSBLosses() {
		return SSBLosses;
	}
	public void setSSBLosses(Integer sSBLosses) {
		SSBLosses = sSBLosses;
	}
	public Integer getUHCKills() {
		return UHCKills;
	}
	public void setUHCKills(Integer uHCKills) {
		UHCKills = uHCKills;
	}
	public Integer getUHCDeaths() {
		return UHCDeaths;
	}
	public void setUHCDeaths(Integer uHCDeaths) {
		UHCDeaths = uHCDeaths;
	}
	public Integer getUHCWins() {
		return UHCWins;
	}
	public void setUHCWins(Integer uHCWins) {
		UHCWins = uHCWins;
	}
	public Integer getUHCLosses() {
		return UHCLosses;
	}
	public void setUHCLosses(Integer uHCLosses) {
		UHCLosses = uHCLosses;
	}
	public Integer getLevel() {
		return CCLevel;
	}
	
	
	public boolean setClass(CCClass clazz, int classspot) {
		if(classspot > CaCint) {
			return false;
		}
		if(classes.containsKey(classspot)) {
			return false;
		} else {
			classes.put(classspot, clazz);
			return true;	
		}

	}
	
	
	public CCClass getClass(int classnumber) {
		if(classnumber > CaCint) {
			return null;
		}
		if(classes.get(classnumber) == null) {
			CCClass newClass = new CCClass();
			//TODO set the dedaults
			classes.put(classnumber, newClass);
			return classes.get(classnumber);
		} else {
			return classes.get(classnumber);
		}
	}
	public Integer getCaCint() {
		return CaCint;
	}
	public void setCaCint(Integer caCint) {
		CaCint = caCint;
	}

	
	

}