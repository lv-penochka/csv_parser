import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
public class CsvReader {
public static void main(String[] args) throws IOException {
    String file ="src\\foreign_names (1).csv";
    Path path =Path.of("src","main","foreign_names (1).csv");
    Files.lines(path).skip(1).map(line->{
        String[] fields =line.split(";");
        return new Person(Integer.prarseInt());
    })
    BufferedReader reader =null;
    String line ="";
    try{
        reader =new BufferedReader(new FileReader(file));
        while ((line = reader.readLine())!= null){
            String[] row=line.split(";");
            for(String index: row){
                System.out.printf("%-10s",index);
            }
            System.out.println();
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    finally {
        try{
            if (reader != null) {
                reader.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
}
