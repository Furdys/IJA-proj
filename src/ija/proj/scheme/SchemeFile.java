/**
 * Backend interface for saving and loading scheme to file.
 * @brief Package for SchemeFile
 * @file SchemeFile.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.scheme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @brief The Scheme class is abstract clas providing saving and loading scheme to file.
 */
abstract public class SchemeFile
{
    /**
     * @brief save is static method saving scheme to specified file.
     * @param scheme Scheme to be saved.
     * @param path Path to where should Scheme be saved.
     * @return True when successful, false when not
     */
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

    /**
     * @brief open is static method loading scheme from specified file.
     * @param path Path to where Scheme is saved.
     * @return Loaded Scheme when successful, null when not.
     */    
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
