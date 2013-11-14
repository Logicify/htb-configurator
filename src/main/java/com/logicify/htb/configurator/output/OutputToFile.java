/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
package com.logicify.htb.configurator.output;

import com.logicify.htb.configurator.htb.HTBClass;
import com.logicify.htb.configurator.htb.TimeRange;
import com.logicify.htb.configurator.htb.Transformations;
import com.logicify.htb.configurator.htb.filters.Mark;
import com.logicify.htb.configurator.htb.filters.Realm;
import com.logicify.htb.configurator.htb.filters.Rule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class OutputToFile implements OutputHTB
{
    private File fileOfOutput;
    private HTBClass htb;
    private PrintWriter output;

    public OutputToFile()
    {
        this.fileOfOutput = null;
    }


    @Override
    public void write(HTBClass htbcl) throws Exception
    {
        htb = htbcl;
        fileOfOutput = new File(htb.getFileName());
        try (PrintWriter out = new PrintWriter(new FileOutputStream(fileOfOutput)))
        {
            output = out;

            writeComment("HTB");
            //checking if the file is root HTB class
            if (htb.isRoot())
            {
                //writing DEFAULT into file
                if (checkIfTrue("DEFAULT"))
                {
                    writeComment("DEFAULT");
                    output.println("DEFAULT=" + htb.getRootParams().getDefaultID());
                }
                //writing R2Q into file
                if (checkIfTrue("R2Q"))
                {
                    writeComment("R2Q");
                    output.println("R2Q=" + htb.getRootParams().getR2q());
                }
                //writing DCHACHE into file
                if (checkIfTrue("DCACHE"))
                {
                    writeComment("DCACHE");
                    if (htb.getRootParams().isDchache()) output.println("DCACHE=yes");
                    else output.println("DCACHE=no");
                }
            } else
            {
                //writing RATE into File
                if (checkIfTrue("RATE"))
                {
                    writeComment("RATE");
                    if (htb.getRate().isPrate())
                    {
                        output.println("RATE=prate");
                    } else
                    {
                        if (htb.getRate().isPceil())
                        {
                            output.println("RATE=pceil");
                        } else
                        {
                            output.println("RATE=" + htb.getRate().getSpeed() + Transformations.fromUnitToString(htb.getRate().getUnit()));
                        }
                    }
                }
                //writing CEIL into File
                if (checkIfTrue("CEIL"))
                {
                    writeComment("CEIL");
                    if (htb.getCeil().isPrate())
                    {
                        output.println("CEIL=prate");
                    } else
                    {
                        if (htb.getCeil().isPceil())
                        {
                            output.println("CEIL=pceil");
                        } else
                        {
                            output.println("CEIL=" + htb.getCeil().getSpeed() + Transformations.fromUnitToString(htb.getCeil().getUnit()));
                        }
                    }
                }
                //writing BURST into File
                if (checkIfTrue("BURST"))
                {
                    writeComment("BURST");
                    output.println("BURST=" + Transformations.fromSpeedInBytesToString(htb.getBurst()));
                }
                //writing CBURST into File
                if (checkIfTrue("CBURST"))
                {
                    writeComment("CBURST");
                    output.println("CBURST=" + Transformations.fromSpeedInBytesToString(htb.getCburst()));
                }
                //writing PRIO into File
                if (checkIfTrue("PRIO"))
                {
                    writeComment("PRIO");
                    output.println("PRIO=" + htb.getPrio());
                }
                //writing LEAF into File
                if (checkIfTrue("LEAF"))
                {
                    writeComment("LEAF");
                    switch (htb.getLeaf())
                    {
                        case SFQ:
                            output.println("LEAF=sfq");
                            break;
                        case PFIFO:
                            output.println("LEAF=pfifo");
                            break;
                        case BFIFO:
                            output.println("LEAF=bfifo");
                            break;
                        default:
                            output.println("LEAF=none");
                            break;
                    }
                }
                //writing MTU into File
                if (checkIfTrue("MTU"))
                {
                    writeComment("MTU");
                    output.println("MTU=" + Transformations.fromSpeedInBytesToString(htb.getMtu()));
                }
                //writing LEAF params into File
                if (checkIfTrue("LEAF"))
                {

                    switch (htb.getLeaf())
                    {
                        case SFQ:
                        {
                            //writing QUANTUM parameter of SFQ into File
                            if (checkIfTrue("QUANTUM"))
                            {
                                writeComment("QUANTUM");
                                output.println("QUANTUM=" + Transformations.fromSpeedInBytesToString(htb.getSfq().getQuantum()));
                            }
                            //writing PERTURB parameter of SFQ into File
                            if (checkIfTrue("PERTURB"))
                            {
                                writeComment("PERTURB");
                                output.println("PERTURB=" + htb.getSfq().getPerturb());
                            }
                        }
                        case PFIFO:
                        {
                            //writing LIMIT parameter of PFIFO
                            if (checkIfTrue("LIMIT"))
                            {
                                writeComment("LIMIT");
                                output.println("LIMIT=" + Transformations.fromSpeedInBytesToString(htb.getPfifo().getLimit()));
                            }

                        }
                        case BFIFO:
                        {
                            //writing LIMIT parameter of BFIFO
                            if (checkIfTrue("LIMIT"))
                            {
                                writeComment("LIMIT");
                                output.println("LIMIT=" + Transformations.fromSpeedInBytesToString(htb.getBfifo().getLimit()));
                            }
                        }
                    }
                    //writing filtering params into file
                    //writing RULES into file
                    if (checkIfTrue("RULE"))
                    {

                        for (Rule rule : htb.getRules())
                        {
                            if (rule.getComment() != null)
                            {
                                output.println('#' + rule.getComment());
                            }
                            output.print("RULE=");
                            if (rule.getSaddr() != null)
                            {
                                output.print(rule.getSaddr().toString() + ",");
                            }
                            if (rule.getDaddr() != null)
                            {
                                output.print(rule.getDaddr().toString());
                            }
                            output.println();
                        }

                    }
                    //writing REALMS into file
                    if (checkIfTrue("REALM"))
                    {

                        for (Realm realm : htb.getRealms())
                        {
                            if (realm.getComment() != null)
                            {
                                output.println('#' + realm.getComment());
                            }
                            output.print("REALM=");
                            if (realm.getSrealm() != null)
                            {
                                output.print(realm.getSrealm() + ",");
                            }
                            if (realm.getDrealm() != null)
                            {
                                output.print(realm.getDrealm());
                            }
                            output.println();
                        }

                    }
                    //writing MARKS into file
                    if (checkIfTrue("MARK"))
                    {
                        for (Mark mark : htb.getMarks())
                        {
                            if (mark.getComment() != null)
                            {
                                output.println('#' + mark.getComment());
                            }
                            output.println("MARK=" + mark.getFirewallRule());
                        }

                    }
                    //writing TIMES into file
                    if (checkIfTrue("TIME"))
                    {
                        for (TimeRange time : htb.getTimeRanges())
                        {
                            if (time.getComment() != null)
                            {
                                output.println('#' + time.getComment());
                            }
                            output.println("TIME=" + time.toString());
                        }

                    }
                }

            }
            output.flush();
            output.close();

        } catch (Exception e)
        {
            throw e;
        }

    }


    private void writeComment(String value)
    {
        if (htb.getComments().get(value) != null)
        {
            output.println("#" + htb.getComments().get(value));
        }
    }

    private boolean checkIfTrue(String value)
    {
        if (htb.getUseOfTheValues().get(value) != null && htb.getUseOfTheValues().get(value))
        {
            return true;
        } else
        {
            return false;
        }
    }

}
