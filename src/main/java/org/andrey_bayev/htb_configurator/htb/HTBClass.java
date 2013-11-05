/**
 * Created with IntelliJ IDEA.
 * User: andrey_bayev
 * Date: 10/26/13
 * Time: 10:36 AM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb;

import org.andrey_bayev.htb_configurator.htb.filters.Mark;
import org.andrey_bayev.htb_configurator.htb.filters.Realm;
import org.andrey_bayev.htb_configurator.htb.filters.Rule;
import org.andrey_bayev.htb_configurator.htb.leaf.FIFOParams;
import org.andrey_bayev.htb_configurator.htb.leaf.Leaf;
import org.andrey_bayev.htb_configurator.htb.leaf.SFQParams;

import java.util.HashMap;
import java.util.LinkedList;


public class HTBClass
{
    private String fileName;//name of file where HTB class will be keeped

    private boolean root; //is HTB class root or not


    public RootParams rootParams; //keeps params for root class if it is root


    private Leaf leaf; //specified leaf queueing discipline


    public SFQParams sfq;//keeps sfq parametrs if LEAF=sfq


    public FIFOParams pfifo;//keeps pfifo params if LEAF=pfifo


    public FIFOParams bfifo;//keeps bfifo params if LEAF=bfifo


    private int prio;//priority of class traffic


    public Bandwidth rate;//Bandwidth allocated to the class


    public Bandwidth ceil;//The maximum bandwidth that can be used by the class.


    private long burst;/*control the amount of data that can
    be sent from one class at maximum (hardware) speed before trying
    to service other class*/


    private long cburst;/*control the amount of data that can
    be sent from one class at maximum (hardware) speed before trying
    to service other class*/


    private long mtu;//Maximum packet size HTB creates rate maps for


    private LinkedList<Rule> rules;//parameters make up "u32" filter rules that select traffic for each of the classes


    private LinkedList<Realm> realms;//These parameters make up "route" filter rules that classify traffic according to packet source/destination realms


    private LinkedList<Mark> marks;//These parameters make up "fw" filter rules that select traffic for each of the classes accoring to firewall "mark"


    private LinkedList<TimeRange> timeRanges;//This parameter allows you to change class bandwidth during the day or week


    private HashMap<String,Boolean>toFile;//Keeps what parameters you need to write into file

    private HashMap<String,String>comments;//keeps the comments to the parameters


    public HTBClass(){}


    public HTBClass(boolean isRoot,HashMap<String,String> values,HashMap<String,Boolean>useValues,HashMap<String,String>comments,LinkedList<Rule> rules,LinkedList<Realm> realms,LinkedList<Mark> marks,LinkedList<TimeRange> timeRanges){
        this.root=isRoot;
        this.comments=comments;
        toFile=useValues;
        fileName=values.get("FILENAME");
        if(this.root)
        {
            this.rootParams=new RootParams();
            if(checkIfTrue("DEFAULT")) this.rootParams.setDefaultID(Integer.parseInt(values.get("DEFAULT")));
            if(checkIfTrue("R2Q")) this.rootParams.setR2q(Integer.parseInt(values.get("R2Q")));
            if(checkIfTrue("DCACHE")) this.rootParams.setDchache(values.get("DCACHE").equals("yes") ? true : false);
        }
        if(checkIfTrue("RATE")){
            rate=new Bandwidth(values.get("RATE"));
        }
        if(checkIfTrue("CEIL")){
            ceil=new Bandwidth(values.get("CEIL"));
        }
        if(checkIfTrue("BURST")){
            this.burst=Transformations.fromBytesToLong(values.get("BURST"));
        }
        if(checkIfTrue("CBURST")){
            this.burst=Transformations.fromBytesToLong(values.get("CBURST"));
        }
        if(checkIfTrue("PRIO")){
            this.setPrio(Integer.parseInt(values.get("PRIO")));
        }
        if(checkIfTrue("LEAF")){
            String lf=values.get("LEAF");
            switch(lf)
            {
                case "sfq": this.setLeaf(Leaf.SFQ); break;
                case "pfifo": this.setLeaf(Leaf.PFIFO); break;
                case "bfifo": this.setLeaf(Leaf.BFIFO); break;
                default: this.setLeaf(Leaf.NONE); break;
            }
        }
        if(checkIfTrue("MTU")){
            this.mtu=Transformations.fromBytesToLong(values.get("MTU"));
        }
        if(checkIfTrue("LEAF")){
        switch(this.getLeaf())
        {
            case SFQ:
            {
                this.sfq=new SFQParams();
                if(checkIfTrue("QUANTUM")){
                    this.getSfq().setQuantum(Transformations.fromBytesToLong(values.get("QUANTUM")));
                }
                if(checkIfTrue("PERTURB")){
                    this.getSfq().setPerturb(Integer.parseInt(values.get("PERTURB")));
                }
            }
            case PFIFO:
            {
                this.pfifo=new FIFOParams();
                if(checkIfTrue("LIMIT")){
                    if (values.get("LIMIT").charAt(values.get("LIMIT").length() - 1)=='b'){
                        this.getPfifo().setPackets(false);
                        this.getPfifo().setLimit(Transformations.fromBytesToLong(values.get("LIMIT")));
                    }
                    else{
                        this.getPfifo().setPackets(true);
                        this.getPfifo().setLimit(Long.parseLong(values.get("LIMIT")));
                    }
                }
            }
            case BFIFO:
            {
                this.bfifo=new FIFOParams();
                if(checkIfTrue("LIMIT")){
                    if (values.get("LIMIT").charAt(values.get("LIMIT").length()-1)=='b'){
                        this.getBfifo().setPackets(false);
                        this.getBfifo().setLimit(Transformations.fromBytesToLong(values.get("LIMIT")));
                    }
                    else{
                        this.getBfifo().setPackets(true);
                        this.getBfifo().setLimit(Long.parseLong(values.get("LIMIT")));
                    }
                }
            }
        }
        }
        if(checkIfTrue("RULE")) {
            this.rules=rules;
        }
        if(checkIfTrue("REALM")){
            this.realms=realms;
        }
        if(checkIfTrue("MARK")) {
            this.marks=marks;
        }
        if(checkIfTrue("TIME")) {
            this.timeRanges=timeRanges;
        }
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

    public FIFOParams getPfifo() {
        return pfifo;
    }

    public void setPfifo(FIFOParams pfifo) {
        this.pfifo = pfifo;
    }

    public FIFOParams getBfifo() {
        return bfifo;
    }

    public void setBfifo(FIFOParams bfifo) {
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

    public long getBurst() {
        return burst;
    }

    public void setBurst(long burst) {
        this.burst = burst;
    }

    public long getCburst() {
        return cburst;
    }

    public void setCburst(long cburst) {
        this.cburst = cburst;
    }

    public long getMtu() {
        return mtu;
    }

    public void setMtu(long mtu) {
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

    public HashMap<String, Boolean> getToFile() {
        return toFile;
    }

    public void setToFile(HashMap<String, Boolean> toFile) {
        this.toFile = toFile;
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

    private boolean checkIfTrue(String value){
        if(toFile.get(value)!=null && toFile.get(value)) return true;
        else return false;
    }

}
