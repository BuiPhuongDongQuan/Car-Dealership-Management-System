package Features;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    //take array and turn it to CSV string
    public static String arrayToCSVString(String[] array){
        StringBuilder data = new StringBuilder();

        for(int i = 0; i < array.length; i++){
            data.append(array[i]);
            if(i != array.length -1){
                data.append(",");
            }
        }
        String string = data.toString();
        return string;
    }

    //take arraylist and turn it to CSV string
    public static String arrayListToCSVString(ArrayList<String> arrayList){
        StringBuilder data = new StringBuilder();

        for(int i = 0; i < arrayList.size(); i++){
            if(i != arrayList.size() - 1){
                data.append(arrayList.get(i) + ",");
            }
            else{
                data.append(arrayList.get(i));
            }
        }
        String string = data.toString();
        return string;
    }

    //remove specific string in file
    //Source:https://javaconceptoftheday.com/modify-replace-specific-string-in-text-file-in-java/
    public static void modifyFile(String filePath, String oldString, String newString){
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            //Replacing oldString with newString in the oldContent
            String newContent = oldContent.replaceAll(oldString, newString);
            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //read all lines in a file
    //Source: https://www.w3schools.com/java/java_files_read.asp
    public static void readAllLines(String filepath){
        try{
            File file = new File(filepath);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
