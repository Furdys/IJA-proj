/**
 * Backend representation of block.
 * @brief Package for Block
 * @file Block.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;

import java.io.Serializable;

/**
 * @brief The Block class is backend representation of block and contains its interface.
 * This class is abstract and is parent of all the block types used.
 */
public abstract class Block implements Serializable
{
    // --- Attributes ---
    private Port[] inputPorts; 
    private Port[] outputPorts;
    private boolean executed = false;

    // --- Cunstructors ---
    /**
     * @brief Main constructor for this class.
     * @param in    Array of input Port objects.
     * @param out   Array of output Port objects.
     */
    Block(Port[] in, Port[] out)
    {
        this.inputPorts = in;
        this.outputPorts = out;
        
        // -- Set owner to ports --
        for(Port port : this.inputPorts)
        {
            port.setOwnerBlock(this);
        }
        for(Port port : this.outputPorts)
        {
            port.setOwnerBlock(this);
        }
    }

    /**
     * @brief Secondary overloaded constructor for this class.
     * @param in    Input Port object.
     * @param out   Output Port object.
     */
    Block(Port in, Port out)
    {
        this(new Port[]{in}, new Port[]{out});
    }

    /**
     * @brief Secondary overloaded constructor for this class.
     * @param in    Array of input Port objects.
     * @param out   Output Port object.
     */    
    Block(Port[] in, Port out)
    {
        this(in, new Port[]{out});
    }

    /**
     * @brief Secondary overloaded constructor for this class.
     * @param in    Array of input Port objects.
     * @param out   Output Port object.
     */    
    Block(Port in, Port[] out)
    {
        this(new Port[]{in}, out);
    }        

    // --- Methods ---
    /**
     * @brief Does general things when executed and calls block-specific action using executeSpecific()
     * @return true when block was executed, false when block is dependet on not yet executed block
     */
    public boolean execute()
    {
        // --- Check for loops ---  // I think we don't need this anymore, it's in findDependency
        if(this.executed == true)
        {
            System.err.println("Block.execute(): Block was already executed (loop detected)");
            //System.exit(2);        
        }
        
        // --- Check for dependency/missing value ---
        for(Port port : this.getInputPorts())    // For every input port
        {
            for(double value : port.content.values()) // For every value in every input port
            {
                if(Double.isNaN(value)) // If value is not yet calculated (connected block wasn't executed)
                {
                    return false;
                }
            }
        }
        
        this.executeSpecific();

        this.executed = true;
        return true;
    }

     /**
     * @brief Virtual method to be replaced by block implemetation. it contains specific action when block is executed
     */
    void executeSpecific()
    {
        System.err.println("Block.executeSpecific(): Called non-overriden function");
    }       

    // --- Setters and getters ---
    /**
     * @brief getInputPort is method returning input Port at specific index.
     * @param index Index specifying required port.
     * @return Input Port at specific index.
     */
    public Port getInputPort(int index) 
    {
        return this.inputPorts[index];
    }  

    /**
     * @brief getOutputPort is method returning output Port at specific index.
     * @param index Index specifying required port.
     * @return Output Port at specific index.
     */    
    public Port getOutputPort(int index) 
    {
        return this.outputPorts[index];
    }   

    /**
     * @brief getInputPorts is method returning array of all input Ports of this Block.
     * @return Array of all input Ports of this Block.
     */ 
    public Port[] getInputPorts() 
    {
        return this.inputPorts;
    }

    /**
     * @brief getOutputPorts is method returning array of all output Ports of this Block.
     * @return Array of all output Ports of this Block.
     */     
    public Port[] getOutputPorts() 
    {
            return this.outputPorts;
    }
    
    /**
     * @brief wasExecuted is method returning bool value indicating if the block was already executed.
     * @return True if block was already executed, false if not
     */
    public boolean wasExecuted() 
    {
        return this.executed;
    }

    /**
     * @brief printPorts is method used by GUI to print the block's Ports and it's values.
     * @return String containing Ports informations formated as e.g. Input: \n [float] 42 \n\n Output: \n [float] 12
     */
    public String printPorts()
    {
        String result = "Input:\n";
        for(Port port : this.getInputPorts())
        {
            result.concat(port.printConnection()+"\n");
        }

        result.concat("\nOutput:\n");
        for(Port port : this.getOutputPorts())
        {
            result.concat(port.printConnection()+"\n");
        }

        return result.trim();    // Remove last \n
    }
}
