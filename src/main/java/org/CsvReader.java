package org;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
/**
 This class provides methods to parse a CSV file containing information about persons and their departments.
**/

public class CsvReader {
    /**
     Parse the CSV file and return a list of Person objects.
     @param sourcePath the path to the CSV file to be parsed
     @return a list of Person objects parsed from the CSV file
     @throws IOException if an I/O error occurs
     */
    public static List<Person> parsePersons(Path sourcePath) throws IOException {
        PreprocessCSV(sourcePath);
        return GenerateListOfPersons(getProcessedPath(sourcePath));

    }
    /**
     Get the processed path for the CSV file.
     @param sourcePath the path to the source CSV file
     @return the processed path for the CSV file
     */
    private static Path getProcessedPath(Path sourcePath){
        return Path.of(sourcePath.getParent().toString(),"edited_" + sourcePath.getFileName());
    }
    /**
     Generate a list of Person objects from the processed CSV file.
     @param path the path to the processed CSV file
     @return a list of Person objects parsed from the processed CSV file
     @throws IOException if an I/O error occurs
     */
    private static List<Person> GenerateListOfPersons(Path path) throws IOException {
       return Files.lines(path)
                .skip(1)
                .map(line -> {
                    try {
                        return getPerson(line);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
    /**
     Preprocess the CSV file by adding department UUIDs to each line.
     @param sourcePath the path to the source CSV file
     @throws IOException if an I/O error occurs
     */
    private static void PreprocessCSV(Path sourcePath) throws IOException {
        if (! Files.exists(sourcePath)){
            throw new IOException("File not found");
        }

        HashMap <String,UUID> departmentsWithKeys = new HashMap<>();
        Path outputPath=getProcessedPath(sourcePath);

        final AtomicBoolean isFirstLine = new AtomicBoolean(true);
        try (FileWriter writer = new FileWriter(outputPath.toString());
             BufferedWriter bw = new BufferedWriter(writer)) {
            Files.lines(sourcePath).forEach(line -> {
                try {
                    if (isFirstLine.get()) {
                        bw.write(line + ";department UUID");
                        isFirstLine.set(false);
                    } else {
                        String depName = getDepartmentNameFrom(line);
                        if (!departmentsWithKeys.containsKey(depName)) {
                            departmentsWithKeys.put(depName, UUID.randomUUID());
                        }
                        bw.write(line + ";" + departmentsWithKeys.get(depName).toString());
                    }
                    bw.newLine();
                } catch (Exception e) {

                }
            } );

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
    /**
     Get a Person object from a CSV line.
     @param line the CSV line
     @return a Person object parsed from the CSV line
     @throws ParseException if an error occurs during parsing
     */
    private static Person getPerson(String line) throws ParseException {
        String[] fields = line.split(";");
        if(fields.length!=7)
            throw new RuntimeException("Invalid CSV line : "+ line);
        return new Person(Integer.parseInt(fields[0]),
                                fields[1],
                                fields[2],
                                fields[3],
                Integer.parseInt(fields[5]),
                new Department(fields[4], UUID.fromString(fields[6])));
    }
    /**
     Get the department name from a CSV line.
     @param line the CSV line
     @return the department name parsed from the CSV line
     @throws ParseException if an error occurs during parsing
     */
    private static String getDepartmentNameFrom(String line) throws ParseException {
        String[] fields = line.split(";");
        if(fields.length!=7)
            throw new RuntimeException("Invalid CSV line : "+ line);
        return fields[4];
    }

}
