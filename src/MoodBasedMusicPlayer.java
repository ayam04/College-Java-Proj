import java.io.File;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class MoodBasedMusicPlayer {

    public static void main(String[] args) {
        String baseFolderPath = "src/Moods";

        String mood = getUserMoodInput();
        String moodFolderPath = baseFolderPath + "/" + mood;
        playRandomSongFromMoodFolder(moodFolderPath);
    }

    public static String getUserMoodInput() {
        System.out.print("Enter Your Mood: ");
        Scanner sc = new Scanner(System.in);
        String mood = sc.nextLine();
        sc.close();
        return mood;
    }

    public static void playRandomSongFromMoodFolder(String moodFolderPath) {
        File moodFolder = new File(moodFolderPath);

        if (!moodFolder.exists() || !moodFolder.isDirectory()) {
            System.out.println("Mood folder not found: " + moodFolderPath);
            return;
        }

        File[] songFiles = moodFolder.listFiles();

        if (songFiles == null || songFiles.length == 0) {
            System.out.println("No songs found in mood folder: " + moodFolderPath);
            return;
        }

        File randomSong = songFiles[new Random().nextInt(songFiles.length)];
        String songPath = randomSong.getAbsolutePath();

        playSong(songPath);
    }

    public static void playSong(String songPath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(songPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
