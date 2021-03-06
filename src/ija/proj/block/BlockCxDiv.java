/**
 * Backend representation of "CxDiv" block.
 * @brief Package for Block type "CxDiv" from group "Complex arithmetic"
 * @file BlockCxDiv.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;


/**
 * @brief The BlockCxDiv class is Block dividing two complex numbers
 */
public class BlockCxDiv extends BlockComplex
{
    void executeSpecific()  /// @brief executeSpecific divides two complex values from input Ports and saves it to output Port
    {
        Double x_re = this.getInputPort(0).getValue("real");
        Double x_im = this.getInputPort(0).getValue("imaginary");
        Double y_re = this.getInputPort(1).getValue("real");
        Double y_im = this.getInputPort(1).getValue("imaginary");
        
        this.getOutputPort(0).setValue("real",
                ((x_re * y_re) + (x_im * y_im)) / (Math.pow(y_re, 2) + Math.pow(y_im, 2)));
        this.getOutputPort(0).setValue("imaginary",
                (-(x_re * y_im) + (x_im * y_re)) / (Math.pow(y_re, 2) + Math.pow(y_im, 2)));
    }
}
