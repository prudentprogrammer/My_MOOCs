import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of WeatherAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WeatherAnalyzer {
    
    // Calculates the coldest hour in a particular file.
    public CSVRecord getColdestHourInFile (CSVParser parser) {
        CSVRecord minRecord = null;
        
        for(CSVRecord record: parser) {
            if (minRecord == null) {
                minRecord = record;
            } else {
                double temp = Double.parseDouble(record.get("TemperatureF"));
                if (temp != -9999 && temp < Double.parseDouble(minRecord.get("TemperatureF"))) {
                    minRecord = record;
                }
            }
            //System.out.printf("%s %s: %s\n", record.get("DateUTC"), record.get("TimeEST"), record.get("TemperatureF"));
        }
        return minRecord;
    }
    
    // Obtains the file name with the coldest temperature.
    public String getFileWithColdestTemperature () {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord minRecord = null;
        File minFile = null;
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            if(minRecord == null) {
                minRecord = getColdestHourInFile (fr.getCSVParser());
                minFile = f;
            } else {
                CSVRecord temp = getColdestHourInFile (fr.getCSVParser());
                if (Double.parseDouble(temp.get("TemperatureF")) < Double.parseDouble(minRecord.get("TemperatureF"))) {
                    minRecord = temp;
                    minFile = f;
                }
            }
        }
        return minFile.getName();
    }
    
    // Calculates the csv record with the lowest humidity.
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        CSVRecord minRecord = null;
        
        for(CSVRecord record: parser) {
            if (minRecord == null) {
                minRecord = record;
            } else {
                if (!record.get("Humidity").equals("N/A")) {
                    double humidity = Double.parseDouble(record.get("Humidity"));
                    if (humidity < Double.parseDouble(minRecord.get("Humidity"))) {
                        minRecord = record;
                    }
                } // End of if
            }
        }
        return minRecord;
    } // End of method
    
    // Obtains the file name with the coldest temperature.
    public String getFileWithLowestHumidity () {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord minRecord = null;
        File minFile = null;
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            if(minRecord == null) {
                minRecord = lowestHumidityInFile (fr.getCSVParser());
                minFile = f;
            } else {
                CSVRecord rec = lowestHumidityInFile (fr.getCSVParser());
                if (Double.parseDouble(rec.get("Humidity")) < Double.parseDouble(minRecord.get("Humidity"))) {
                    minRecord = rec;
                    minFile = f;
                }
            }
        }
        return minFile.getName();
    }
    
    // Calculates the coldest hour in a particular file.
    public double averageTemperatureInFile (CSVParser parser) {
        double sum = 0;
        int count = 0;
        
        for(CSVRecord record: parser) {
            sum += Double.parseDouble(record.get("TemperatureF"));
            count += 1;
        }
        return sum / count;
    }
    
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double sum = 0;
        int count = 0;
        
        for(CSVRecord record: parser) {
            if (Integer.parseInt(record.get("Humidity")) >= value) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                count += 1;
            }
            
            
        }
        return (count != 0) ? (double)sum / count: 0;
    }
        
    
    /********** UNIT TEST FUNCTIONS *******************/
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord rec = getColdestHourInFile(fr.getCSVParser());
        System.out.println(String.format("The coldest temperature was %s on %s", rec.get("TemperatureF"), rec.get("DateUTC")));
    }
    
    public void testFileWithColdestTemperature() {
        String fileName = getFileWithColdestTemperature();
        String filePath = "nc_weather/2013/" + fileName;
        CSVRecord coldestRecord = getColdestHourInFile(new FileResource(filePath).getCSVParser());
        System.out.println(String.format("Coldest day was in file %s", fileName));
        System.out.printf("Coldest temperature on that day was %s\n", coldestRecord.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: ");
        
        for(CSVRecord record: new FileResource(filePath).getCSVParser()) {
            System.out.printf("%s %s: %s\n", record.get("DateUTC"), record.get("TimeEST"), record.get("TemperatureF"));
        }
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = lowestHumidityInFile(parser);
        System.out.println(String.format("Lowest Humidity was %s at %s", record.get("Humidity"), record.get("DateUTC")));
    }
    
    public void testLowestHumidityInManyFiles() {
        String fileName = getFileWithLowestHumidity();
        CSVRecord record = lowestHumidityInFile(new FileResource("nc_weather/2013/" + fileName).getCSVParser());
        System.out.println(String.format("Lowest Humidity was %s at %s", record.get("Humidity"), record.get("DateUTC")));
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(String.format("Average temperature in file is %f", averageTemperatureInFile(parser)));
    }
    
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double val = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (val != 0) {
            System.out.println(String.format("Average temperature in file is %f", val));
        } else {
            System.out.println("No temperatures with that humidity");
        }
    }

}
