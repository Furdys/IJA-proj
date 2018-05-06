/**
 * Backend representation of "Sub" block.
 * @brief Package for Block type "Sub" from group "Arithmetic"
 * @file BlockSub.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;


/**
 * @brief The BlockSub class is Block substracting two float numbers
 */
public class BlockSub extends BlockArithmetic 
{
    @Override
    void executeSpecific()  /// @brief executeSpecific substracts two float values from input Ports and saves it to output Port
    {
        this.getOutputPort(0).setValue("float", this.getInputPort(0).getValue("float") - this.getInputPort(1).getValue("float"));
    }
}