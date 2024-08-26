package Features;

import java.io.*;
import java.util.ArrayList;

public class Features {
    //Count lines and keep track of the ID
    //Source: https://mkyong.com/java/how-to-get-the-total-number-of-lines-of-a-file-in-java/
    public static int countLine(String filePath) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null)
                lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // Read and print out columns of a file
    //Source: https://www.youtube.com/watch?v=Ek6HFMNi3fs
    public static String[] ReadCol(int col, String filepath, String delimiter){
        String data[];
        String currentLine;
        ArrayList<String> colData = new ArrayList<String>();

        try{
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null) {
                data = currentLine.split(delimiter);
                colData.add(data[col]);
            }

            fr.close();
            br.close();
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }

        //remove the first comment line in files
        colData.remove(0);

        return colData.toArray(new String[0]);
    }

    public static void writeToFile(String filePath, String string){
        try{
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(string);

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
