/**
 * Backend representation of "Or" block.
 * @brief Package for Block type "Or" from group "Logic"
 * @file BlockOr.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;


/**
 * @brief The BlockOr class is Block realising logical disjuction of two bool values.
 */
public class BlockOr extends BlockLogic
{
    @Override
    void executeSpecific()  /// @brief executeSpecific realises logical disjuction of two bool values from input Ports and saves it to output Port
    {
        boolean resultB = BlockLogic.double2bool(getInputPort(0).getValue("bool")) | double2bool(getInputPort(1).getValue("bool"));
        double resultD = BlockLogic.bool2double(resultB);        
        
        this.getOutputPort(0).setValue("bool", resultD);
    }
}
