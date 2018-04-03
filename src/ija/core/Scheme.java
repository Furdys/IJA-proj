/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.core;

import java.util.ArrayList;

/**
 *
 * @author furdys
 */
public class Scheme {
    private String name;
    private ArrayList<Block> blocks = new ArrayList<Block>();
    private ArrayList<Block> visitedBlocks = new ArrayList<Block>();
    private Block nextBlock;
    
    private int maxBlocks = 420;    // @todo Change later
    
    public void start(boolean step)
    {
        Block startingBlock;
        
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
        // --- Check for loops ---
        if(this.visitedBlocks.contains(block))
        {
            System.out.println("ERROR: Loop detected!");
            // @todo die
        }
        
        // @todo Check if parent blocks are calculated?
        
        // --- Execute calculation ---
        block.execute();
        
        visitedBlocks.add(block);
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
}
