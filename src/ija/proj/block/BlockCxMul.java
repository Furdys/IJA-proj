/**
 * Backend representation of "CxMul" block.
 * @brief Package for Block type "CxMul" from group "Complex arithmetic"
 * @file BlockCxMul.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;

public class BlockCxMul extends BlockComplex
{
    void executeSpecific()  /// @brief executeSpecific mutliplies two complex values from input Ports and saves it to output Port
    {
        Double x_re = this.getInputPort(0).getValue("real");
        Double x_im = this.getInputPort(0).getValue("imaginary");
        Double y_re = this.getInputPort(1).getValue("real");
        Double y_im = this.getInputPort(1).getValue("imaginary");
        
        this.getOutputPort(0).setValue("real",
                (x_re * y_re) - (x_im * y_im));
        this.getOutputPort(0).setValue("imaginary",
                (x_re * y_im) + (y_re * x_im));
    }
}


