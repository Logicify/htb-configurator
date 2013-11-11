/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/26/13
 * Time: 6:37 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb.leaf;

/**
 * Enum of specified leaf queueing discipline
 */
public enum Leaf
{
    NONE, //we haven't queueing disciplines
    SFQ, //SFQ queueing discipline
    PFIFO, //PFIFO queueing discipline
    BFIFO //BFIFO queueing discipline
}
