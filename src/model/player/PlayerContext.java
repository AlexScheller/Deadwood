package model.player;

/*
 * This class represents a player's state. Rather than
 * writing a whole bunch of methods for extracting
 * individual bits of information from/around the model
 * about players, a single block of information is passed,
 * and the requesting method/object extracts from it
 * whatever information is relevant at the time.
 */

// TODO: See about converting this from a simple struct
// to a dynamic source of information. Currently there
// are many times when this class will only be semi-filled
// before being passed, and nowhere is the entire class
// referenced at once.

public class PlayerContext {
	public boolean acting;
	public boolean canRehearse;
	public boolean canUpgrade;
	public String name;
	public int id;
	public int dollars;
	public int credits;
	public int rank;
	public int rehearsalTokens;
}