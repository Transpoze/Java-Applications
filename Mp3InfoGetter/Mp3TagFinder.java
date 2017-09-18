package mp3Tag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * @author Addi Djikic
 * 
 *         Hemuppgift 2, Övning 5: Skriver ut information från en mp3-fil som är
 *         lagrad i de sista 128-bitarna, Ide till kod som gör om mp3 till bytes tagen från
 *         http://www.javaprogrammingforums
 *         .com/java-theory-questions/335-parsing-id3-tags-mp3.html
 *         Kändes ej nödvändigt att dela upp ett så litet program i metoder
 * 
 */
public class Mp3TagFinder {

	public static void main(String[] arguments) throws FileNotFoundException,
			IOException {

		// Reads the mp3-file with randomaccessfile
		String mp3Song = "/Users/Addi/Documents/mp3Files/RideTheLightning.mp3";
		File file = new File(mp3Song);
		RandomAccessFile raf = new RandomAccessFile(file, "r");

		// Takes the last 128 bits and creates a new string of them
		int songSize = (int) raf.length();
		raf.skipBytes(songSize - 128);
		byte[] usableBits = new byte[128];
		raf.read(usableBits);
		String songInfoID3 = new String(usableBits);

		System.out.println("Title: " + songInfoID3.substring(3, 32));
		System.out.println("Artist: " + songInfoID3.substring(33, 62));
		System.out.println("Album: " + songInfoID3.substring(63, 91));
		System.out.println("Year: " + songInfoID3.substring(93, 97));
		System.out.println("Comment: " + songInfoID3.substring(97, 127));
		

	}
}