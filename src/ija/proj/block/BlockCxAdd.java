/**
 * Backend representation of "CxAdd" block.
 * @brief Package for Block type "CxAdd" from group "Complex arithmetic"
 * @file BlockCxAdd.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;


/**
 * @brief The BlockCxAdd class is Block adding two complex numbers
 */
public class BlockCxAdd extends BlockComplex
{
    void executeSpecific()  /// @brief executeSpecific adds two complex values from input Ports and saves it to output Port
    {
        for(String name : new String[]{"real","imaginary"})
        {
            this.getOutputPort(0).setValue(name,
                this.getInputPort(0).getValue(name) + this.getInputPort(1).getValue(name));
        }
    }
}

