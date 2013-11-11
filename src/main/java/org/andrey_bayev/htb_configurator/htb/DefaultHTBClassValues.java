package org.andrey_bayev.htb_configurator.htb;

/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 11/11/13
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultHTBClassValues {
    public static final int DEFAULT_LIMIT_SPEED=1000; //default limits speed
    public static final Unit DEFAULT_SPEED_UNIT=Unit.BPS; //default limits unit
    public static final int DEFAULT_QUANTUM_SPEED=1600; //quantum speed mustn't be less than MTU the default value of MTU is 1600
    public static final int DEFAULT_PERTURB=10; //default perturb parameter
    public static final int DEFAULT_ROOT_ID = 0; //default ID of the root HTB class
    public static final int DEFAULT_R2Q = 10; //default r2q parameter of root HTB class
}
