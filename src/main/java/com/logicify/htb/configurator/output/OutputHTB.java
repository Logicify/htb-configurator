package com.logicify.htb.configurator.output;

import com.logicify.htb.configurator.htb.HTBClass;
import com.logicify.htb.configurator.htb.HTBException;

/**
 * This is interface that different classes use for HTB class Output
 */
public interface OutputHTB {
    public void write(HTBClass htb) throws HTBException;
}
