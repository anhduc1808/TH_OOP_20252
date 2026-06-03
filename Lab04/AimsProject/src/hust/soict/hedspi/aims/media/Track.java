package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable {
	
	private String title;
	private int length;
	
	public String getTitle() {
		return title;
	}

	public int getLength() {
		return length;
	}

	public Track(String title, int length) {
		this.title = title;
		this.length = length;
	}
	
	public void play() {
		System.out.println("Playing track: " + this.getTitle());
		System.out.println("Track length: " + this.getLength());
	}

	public void playGUI() throws PlayerException {
		if (this.getLength() <= 0) {
			throw new PlayerException("Track " + this.getTitle() + " has invalid length!");
		}
		play();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if(!(obj instanceof Track)) {
			return false;
		}
		return ((Track)obj).getTitle() == this.getTitle() && ((Track)obj).getLength() == this.getLength();
 	}
}
