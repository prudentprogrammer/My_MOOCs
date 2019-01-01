import java.util.*;
import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {

    public List<String> findYoutubeLinks (String url) {
        List<String> links = new ArrayList<>();
        URLResource us = new URLResource(url);
        for(String line: us.lines()) {
            int youtubeIndex = line.indexOf("youtube.com");
            if (youtubeIndex != -1) {
                int beg = line.lastIndexOf("\"",youtubeIndex);
                int end = line.indexOf("\"", youtubeIndex+1);
                links.add(line.substring(beg + 1, end));
            }
        } // End of for
        return links;
    }
    
    
    public void testing() {
        System.out.println("--------------------------------");
        List<String> links = findYoutubeLinks("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String link: links)
            System.out.println(link);
        System.out.println("--------------------------------");
    }
    
    public static void main (String[] args) {
        Part4 p = new Part4();
        p.testing();
    } // End of main
}
