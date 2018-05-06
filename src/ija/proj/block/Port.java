package ija.proj.block;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Port implements Serializable
{
    // --- Atributtes ---
    protected Map<String, Double> content = new HashMap<String, Double>();
    private Port connectedPort = null;
    private Block ownerBlock = null;
    private boolean type;
    
    // --- Constants ---
    public static final boolean inputType = true;
    public static final boolean outputType = false;
    
    // --- Constructors ---
    public Port(String[] names, boolean type)
    {
        this.type = type;
        
        for(String name : names)
        {
            // @todo add check for existing names
            
            this.content.put(name, Double.NaN);
        }
    }
    
    public Port(String name, boolean type)
    {
        this(new String[]{name}, type);
    } 

    /**
     * @brief Checks if two Ports have compatibile type for connection
     * @param other Other Port for connection
     * @return True if compatibile or false if not
     */
    public boolean compatibile(Port other)
    {
        return this.getNames().equals(other.getNames()) && this.getType() != other.getType();
        // Check if same value types are used && one block is input and other output
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
            System.err.format("Port.setValue: Name '%s' not found in port\n",name);
            return false;
        }

        // --- Set value ---
        this.content.put(name, value);

        // --- Set value to connected port ---
        if(this.type == Port.outputType && this.connectedPort != null)  // Only set value to output port
            return this.connectedPort.setValue(name, value);
        
        return true;
    }
    
    public double getValue(String name)     
    {
        // --- Check if name is present in this Port ---
        if(!this.content.containsKey(name))
        {
            System.err.format("Port.getValue: Name '%s' not found in port\n",name);
            return Double.NaN;
        }

        return this.content.get(name);
    }
    
    /**
     * @brief Check if port has missing value
     * @return true if any value equals Double.NaN, false if every value is legit
     */
    public boolean hasNaNValue()
    {
        for(double value : this.getContent().values())
        {
            if(Double.isNaN(value))
                return true;
        }
        return false;
    }

    public Map<String, Double> getContent()     
    {
        return this.content;
    }  

    /**
     * @brief Get set of Port type names
     * @return Set of Port type names
     */
    public Set<String> getNames()
    {
        return this.content.keySet();
    }
    
    public String getName()
    {
        // Temporary
        return this.content.keySet().toArray()[0].toString();
    }

    public boolean setConnectedPort(Port other)
    {
        // --- Check if port is empty ---
        if(this.connectedPort != null || other.connectedPort != null)
        {
            System.err.println("Port.setConnectedPort: Port is already connected");
            return false;                
        }
        
        // --- Check if Ports are comptibile ---
        if(!this.compatibile((other)))
        {
            System.err.println("Port.setConnectedPort: Port types are not compatibile");
            return false;               
        }

        // --- Connect Ports ---
        this.connectedPort = other;
        other.connectedPort = this;

        return true;
    }

    public Port getConnectedPort()
    {
        return this.connectedPort;
    }	
    
    public Block getOwnerBlock()
    {
        return this.ownerBlock;
    }	
    
    public void setOwnerBlock(Block ownerBlock)
    {
        this.ownerBlock = ownerBlock;
    }	
    
    public boolean getType()
    {
        return this.type;
    }	
    
    public String print()
    {
        String result = "";

        for(String name : this.getNames())
        {
            result.concat("["+name+"]");
        }

        if(this.getConnectedPort() == null)
            result.concat(" Not connected");
        else
            result.concat(" Connected");
    
        return result;  // [real][imaginary] Not connected
    }
    
    public String printConnection()
    {
        String result = "";
        boolean first = true; // Don't insert comma at beginning

        for(String name : this.getNames())
        {
            if(!first)
                result.concat(", ");
            else
                first = false;

            result.concat("["+name+"] ");
            result.concat(String.valueOf(this.getValue(name)));
        }

        return result;  // [real] 42, [imaginary] 78
    }
}

