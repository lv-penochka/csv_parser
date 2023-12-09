package org;

import java.io.IOException;
import java.nio.file.Path;


public class Main {
    public static void main(String[] args) throws IOException {
        Path sourcePath = Path.of("src","foreign_names.csv");
       CsvReader.parsePersons(sourcePath);
    }
}
