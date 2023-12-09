package org;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {
    Path sourcePath = Path.of("src","foreign_names.csv");
    @Test
    public void test_readingPersons() {
        try {
            List<Person> persons= CsvReader.parsePersons(sourcePath);
            Assertions.assertEquals(persons.get(0).getPersonID(), 53970);
            Assertions.assertEquals(persons.get(0).getName(),"Zainab");
            Assertions.assertEquals(persons.get(0).getSex(),"Female");
            Assertions.assertEquals(persons.get(0).getBirthday(),"14.11.1947");
            Assertions.assertEquals(persons.get(0).getDepartment().getDepartmentName(),"N");
            Assertions.assertEquals(persons.get(0).getSalary(), 6300);
            Assertions.assertEquals(persons.size(), 3);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    public void test_creatingUUID() {
        try {

            List<Person> persons= CsvReader.parsePersons(sourcePath);
            Assertions.assertEquals(persons.get(1).getDepartment().getDepartmentID(),persons.get(2).getDepartment().getDepartmentID());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    public void test_failedReadingFile() {
        Path brokenSourcePath = Path.of("src","forigner_names.csv");
        Assertions.assertThrows(IOException.class, () -> {
            CsvReader.parsePersons(brokenSourcePath);
        });
    }
    @Test
    public void test_brokenCSV() {
        Path brokenSourcePath = Path.of("src","foreign_names_broken.csv");
        Assertions.assertThrows(RuntimeException.class, () -> {
            CsvReader.parsePersons(brokenSourcePath);
        });
    }

}