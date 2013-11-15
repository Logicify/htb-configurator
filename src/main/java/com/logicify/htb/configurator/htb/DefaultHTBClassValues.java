package com.logicify.htb.configurator.htb;

/**
 * <h1>DefaultHTBClassValues</h1>
 * <p>This class keeps important constants that all HTB class' library uses </p>
 */
public class DefaultHTBClassValues {
    public static final int DEFAULT_LIMIT_SPEED = 1000; //default limits speed
    public static final Unit DEFAULT_SPEED_UNIT = Unit.BPS; //default limits unit
    public static final int DEFAULT_QUANTUM_SPEED = 1600; //quantum speed mustn't be less than MTU the default value of MTU is 1600
    public static final int DEFAULT_PERTURB = 10; //default perturb parameter
    public static final int DEFAULT_ROOT_ID = 0; //default ID of the root HTB class
    public static final int DEFAULT_R2Q = 10; //default r2q parameter of root HTB class
}
