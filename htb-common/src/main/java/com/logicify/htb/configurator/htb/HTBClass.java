package com.logicify.htb.configurator.htb;

import com.logicify.htb.configurator.htb.filters.Mark;
import com.logicify.htb.configurator.htb.filters.Realm;
import com.logicify.htb.configurator.htb.filters.Rule;
import com.logicify.htb.configurator.htb.leaf.FIFOParam;
import com.logicify.htb.configurator.htb.leaf.Leaf;
import com.logicify.htb.configurator.htb.leaf.SFQParams;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * <h1>HTBClass</h1>
 * <p>
 * This class represent HTB Class that is described in file. It has fields:
 * <ul>
 * <li><b>filename</b>-keeps name of file where class is kept</li>
 * <li><b>root</b>-detects if class is root or not</li>
 * <li><b>rootParams</b>-keeps params of root HTBClass if HTBClass is root</li>
 * <li><b>leaf</b>-keeps leaf queueing discipline</li>
 * <li><b>sfq</b>-keeps SFQ params</li>
 * <li><b>pfifo</b>-keeps PFIFO params</li>
 * <li><b>bfifo</b>-keeps BFIFO params</li>
 * <li><b>prio</b>-keeps priority of HTB class traffic</li>
 * <li><b>rate</b>-keeps RATE param</li>
 * <li><b>ceil</b>-keeps CEIL param</li>
 * <li><b>burst</b>-keeps BURST param</li>
 * <li><b>cburst</b>-keeps CBURST param</li>
 * <li><b>mtu</b>-keeps MTU param</li>
 * <li><b>rules</b>-keeps traffic rules</li>
 * <li><b>realms</b>-keeps traffic rules depending on the Internet realm</li>
 * <li><b>marks</b>-keeps firewall marks</li>
 * <li><b>timeRanges</b>-keeps bandwidth changes that are depend on different time periods</li>
 * <li><b>useOfValues</b>-keeps fields of HTB we need to use</li>
 * <li><b>comments</b>-keeps comments to different fields</li>
 * </ul>
 * </p>
 */
public class HTBClass {

    private String fileName;//name of file where HTB class will be keeped

    private boolean root; //is HTB class root or not

    private RootParams rootParams; //keeps params for root class if it is root

    private Leaf leaf; //specified leaf queueing discipline

    private SFQParams sfq;//keeps sfq parameters if LEAF=sfq

    private FIFOParam pfifo;//keeps pfifo params if LEAF=pfifo

    private FIFOParam bfifo;//keeps bfifo params if LEAF=bfifo

    private int prio;//priority of class traffic

    private Bandwidth rate;//Bandwidth allocated to the class

    private Bandwidth ceil;//The maximum bandwidth that can be used by the class.

    private SpeedInBytes burst;/*control the amount of data that can
    be sent from one class at maximum (hardware) speed before trying
    to service other class*/

    private SpeedInBytes cburst;/*control the amount of data that can
    be sent from one class at maximum (hardware) speed before trying
    to service other class*/

    private SpeedInBytes mtu;//Maximum packet size HTB creates rate maps for

    private LinkedList<Rule> rules;//parameters make up "u32" filter rules that select traffic for each of the classes

    private LinkedList<Realm> realms;//These parameters make up "route" filter rules that classify traffic according to packet source/destination realms

    private LinkedList<Mark> marks;//These parameters make up "fw" filter rules that select traffic for each of the classes accoring to firewall "mark"

    private LinkedList<TimeRange> timeRanges;//This parameter allows you to change class bandwidth during the day or week

    private HashMap<String, Boolean> useOfTheValues;//Keeps what parameters you need to write into file

    private HashMap<String, String> comments;//keeps the comments to the parameters

    public HTBClass() {
        burst = new SpeedInBytes();
        cburst = new SpeedInBytes();
        mtu = new SpeedInBytes();

    }

    //All getters and setters
    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public RootParams getRootParams() {
        return rootParams;
    }

    public void setRootParams(RootParams rootParams) {
        this.rootParams = rootParams;
    }

    public Leaf getLeaf() {
        return leaf;
    }

    public void setLeaf(Leaf leaf) {
        this.leaf = leaf;
    }

    public SFQParams getSfq() {
        return sfq;
    }

    public void setSfq(SFQParams sfq) {
        this.sfq = sfq;
    }

    public FIFOParam getPfifo() {
        return pfifo;
    }

    public void setPfifo(FIFOParam pfifo) {
        this.pfifo = pfifo;
    }

    public FIFOParam getBfifo() {
        return bfifo;
    }

    public void setBfifo(FIFOParam bfifo) {
        this.bfifo = bfifo;
    }

    public int getPrio() {
        return prio;
    }

    public void setPrio(int prio) {
        this.prio = prio;
    }

    public Bandwidth getRate() {
        return rate;
    }

    public void setRate(Bandwidth rate) {
        this.rate = rate;
    }

    public Bandwidth getCeil() {
        return ceil;
    }

    public void setCeil(Bandwidth ceil) {
        this.ceil = ceil;
    }

    public SpeedInBytes getBurst() {
        return burst;
    }

    public void setBurst(SpeedInBytes burst) {
        this.burst = burst;
    }

    public SpeedInBytes getCburst() {
        return cburst;
    }

    public void setCburst(SpeedInBytes cburst) {
        this.cburst = cburst;
    }

    public SpeedInBytes getMtu() {
        return mtu;
    }

    public void setMtu(SpeedInBytes mtu) {
        this.mtu = mtu;
    }

    public LinkedList<Rule> getRules() {
        return rules;
    }

    public void setRules(LinkedList<Rule> rules) {
        this.rules = rules;
    }

    public LinkedList<Realm> getRealms() {
        return realms;
    }

    public void setRealms(LinkedList<Realm> realms) {
        this.realms = realms;
    }

    public LinkedList<Mark> getMarks() {
        return marks;
    }

    public void setMarks(LinkedList<Mark> marks) {
        this.marks = marks;
    }

    public LinkedList<TimeRange> getTimeRanges() {
        return timeRanges;
    }

    public void setTimeRanges(LinkedList<TimeRange> timeRanges) {
        this.timeRanges = timeRanges;
    }

    public HashMap<String, Boolean> getUseOfTheValues() {
        return useOfTheValues;
    }

    public void setUseOfTheValues(HashMap<String, Boolean> useOfTheValues) {
        this.useOfTheValues = useOfTheValues;
    }

    public HashMap<String, String> getComments() {
        return comments;
    }

    public void setComments(HashMap<String, String> comments) {
        this.comments = comments;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //this method checks is we need to write the param to the file
    public boolean checkIfTrue(String value) {
        if (useOfTheValues.get(value) != null && useOfTheValues.get(value)) return true;
        else return false;
    }


}
