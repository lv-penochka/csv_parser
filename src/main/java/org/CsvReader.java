package org;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CsvReader {
public static void main(String[] args) throws IOException {
    Path path =Path.of("src","foreign_names.csv");
    GenerateListOfPersons(path);
    ShowData(path);

}
    public static void GenerateListOfPersons(Path path) throws IOException {
        List<Person> personList = Files.lines(path)
                .skip(1)
                .map(line -> {
                    try {
                        return getPerson(line);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        System.out.println(personList);
    }
    public static void ShowData(Path path) throws IOException {
        Path pathWrite = Path.of("src").resolve("foreign_names_edited.csv");
        HashMap <String,UUID> departmentsWithKeys = new HashMap<>();
        try (FileWriter writer = new FileWriter(pathWrite.toString());
             BufferedWriter bw = new BufferedWriter(writer)) {
            Files.lines(path).skip(1).forEach(line -> {
                try {
                    String depName = getDepartmentName(line);
                    if (!departmentsWithKeys.containsKey(depName)){
                        departmentsWithKeys.put(depName,UUID.randomUUID());
                    }
                    bw.write(line + ";"+departmentsWithKeys.get(depName).toString());
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
        if(fields.length!=6)
            throw new RuntimeException("Invalide CSV line : "+ line);
        return new Person(Integer.parseInt(fields[0]),
                                fields[1],
                                fields[2],
                                dateFormatter.parse(fields[3]),
                Integer.parseInt(fields[5]),
                Departments.getDepartment(fields[4]));
    }
    private static String getDepartmentName(String line) throws ParseException {
        String[] fields = line.split(";");
        if(fields.length!=6)
            throw new RuntimeException("Invalide CSV line : "+ line);
        return fields[4];
    }
    private void ExpandPerson(){

    }
}
