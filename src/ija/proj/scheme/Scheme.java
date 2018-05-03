package ija.proj.scheme;

import ija.proj.block.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Scheme implements Serializable
{
    // --- Attributes ---
    private String name;
    private boolean readOnly;
    private boolean finished;
    private ArrayList<Block> blocks;
    private ArrayList<Block> notExecutedBlocks;
    private ArrayList<Block> loopDetectionTrace;
    private Block expectedNextBlock;
    
    // --- Constants ---
    public static final Block LoopDetected = new BlockAdd();    // Pseudo block beacuse fuck Java
    
    // --- Constructor ---
    public Scheme(String name)
    {
        this.name = name;
        this.readOnly = false;
        this.finished = false;
        this.blocks = new ArrayList<Block>();
        this.expectedNextBlock = null;
    }
    
    /**
     * @brief Adds block to scheme blocks list
     * @param block Block to be added
     * @return true if succesful, false if not
     */
    public boolean addBlock(Block block)
    {
        if(this.readOnly)
        {
            System.err.println("Schene.addBlock(): Scheme is read-only");
            return false;     
        }
        
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
    
    public boolean removeBlock(Block block)
    {
        if(this.readOnly)
        {
            System.err.println("Schene.removeBlock(): Scheme is read-only");
            return false;     
        }
        
        // --- Check for null block ---
        if(block == null)
        {
            System.err.println("Schene.removeBlock(): Tried to add null block");
            return false;     
        }
        
        // --- Check for existence ---
        if(!this.blocks.contains(block))
        {
            System.err.println("Schene.removeBlock(): Scheme doesn't contain this block");
            return false;     
        }
        
        this.blocks.remove(block);
        
        return true;
    }
    
    /**
     * @brief Runs the calucation of scheme
     */
    public boolean run()
    { 
        // --- Execution ---
        do
        {
            boolean runStepOK;
            runStepOK = this.runStep();
            
            if(!runStepOK)
                return false;
        }
        while(!this.finished);
        
        return true;
    }
    
    public boolean runStep()
    {
        // --- Already finished executing ---
        if(this.finished)
        {
            System.err.println("Schene.runStep(): Scheme already finished executing");
            return false;             
        }
 
        // --- Not yet finished executing ---
        // -- First step --
        if(!this.readOnly)  // Not in middle of executing (= this is first step)
        {
            boolean preRunOK = this.preRun(); // Make run prerequisites
            if(!preRunOK) 
                return false;   // Return false if preRun() failed
        } 
        
        // -- Other steps ---
        // - None block is expected to be next -
        if(this.expectedNextBlock == null)
            this.expectedNextBlock = this.notExecutedBlocks.get(0);  // Get first block in execution queqe

        // - Do one step -
        this.expectedNextBlock = this.step_internal(this.expectedNextBlock);

        // - Check loop detection -
        if(this.expectedNextBlock == Scheme.LoopDetected)
            return false;
        
        // - Check if finished -
        if(this.notExecutedBlocks.isEmpty())
            this.finished = true;     
        
        return true;
    }
    
    private boolean preRun()
    {
        // --- Check read only ---
        if(this.finished)
        {
            System.err.println("Schene.pre_run(): Can't run scheme twice");
            return false;     
        }
        this.readOnly = true;
        
        // --- Get user input to not connected ports ---
        this.searchUserDependentBlocks();
        
        // --- Copy blocks list ---
        this.notExecutedBlocks = new ArrayList<Block>(this.blocks);
        
        // --- Reset expected block variable ---
        this.expectedNextBlock = null;
        this.loopDetectionTrace = new ArrayList<Block>();
        
        return true;
    }
    
    /**
     * @brief Does one step of running scheme
     * @param expectedNextBlock Block that is expected to be executed in this step
     * @return Block that is expected to be executed in next step or null when no idea what should be executed next
     */
    private Block step_internal(Block expectedNextBlock)
    {
        // --- Find block that can be executed ---
        Block realNextBlock = findNonDependentBlock(expectedNextBlock);
        
        if(realNextBlock == Scheme.LoopDetected)
            return Scheme.LoopDetected;
        
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
    
    /**
     * @brief Check all blocks in scheme if they need user's input
     * @todo Input Dialog
     */
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

    /**
     * @brief Search first non-dependent block in specified blocks ancestors
     * @param block Block from where to start search
     * @todo Not sure what happens when there is nowhere to continue search
     * @return
     */      
    private Block findNonDependentBlock(Block block)
    {
        this.loopDetectionTrace.clear();
        
        // --- Actual search ---
        return this.findNonDependentBlock_private(block);
    }
    
    /**
     * @brief Search first non-dependent block in specified blocks ancestors
     * @param block Block from where to start search
     * @todo Not sure what happens when there is nowhere to continue search
     * @return
     * 
     * @warning DO NOT CALL THIS METHOD! Use Scheme.findNonDependentBlock() instead
     */    
    private Block findNonDependentBlock_private(Block block)
    {
        // --- Loop detection --- 
        if(this.loopDetectionTrace.contains(block)) 
            return Scheme.LoopDetected; 
        else 
            this.loopDetectionTrace.add(block);    
        
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
                    System.err.println("Scheme.findNonDependentBlock_private: Block is dependent on user input");
                    //System.exit(1);   
                }
                else
                    continue;
            }
            else    // Port is connected
            {
                // --- Check for block with no value ---
                if(connectedPort.getOwnerBlock().wasExecuted() == false)
                {
                    // That means this block is waiting on connectedPort's block calculation

                    //this.dependentBlocks.add(block); @ todo Save visited blocks to reduce redundant search

                    return this.findNonDependentBlock_private(connectedPort.getOwnerBlock()); // Use recursive call (ask if inputPort's block is depended)
                }
            }
        }
        
        // --- Block is not depended & can be executed ---
        return block;
    }
    
    public ArrayList<Block> getBlocks()
    {
        return this.blocks;
    }
}
