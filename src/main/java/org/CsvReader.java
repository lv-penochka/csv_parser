package org;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class CsvReader {
    public static List<Person> parsePersons(Path sourcePath) throws IOException {
        PreprocessCSV(sourcePath);
        return GenerateListOfPersons(getProcessedPath(sourcePath));

    }
    private static Path getProcessedPath(Path sourcePath){
        return Path.of(sourcePath.getParent().toString(),"edited_" + sourcePath.getFileName());
    }

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

    private static void PreprocessCSV(Path sourcePath) throws IOException {
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

    private static Person getPerson(String line) throws ParseException {
        String[] fields = line.split(";");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        if(fields.length!=7)
            throw new RuntimeException("Invalide CSV line : "+ line);
        return new Person(Integer.parseInt(fields[0]),
                                fields[1],
                                fields[2],
                                dateFormatter.parse(fields[3]),
                Integer.parseInt(fields[5]),
                new Department(fields[4], UUID.fromString(fields[6])));
    }
    private static String getDepartmentNameFrom(String line) throws ParseException {
        String[] fields = line.split(";");
        if(fields.length!=6)
            throw new RuntimeException("Invalide CSV line : "+ line);
        return fields[4];
    }
}
