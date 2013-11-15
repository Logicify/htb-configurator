package com.logicify.htb.configurator.output;

import com.logicify.htb.configurator.htb.HTBClass;


public interface OutputHTB {
    public void write(HTBClass htb) throws Exception;
}
