package utility;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class ResourceLoader {

	public static Image loadImage(String imagePath, double width, double height) {
		String fullPath = ClassLoader.getSystemResource("images/" + imagePath).toString();
		return new Image(fullPath, width, height, false, false);
	}

	public static AudioClip loadAudioClip(String audioClipPath) {
		String fullPath = ClassLoader.getSystemResource("audioclips/" + audioClipPath).toString();
		return new AudioClip(fullPath);
	}

}
