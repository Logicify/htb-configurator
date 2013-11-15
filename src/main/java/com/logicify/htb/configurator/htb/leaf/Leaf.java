package com.logicify.htb.configurator.htb.leaf;

/**
 * <h1>Leaf enumeration:</h1>
 * <p>This enum specifies leaf queueing discipline to HTB
 * class. By default, no leaf qdisc is used.<p/>
 * <ul>
 * <li><b>NONE<b>-we don't use queueing discipline</li>
 * <li><b>SFQ</b>-we use SFQ queuing discipline</li>
 * <li><b>PFIFO</b>-we use PFIFO queuing discipline</li>
 * <li><b>BFIFO</b>-we use BFIFO queuing discipline</li>
 * <ul/>
 */
public enum Leaf {
    NONE,
    SFQ,
    PFIFO,
    BFIFO
}
