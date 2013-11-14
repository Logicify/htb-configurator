/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */

package com.logicify.htb.configurator.input;

import com.logicify.htb.configurator.htb.HTBClass;


public interface InputHTB
{
    public HTBClass read() throws Exception;
}
