/**
 * Backend representation of logic blocks group.
 * @brief Package for Block classes from group "Logic"
 * @file BlockComplex.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;


/**
 * @brief The BlockLogic class is abstract class containing constructor for Block classes from group "Logic".
 */
public abstract class BlockLogic extends Block
{
    public BlockLogic()  /// Constructor for Block classes from group "Logic".
    {        
        super(new Port[]{new Port("bool", Port.inputType), new Port("bool", Port.inputType)}, new Port("bool", Port.outputType));
    }  
    
    // --- Constants ---
    public static final Double True = 1.0;  // @brieg Class constant representing true boolean with double value
    public static final Double False = 0.0; // @brieg Class constant representing false boolean with double value
    
    // --- Class methods ---
    /**
    * @brief double2bool is class method converting double value to boolean value.
    * @param value Double to be converted.
    * @return Converted boolean value.
    */
    public static boolean double2bool(double value)
    {
        if(value > (BlockLogic.True - BlockLogic.False)/2.0)
            return true;
        else
            return false;
    }
    
    /**
    * @brief bool2double is class method converting boolean value to double value.
    * @param value Boolean to be converted.
    * @return Converted double value.
    */
    public static  double bool2double(boolean value)
    {
        if(value)
            return BlockLogic.True;
        else
            return BlockLogic.False;
    }
}
