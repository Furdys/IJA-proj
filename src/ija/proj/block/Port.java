/**
 * Backend representation of port.
 * @brief Package for Port
 * @file Port.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @brief The Port class is backend representation of port and contains its interface.
 */
public class Port implements Serializable
{
    // --- Atributtes ---
    protected Map<String, Double> content = new HashMap<String, Double>();
    private Port connectedPort = null;
    private Block ownerBlock = null;
    private boolean type;
    
    // --- Constants ---
    public static final boolean inputType = true;   // @brief Class constant representing Port that is input
    public static final boolean outputType = false; // @brief Class constant representing Port that is output
    
    // --- Constructors ---
    /**
     * @brief Constructor of class Port with value type consisting of more than one part.
     * @param names  Array of value type names.
     * @param type  False for output, true for input.
     */
    public Port(String[] names, boolean type)
    {
        this.type = type;
        
        for(String name : names)
        {
            // @todo add check for existing names
            
            this.content.put(name, Double.NaN);
        }
    }

    /**
     * @brief Constructor of class Port with value type consisting of only one part.
     * @param name  Value type name.
     * @param type  False for output, true for input.
     */    
    public Port(String name, boolean type)
    {
        this(new String[]{name}, type);
    } 

    /**
     * @brief compatible is method determining if this and other Port are compatibile for connection.
     * @param other Port to connect to.
     * @return True when compatibile, false when not.
     */
    public boolean compatibile(Port other)
    {
        return this.getNames().equals(other.getNames()) && this.getType() != other.getType();
        // Check if same value types are used && one block is input and other output
    }

    /**
     * @brief setValue is method used to set value with specified name of the Port.
     * @param name Name of value type to be set.
     * @param value Value to be set.
     * @return True when successful, false when not.
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

    /**
     * @brief getValue is method used to get value with specified name of the Port.
     * @param name  Name of desired value type.
     * @return  Desired value.
     */    
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
     * @brief hasNaNvalue checks if port has any missing value
     * @return True if any value equals Double.NaN, false if every value is legit
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

    /**
     * @brief getValue is method used to get map of both value and its name with specified name of the Port.
     * @return  Map of values and names of this Port.
     */
    public Map<String, Double> getContent()     
    {
        return this.content;
    }  

    /**
     * @brief getNames is method used to get vector of value type names of this Port
     * @return Vector of value type names of this Port
     */
    public Set<String> getNames()
    {
        return this.content.keySet();
    }

    /**    
     * @brief setConnectedPort is method used to set connected Port to this Port
     * @param other Other Port this Ports is connected to
     * @return True when successful, false when not
     */
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

    /**
     * @brief getConnectedPort is method returning pointer of Port connected to this Port
     * @return Pointer of Port connected to this Port or NULL if none Port is connected
     */    
    public Port getConnectedPort()
    {
        return this.connectedPort;
    }	
    
    public void resetConnectedPort()
    {
    	this.connectedPort = null;
    	
    }
    
    /**
     * @brief getOwnerBlock is method returting pointer of Block that owns this Port
     * @return Pointer of Block that owns this Port
     */    
    public Block getOwnerBlock()
    {
        return this.ownerBlock;
    }	

    /**
     * @brief setOwnerBlock is method that sets owner Block of this Port
     * @param otherBlock Pointer of owner Block
     */    
    public void setOwnerBlock(Block ownerBlock)
    {
        this.ownerBlock = ownerBlock;
    }	
    
    /**
     * @brief getType determines type of the Port
     * @return Port.inputType if Port is input or Port.outputType if Port is output
     */
    public boolean getType()
    {
        return this.type;
    }	

    /**
     * @brief print is method used by GUI to get informations about this Port
     * @return String containing informations about this Port
     */    
    public String print()
    {
        String result = "";

        for(String name : this.getNames())
        {
            result += "["+name+"]";
            result += this.getValue(name);
        }

        if(this.getConnectedPort() == null)
            result += " Not connected";
       /* else
            result += " Connected";*/
        
        
    
        return result;  // [real][imaginary] Not connected
    }

    /**
     * @brief printConnection is method used by GUI to get informations and value about connection of Port
     * @return String containing informations and value about connection of Port
     */    
    public String printConnection()
    {
        String result = "";
        boolean first = true; // Don't insert comma at beginning

        for(String name : this.getNames())
        {
            if(!first)
                result += ", ";
            else
                first = false;

            result += "["+name+"] ";
            result += String.valueOf(this.getValue(name));
        }

        return result;  // [real] 42, [imaginary] 78
    }
}

