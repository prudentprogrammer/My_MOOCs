import edu.duke.*;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public void processGenes (StorageResource sr) {
        System.out.println("Total genes: " + sr.size());
        printStringsLongerThan(sr, 60);
        printStringsCGRatioLargerThan(sr,0.35);
        printLongestGene(sr);
    }
    
    
    public void printStringsLongerThan(StorageResource sr, int limit) {
        int count = 0;
        for(String s: sr.data()) {
            if (s.length() > limit) {
                count += 1;
                System.out.printf("Gene %d: %s\n", count, s);
            }
        }
        System.out.printf("Total count larger than %d: %d\n", limit, count);
    }
    
    public void printStringsCGRatioLargerThan(StorageResource sr, double ratio) {
        int count = 0;
        Part2 p = new Part2();
        for (String s: sr.data()) {
            if (p.cgRatio(s) > ratio) {
                count += 1;
                System.out.println(s);
            }
        }
        System.out.printf("Total genes having count > %f are %d\n", ratio, count); 
    }
    
    public void printLongestGene(StorageResource sr) {
        String longestGene = "";
        
        for(String s: sr.data()) {
            if (s.length() > longestGene.length()) {
                longestGene = s;
            }
        }

        System.out.println("Longest Gene is: " + longestGene);
        System.out.println("Longest Gene has length: " + longestGene.length());
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        processGenes(new Part1().getAllGenes(dna));
    }
    
}
