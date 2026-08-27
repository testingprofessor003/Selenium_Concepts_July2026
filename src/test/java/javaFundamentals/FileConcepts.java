package javaFundamentals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileConcepts {

    void main()
    {
        //This will print the current path of the project
        IO.println(System.getProperty("user.dir"));

        //IO Package is mainly used for Input Output operations with external sources for data persistency
        //File is a class coming from java.io package
        File f1=new File("SampleData.txt"); //Path of the file that you pass here is not mandatory to exist

        //Checks if the given file exists or not (Returns true if the above condition is satisfied else false)
        IO.println(f1.exists());

        //Returns if the given path in the file object is a file or not
        //Returns true if the above condition is satisfied else false
        IO.println(f1.isFile());

        //Returns true if the file can be read else false
        IO.println(f1.canRead());

        try {
            f1.createNewFile(); //Creates a new file in the given path if it does not exist
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File f2=new File(System.getProperty("user.dir")+"//Logs");

        //Creates the missing directories
        f2.mkdirs();

        //f2.getPath() --> Gets the file path stored inside the file object
        File f3=new File(f2.getPath()+"//Logs.txt");

        try {
            f3.createNewFile();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Files is a class coming from java.nio package
        //Nio stands for New Input Output Operations
        try {
            Files.writeString(f3.toPath(),"Hello World!!!"); //Used to write the data to the file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
