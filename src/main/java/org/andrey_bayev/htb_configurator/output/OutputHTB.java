/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.output;
import org.andrey_bayev.htb_configurator.htb.HTBClass;


public interface OutputHTB {
    public void write(HTBClass htb);
}
