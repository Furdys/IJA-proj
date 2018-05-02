package ija.proj.scheme;

import ija.proj.block.*;
import java.util.ArrayList;

public class Scheme
{
    private String name;
    private ArrayList<Block> blocks = new ArrayList<Block>();
    private ArrayList<Block> notExecutedBlocks = new ArrayList<Block>();
    
    /*
    private boolean executeBlock(Block block)
    {
        // --- Check for null block ---
        if(block == null)
        {
            System.err.println("ERROR: Tried to execute null block");
            return false;        
        }
        
        block = this.findNonDependentBlock(block);

        // --- Check for loops --- 
        if(block.wasExecuted() == true) // @todo This should be in Block class
        {
            System.err.println("ERROR: Loop detected (Tried to execute one block twice)!");
            return false;
        }
        
        // --- Execute calculation ---
        block.execute();
        
        return true;
    }    
    */
    public boolean addBlock(Block block)
    {
        // --- Check for null block ---
        if(block == null)
        {
            System.err.println("Schene.addBlock(): Tried to add null block");
            return false;     
        }
        
        // --- Check for duplicity ---
        if(this.blocks.contains(block))
        {
            System.err.println("Schene.addBlock(): Scheme already contains this block");
            return false;     
        }
        
        this.blocks.add(block);
        
        return true;
    }
    
    public void run()
    {
        this.notExecutedBlocks = new ArrayList<Block>(this.blocks);
        
        Block expectedNextBlock = null;
        
        while(!this.notExecutedBlocks.isEmpty())
        {
            if(expectedNextBlock == null)
                expectedNextBlock = this.notExecutedBlocks.get(0);
            
            expectedNextBlock = this.step(expectedNextBlock);
        }
    }
    
    private Block step(Block expectedNextBlock)
    {
        // --- Find block that can be executed ---
        Block realNextBlock = findNonDependentBlock(expectedNextBlock);
        
        // --- Execute block ---
        realNextBlock.execute();
        this.notExecutedBlocks.remove(realNextBlock);
        
        // --- Get expected next block ---
        if(realNextBlock.getOutputPorts().length != 0)  // Executed block has output port
        {
            if(realNextBlock.getOutputPort(0).getConnectedPort() != null)   // Output port is connected to other port
                return realNextBlock.getOutputPort(0).getConnectedPort().getOwnerBlock();   // Returns owner of connected port
        }
        
        // --- Expected next block not found ---
        return null;
    }
    
    private void searchUserDependentBlocks()
    {
        for(Block block : this.blocks)  // For every block in this scheme
        {
            for(Port port : block.getInputPorts())    // For every input port
            {
               if(port.getConnectedPort() == null)    // Port is not connected
               {
                   for(String name : port.getNames())   // For every value type
                   {
                       if(Double.isNaN(port.getValue(name)))    // If value not set
                       {
                           // @todo user input dialog
                           System.err.println("Scheme.searchUserDependentBlocks: @todo user input dialog");
                           port.setValue(name, 420);
                       }                        
                   }
               }
            }      
        }
    }
    
    
    public Block findNonDependentBlock(Block block)
    {
        // --- Check if this block can be executed ---
        for(Port port : block.getInputPorts())    // For every input port
        {
            Port connectedPort = port.getConnectedPort();   // Output port of previous block
            
            // --- Check if port is not connected ---
            if(connectedPort == null)
            {
                if(port.hasNaNValue())
                {
                    // This should never happen, user must fill every non-connected port before start
                    System.err.println("Scheme.findNonDependentBlock: Block is dependent on user input");
                    //System.exit(1);   
                }
                else
                    continue;
            }
            else
            {
                // --- Check for block with no value ---
                if(connectedPort.getOwnerBlock().wasExecuted() == false)
                {
                    // That means this block is waiting on connectedPort's block calculation

                    //this.dependentBlocks.add(block); @ todo Save visited blocks to reduce redundant search

                    return this.findNonDependentBlock(connectedPort.getOwnerBlock()); // Use recursive call (ask if inputPort's block is depended)
                }
            }
        }
        
        // --- Block is not depended & can be executed ---
        return block;
    }
    
    
}
