package blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Port
{
    private Map<String, Double> content = new HashMap<String, Double>();
    private Port connectedPort = null;

    public Port(){

    }

    /**
     * @brief Checks if two Ports have compatibile type for connection
     * @param other Other Port for connection
     * @return True if compatibile or false if not
     */
    public boolean compatibile(Port other)
    {
        return this.getNames().equals(other.getNames());
    }

    /**
     * @brief Set one Port type value
     * @param name Name of value to be changed
     * @param value New value
     * @return True if successful or false if name is not found 
     */
    public boolean setValue(String name, double value)     
    {
        // --- Check if name is present in this Port ---
        if(!this.content.containsKey(name))
        {
            System.err.println("Port.setValue: Name not found in port");
            return false;
        }

        // --- Set value ---
        this.content.put(name, value);

        return true;
    }



    /**
     * @brief Get set of Port type names
     * @return Set of Port type names
     */
    public Set<String> getNames()
    {
        return this.content.keySet();
    }


    public boolean setConnectedPort(Port other)
    {
        // --- Check if Ports are comptibile ---
        if(!this.compatibile((other)))
        {
            System.err.println("Port.setConnectedPort: Port types are not compatibile");
            return false;               
        }

        // --- Connect Ports ---
        this.connectedPort = other;

        return true;
    }

    public Port getConnectedPort()
    {
        return this.connectedPort;
    }	
}
