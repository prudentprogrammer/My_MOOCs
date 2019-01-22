import edu.duke.*;

/**
 * Write a description of Part1 here.
 *
 * @author Arjun Bharadwaj
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ( (currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon, currIndex + 1); // Search for the next occurence codon.
        }
        return dna.length();
    }

    public void testFindStopCodon () {
        String dna = "AAATGAAAAAATAA";
        int startIndex = dna.indexOf("ATG");
        System.out.println(findStopCodon(dna, startIndex, "TAA"));

        dna = "AAATGATAA";
        System.out.println(findStopCodon(dna, startIndex, "TAA"));
    }

    public String findGene (String dna, int s) {
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG", s);
        if (startIndex == -1)
            return "";

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        if (minIndex == dna.length())
            return "";
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public int findStopIndex(String dna, int s) {
        dna = dna.toUpperCase();
    
        int taaIndex = findStopCodon(dna, s, "TAA");
        int tagIndex = findStopCodon(dna, s, "TAG");
        int tgaIndex = findStopCodon(dna, s, "TGA");
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        return minIndex;
    }
        

    public void testFindGene() {
        // Test code goes here
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        System.out.println(findGene(dna, 0));

    }

    public StorageResource getAllGenes (String dna) {
        dna = dna.toUpperCase();
        int start = 0;
        StorageResource genes = new StorageResource();
        
        while (true) {
            start = dna.indexOf("ATG", start);
            if (start == -1)
                break;
            //System.out.println(stop + "," + start + "," + loc);
            int stop = findStopIndex(dna, start);
            if ( stop != dna.length() ) {
                genes.add( dna.substring(start, stop + 3) );
                start = stop + 3;
            } else {
                start = start + 3;
            }
               
        }
        
        return genes;
        /*dna = dna.toUpperCase();
        int startIndex = 0;
        int totalGenes = 0;
        StorageResource resource = new StorageResource();

        while (true) {
           String tempGene = findGene(dna, startIndex);
           if (tempGene.isEmpty()) { // No genes found!
               break;
           }
           
           totalGenes += 1;
           resource.add(tempGene);
           startIndex += dna.indexOf(tempGene) + tempGene.length();
        }
        return resource;*/
    }

    public void testAllGenes() {
        //System.out.println("Gene: " + tempGene);
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        for(String s: getAllGenes(dna).data())
            System.out.println(s);
    }
}
