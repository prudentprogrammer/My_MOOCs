import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

/**
 * Program to analyze the csv files representing babies' births.
 * 
 * @author Arjun Bharadwaj
 * @version 1.0
 */
public class BabyBirths {
    private final String filePath = "us_babynames/us_babynames_by_year/yob%d.csv";
    
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec: fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
        }
    }
            
    public void totalBirths (FileResource fr) {
        int count = 0, totalBoys = 0, totalGirls = 0;
        int girlNames = 0, boyNames = 0;
        
        for (CSVRecord rec: fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            count += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys  += numBorn;
                boyNames += 1;
            } else {
                totalGirls += numBorn;
                girlNames += 1;
            }
        }
        System.out.printf("Total Births: %d\n", count); 
        System.out.printf("Total Girls: %d\n", girlNames);
        System.out.printf("Total Boys: %d\n", boyNames);
    }
    
    public int getRank (int year, String name, String gender) {
        FileResource fr = new FileResource(String.format(filePath, year));
        int girlRank = 0, boyRank = 0;
        for (CSVRecord rec: fr.getCSVParser(false)) {
            String recGender = rec.get(1);
            if (recGender.equals("F")) {
                girlRank += 1;
            } else {
                boyRank += 1;
            }
            
            if (rec.get(0).equals(name) && recGender.equals(gender)) { // Match found!
                if (recGender.equals("F")) {
                    return girlRank;
                } else if (recGender.equals("M")) {
                    return boyRank;
                }
            } // End of if
        }
        
        return -1;
    }
    
    public String getName (int year, int rank, String gender) {
        FileResource fr = new FileResource(String.format(filePath, year));
        int girlRank = 0, boyRank = 0;
        for (CSVRecord rec: fr.getCSVParser(false)) {

            String recGender = rec.get(1);
            if (recGender.equals("F")) {
                girlRank += 1;
            } else {
                boyRank += 1;
            }
            
            if ( (gender.equals("M") && boyRank == rank) ||  (gender.equals("F") && girlRank == rank) ) { // Match found!
                if (recGender.equals("F")) {
                    return rec.get(0);
                } else if (recGender.equals("M")) {
                    return rec.get(0);
                }
            } // End of if
        }
        
        return "NO NAME";
    }
    
    public String whatIsNameInYear (String name, int year, int newYear, String gender) {
        int currYearRank = getRank (year, name, gender);
        String nextYearName = getName (newYear, currYearRank, gender);
        return nextYearName;
    }
    
    public int yearOfHighestRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int maxYear = Integer.MAX_VALUE, ranking = Integer.MAX_VALUE;
        for (File f: dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, fileName.indexOf(".")));
            int temp = getRank (year, name, gender);
            
            if (temp != -1 && temp < ranking) {
                ranking = temp;
                maxYear = year;
            }
        }
        return maxYear;
    }
    
    public double getAverageRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int sum = 0, count = 0;
        for (File f: dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, fileName.indexOf(".")));
            int tempRank = getRank (year, name, gender);
            if (tempRank != -1) {
                sum += tempRank;
            }
            count += 1;
        }
        return sum == 0 ? -1: (double) sum / count;
    }
    
    public int getTotalBirthsRankedHigher (int year, String name, String gender) {
        FileResource fr = new FileResource(String.format(filePath, year));
        
        int rank = getRank (year, name, gender);
        int girlBirths = 0, boyBirths = 0;
        for (CSVRecord rec: fr.getCSVParser(false)) {
            String recName   = rec.get(0);
            String recGender = rec.get(1);
            int tempRank = getRank (year, recName, recGender);
            
            if (tempRank != -1) {

                if (recName.equals(name) && recGender.equals(gender) && tempRank == rank) { // Match found!
                    if (recGender.equals("F")) {
                        return girlBirths;
                    } else if (recGender.equals("M")) {
                        return boyBirths;
                    }
                } // End of if
                
                if (recGender.equals("F")) {
                    girlBirths += Integer.parseInt(rec.get(2));
                } else {
                    boyBirths  += Integer.parseInt(rec.get(2));
                }
            }
        }
        
        return 0;
    }
    
    /******* TEST UTILITY FUNCTIONS ********/
    public void testTotalBirths() {
        FileResource fr = new FileResource(String.format(filePath, 1900));//"us_babynames/us_babynames_test/example-small.csv");
        totalBirths (fr);
        fr = new FileResource(String.format(filePath, 1905));//"us_babynames/us_babynames_test/example-small.csv");
        totalBirths (fr);
    }
    
    public void testGetRank() {
        //System.out.println("getRank(2012, \"Mason\", \"M\") = " + getRank(2012, "Mason", "M"));
        //System.out.println("getRank(2012, \"Mason\", \"F\") = " + getRank(2012, "Mason", "F"));
        System.out.println("getRank(1960, \"Emily\", \"F\") = " + getRank(1960, "Emily", "F"));
        System.out.println("getRank(1971, \"Frank\", \"M\") = " + getRank(1971, "Frank", "M"));
    }
    
    public void testGetName() {
        /*System.out.println("getName (2012, 2, \"M\") = " + getName (2012, 2, "M"));
        System.out.println("getName (2012, 2, \"F\") = " + getName (2012, 2, "F"));*/
        System.out.println("getName (1980, 350, \"F\") = " + getName (1980, 350, "F"));
        System.out.println("getName (1982, 450, \"M\") = " + getName (1982, 450, "M"));
    }
        
    public void testWhatIsNameInYear() {
        /*int currYear = 2012, nextYear = 2014;
        String currName = "Isabella", gender = "F";
        String nextName = whatIsNameInYear (currName, currYear, nextYear, "F");
        System.out.printf("%s born in %d would be %s if she was born in %d.\n",
                            currName, currYear, nextName, nextYear);*/
        
        int currYear = 1972, nextYear = 2014;
        String currName = "Susan", gender = "F";
        String nextName = whatIsNameInYear (currName, currYear, nextYear, "F");
        System.out.printf("%s born in %d would be %s if she was born in %d.\n",
                            currName, currYear, nextName, nextYear);
                            
        currYear = 1974;
        nextYear = 2014;
        currName = "Owen";
        gender = "M";
        nextName = whatIsNameInYear (currName, currYear, nextYear, "M");
        System.out.printf("%s born in %d would be %s if she was born in %d.\n",
                            currName, currYear, nextName, nextYear);
                            
                            
                            
                         
    }
    
    public void testYearOfHighestRank() {
        //System.out.println( "yearOfHighestRank(\"Mason\", \"M\") = " + yearOfHighestRank("Mason", "M"));
        System.out.println( "yearOfHighestRank(\"Genevieve\", \"F\") = " + yearOfHighestRank("Genevieve", "F"));
        System.out.println( "yearOfHighestRank(\"Mich\", \"M\") = " + yearOfHighestRank("Mich", "M"));
    }
    
    public void testGetAverageRank() {
        System.out.println("getAverageRank(\"Susan\", \"F\") = " + getAverageRank("Susan", "F"));
        System.out.println("getAverageRank(\"Robert\", \"M\") = " + getAverageRank("Robert", "M"));
    }
    
    public void testGetTotalBirthsRankedHigher() {
        //System.out.println("getTotalBirthsRankedHigher (1990, \"Emily\", \"F\") = " + getTotalBirthsRankedHigher (1990, "Emily", "F"));
        System.out.println("getTotalBirthsRankedHigher (1990, \"Drew\", \"M\") = " + getTotalBirthsRankedHigher (1990, "Drew", "M"));
    }
    
}

