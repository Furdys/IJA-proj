/**
 * Backend representation of "Xor" block.
 * @brief Package for Block type "Xor" from group "Logic"
 * @file BlockXor.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;


/**
 * @brief The BlockXor class is Block realising exclusive logical disjuction of two bool values.
 */
public class BlockXor extends BlockLogic
{
    @Override
    void executeSpecific()  /// @brief executeSpecific realises exclusive logical disjuction of two bool values from input Ports and saves it to output Port
    {
        boolean resultB = BlockLogic.double2bool(getInputPort(0).getValue("bool")) ^ double2bool(getInputPort(1).getValue("bool"));
        double resultD = BlockLogic.bool2double(resultB);        
        
        this.getOutputPort(0).setValue("bool", resultD);
    }
}
