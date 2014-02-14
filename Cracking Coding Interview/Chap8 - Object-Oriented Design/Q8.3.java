import java.util.*;

class CD {
	// TODO
}

class PlayList {
	// TODO
}

class Music {
	String player;
	String name;
	int year;

	public Music(String player, String name, int year) {
		this.player = player;
		this.name = name;
		this.year = year;
	}

	public void play() {
		System.out.println("Playing " + name + " by " + player);
	}
}

class MusicalJukebox {
	LinkedList<Music> musicList;

	public MusicalJukebox() {
		musicList = new LinkedList<Music>();
	}

	public void play() {
		if(isListEmpty()) {
			System.out.println("Music list is empty");
			return;
		}
		Music current = musicList.poll();
		current.play();
	}

	public void addMusic(Music music) {
		musicList.add(music);
	}

	public void addMusic(Music music, int index) {
		musicList.add(index, music);
	}

	public void moveMusic(int from, int to) {
		Music music = musicList.remove(from);
		if(music != null)
			musicList.add(to, music);
	}

	public boolean isListEmpty() {
		return musicList.size() == 0;
	}
}

class Q8_3App {
	public static void main(String[] args) {

	}
}