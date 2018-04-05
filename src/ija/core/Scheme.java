/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.core;

import blocks.*;

import java.util.ArrayList;

/**
 *
 * @author furdys
 */
public class Scheme {
    private String name;
    private ArrayList<Block> blocks = new ArrayList<Block>();
//    private ArrayList<Block> dependentBlocks = new ArrayList<Block>();
    private Block nextBlock;
    
    private final int maxBlocks = 420;    // @todo Change later
    
    public void start(boolean step)
    {
        Block startingBlock = null;
        
        // --- Iterate every block ---
        for(int i = 0; i < this.blocks.size(); i++)
        {
            Block currentBlock = this.blocks.get(i);
            
            /* !!! DRAFT !!!
            ArrayList<Port> inputPorts = currentBlock.getInputPorts();
            int inputPortsCount = inputPorts.size();
            int nullPortsCount = 0;
            
            // --- Iterate every input port ---
            for(int i = 0; i < inputPortsCount; i++)
            {
                Port currentPort = inputPorts.get(i);
                
                if(currentPort == NULL)
                {
                    double enteredValue = this.getUserInput(); // Show window to enter value
                    currentPort = new PseudoConnection(enteredValue);   // Set new value
            
                    nullPortsCount++;
                }
            }
            
            // --- Check if suitable for starting block ---
            if(startingBlock == NULL)
            {
                if(inputPortsCount == nullPortsCount)
                    startingBlock = currentBlock;
            }
            */
        }
        
        // --- Execute first step ---
        this.executeBlock(startingBlock);
    }
    
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
    
    private Block findNonDependentBlock(Block block)
    {
        // --- Check if block can be executed ---
        for(Port port : block.getInputPorts())    // Check every input port
        {
            Port inputPort = port.getConnectedPort();
            
            // --- Check if port is not connected ---
            if(inputPort == null)
            {
                // This should never happen, user must fill every non-connected port before start
                System.err.println("Scheme.checkDependency: Block is dependent on user input");
                System.exit(1);
            }
            
            // --- Check for block with no value ---
            if(inputPort.getOwnerBlock().wasExecuted() == false)
            {
                // That means this block is waiting on inputPort's block calculation
                //this.dependentBlocks.add(block); @ todo Save visited blocks to reduce redundant search
                return this.findNonDependentBlock(inputPort.getOwnerBlock()); // Use recursive call (ask if inputPort's block is depended)
            }
        }
        
        // --- Block is not depended & can be executed ---
        return block;
    }
    
    
    private double getUserInput()
    {
        return 420;
    }
    
    
    
    public boolean setName(String name)
    {
        this.name = name;
        return true;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public boolean addBlock(Block block)
    {
        if(blocks.contains(block) == true)
        {
            System.err.println("Scheme.addBlock: Block is already added");
            return false;
        }
        
        blocks.add(block);
        
        return true;
    }
}
