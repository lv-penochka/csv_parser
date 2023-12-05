package org;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.UUID;

public class CsvReader {
public static void main(String[] args) throws IOException {
    String file ="src\\foreign_names (1).csv";
    Path path =Path.of("src","foreign_names (1).csv");
    GenerateListOfPersons(path);

}
    private static void GenerateListOfPersons(Path path) throws IOException {
        List<Person> personList = Files.lines(path)
                .skip(1)
                .map(CsvReader::getPerson)
                .collect(Collectors.toList());
        System.out.println(personList);
    }
    private static void ShowData(Path path) throws IOException {
        Files.lines(path).skip(1).map(line->{
            return getPerson(line);
        }).forEach(System.out::println);
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
                new Department(fields[4]));
    }
}
