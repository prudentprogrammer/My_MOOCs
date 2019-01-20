import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of CountryExportsAnalyzer here.
 * 
 * @author Arjun Bharadwaj 
 * @version 1.0 
 */
public class CountryExportsAnalyzer {

    public void readExports() {
        FileResource fr = new FileResource();
        //CSVParser cp = fr.getCSVParser();
        //System.out.println(getCountryInfo(cp, "Nauru"));
        
        CSVParser cp = fr.getCSVParser();
        listExportersTwoProducts(cp, "cotton", "flowers");
        
        cp = fr.getCSVParser();
        System.out.println(getNumberOfExporters(cp, "cocoa"));
        
        cp = fr.getCSVParser();
        printBigExporters(cp, "$999,999,999,999");
    }

    public String getCountryInfo(CSVParser cp, String country) {
        for (CSVRecord record: cp) {
            String countryName = record.get("Country");
            String exportsOfCountry = record.get("Exports");
            String valueInDollars = record.get("Value (dollars)");
            if (countryName.equals(country)) {
                return String.format("%s: %s: %s", countryName, exportsOfCountry, valueInDollars);
            }
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord record: parser) {
            String countryName = record.get("Country");
            String exportsOfCountry = record.get("Exports");
            if (exportsOfCountry.contains(exportItem1) && exportsOfCountry.contains(exportItem2)) {
                System.out.println(countryName);
            }
        }
    }

    public int getNumberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record: parser) {
            String exportsOfCountry = record.get("Exports");
            if (exportsOfCountry.contains(exportItem))
                count += 1;
        }
        return count;
    }

    public void printBigExporters(CSVParser parser, String amount) {
        int maxLength = amount.length();
        for(CSVRecord record: parser) {
            String valueInDollars = record.get("Value (dollars)");
            String countryName = record.get("Country");
            if (valueInDollars.length() > maxLength) {
                System.out.println(String.format("%s %s", countryName, valueInDollars)); 
            }
        } // End of for
    }

    public static void main(String[] args) {
        // Client code.
    }
}
