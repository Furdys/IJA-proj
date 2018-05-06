/**
 * Backend representation of "CxSub" block.
 * @brief Package for Block type "CxSub" from group "Complex arithmetic"
 * @file BlockCxSub.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;


/**
 * @brief The BlockCxSub class is Block substracting two complex numbers
 */
public class BlockCxSub extends BlockComplex
{
    void executeSpecific()  /// @brief executeSpecific substracts two complex values from input Ports and saves it to output Port
    {
        for(String name : new String[]{"real","imaginary"})
        {
            this.getOutputPort(0).setValue(name,
                this.getInputPort(0).getValue(name) - this.getInputPort(1).getValue(name));
        }
    }
}

