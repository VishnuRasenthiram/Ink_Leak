package universite_paris8.iut.ink_leak.Vue;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Musique {

    private Clip clip;

    public void jouer(String chemin, int repetitions) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(chemin));
            clip = AudioSystem.getClip();
            clip.open(audio);
            if (repetitions == -1) { clip.loop(clip.LOOP_CONTINUOUSLY); }
            else clip.loop(repetitions);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void arreter() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
