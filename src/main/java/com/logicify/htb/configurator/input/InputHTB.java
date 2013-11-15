package com.logicify.htb.configurator.input;

import com.logicify.htb.configurator.htb.HTBClass;
import com.logicify.htb.configurator.htb.HTBException;

/**
 * This is interface that different classes use for HTB class Input
 */
public interface InputHTB {
    public HTBClass read() throws HTBException;
}
