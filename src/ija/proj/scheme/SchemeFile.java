package ija.proj.scheme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

abstract public class SchemeFile
{
    static public boolean save(Scheme scheme, String path)
    {
        if(scheme == null)
        {
            System.err.println("SchemeFile.save(): Scheme is null");
            return false;
        }
        if(scheme.isReadOnly())
        {
            System.err.println("SchemeFile.save(): Scheme is in read only mode (it is already executed or in middle of execution)");
            return false;
        }
        
        try
        {
            FileOutputStream fileStream = new FileOutputStream(new File(path));
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);  
            
            objectStream.writeObject(scheme);

            objectStream.close();
            fileStream.close();
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("SchemeFile.save(): File not found");
            return false;  
        }
        catch (IOException ex)
        {
            System.err.println("SchemeFile.save(): IOException");
            return false;
        }
        
        return true;
    }
    
    static public Scheme open(String path)
    {
        Scheme loadedScheme;
                
        try
        {
            FileInputStream fileStream = new FileInputStream(new File(path));
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);  
            
            loadedScheme = (Scheme) objectStream.readObject();
            
            objectStream.close();
            fileStream.close();
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("SchemeFile.open(): File not found");
            return null;  
        }
        catch (IOException ex)
        {
            System.err.println("SchemeFile.open(): IOException");
            return null;
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("SchemeFile.open(): Unexpected content of file");
            return null;
        }  
        
        return loadedScheme;
    }
}
