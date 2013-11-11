/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */

package org.andrey_bayev.htb_configurator.input;

import org.andrey_bayev.htb_configurator.htb.*;
import org.andrey_bayev.htb_configurator.htb.filters.Mark;
import org.andrey_bayev.htb_configurator.htb.filters.Realm;
import org.andrey_bayev.htb_configurator.htb.filters.Rule;
import org.andrey_bayev.htb_configurator.htb.leaf.FIFOParams;
import org.andrey_bayev.htb_configurator.htb.leaf.Leaf;
import org.andrey_bayev.htb_configurator.htb.leaf.SFQParams;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class InputFromFile implements InputHTB
{
    private File fileOfInput;

    public InputFromFile()
    {
        this.fileOfInput = null;
    }

    public InputFromFile(File file)
    {
        this.fileOfInput = file;
    }

    public InputFromFile(String filename)
    {
        this.fileOfInput = new File(filename);
    }

    public void setFile(File file)
    {
        this.fileOfInput = file;
    }


    /**
     * In this method I am building the HTBClass from HTB file
     *
     * @return
     */
    @Override
    public HTBClass read()
    {

        HTBClass htb = new HTBClass();
        try(BufferedReader input=new BufferedReader(new InputStreamReader(new FileInputStream(fileOfInput)))){

        //I am defining values I need to create HTB class
        htb.setComments(new HashMap<String, String>());
        htb.setToFile(new HashMap<String, Boolean>());
        htb.setRules(new LinkedList<Rule>());
        htb.setRealms(new LinkedList<Realm>());
        htb.setMarks(new LinkedList<Mark>());
        htb.setTimeRanges(new LinkedList<TimeRange>());
        HashMap<String, String> values = new HashMap<String, String>();

        if (!fileOfInput.getAbsolutePath().contains("."))
        {
            htb.setRoot(true);
        }
        htb.setFileName(fileOfInput.getAbsolutePath());

        //I am reading data from file
        String str = null;
        String comment = null;
        String key = null;
        String value = null;
            str = input.readLine();
            if (str.length() != 0 && str.charAt(0) == '#')
            {
                htb.getComments().put("HTB", str.substring(1));//Main comment to HTB file
                str = input.readLine();
            }

            do
            {
                if (str.length() != 0 && str.charAt(0) == '#')
                {
                    comment = str.substring(1);      //Comment to some key
                } else
                {
                    if (str.contains("="))
                    {
                        String strs[] = str.split("=");
                        key = strs[0];
                        value = strs[1];
                        htb.getToFile().put(key, true);

                        if (key.equals("RULE"))
                        {
                            if (value.contains(","))
                            {
                                strs = value.split(",");
                                if (strs.length == 2)
                                {
                                    htb.getRules().add(new Rule(strs[0], strs[1], comment));
                                } else
                                {
                                    htb.getRules().add(new Rule(strs[0], null, comment));
                                }
                            } else htb.getRules().add(new Rule(null, value, comment));


                        } else if (key.equals("REALM"))
                        {
                            if (value.contains(","))
                            {
                                strs = value.split(",");
                                if (strs.length == 2)
                                {
                                    htb.getRealms().add(new Realm(strs[0], strs[1], comment));
                                } else
                                {
                                    htb.getRealms().add(new Realm(strs[0], null, comment));
                                }
                            } else htb.getRealms().add(new Realm(null, value, comment));

                        } else if (key.equals("MARK"))
                        {
                            htb.getMarks().add(new Mark(value, comment));

                        } else if (key.equals("TIME"))
                        {
                            htb.getTimeRanges().add(new TimeRange(value, comment));

                        } else
                        {
                            if (comment != null)
                            {
                                htb.getComments().put(key, comment);
                            }
                            values.put(key, value);

                        }
                        comment = null;
                    }
                }
            } while ((str = input.readLine()) != null);
            if (htb.isRoot())
            {
                htb.setRootParams(new RootParams());
                if (htb.checkIfTrue("DEFAULT"))
                    htb.getRootParams().setDefaultID(Integer.parseInt(values.get("DEFAULT")));
                if (htb.checkIfTrue("R2Q")) htb.getRootParams().setR2q(Integer.parseInt(values.get("R2Q")));
                if (htb.checkIfTrue("DCACHE"))
                    htb.getRootParams().setDchache(values.get("DCACHE").equals("yes") ? true : false);
            }
            if (htb.checkIfTrue("RATE"))
            {
                htb.setRate(new Bandwidth(values.get("RATE")));
            }
            if (htb.checkIfTrue("CEIL"))
            {
                htb.setCeil(new Bandwidth(values.get("CEIL")));
            }
            if (htb.checkIfTrue("BURST"))
            {
                htb.setBurst(Transformations.fromStringToSpeedInBytes(values.get("BURST")));
            }
            if (htb.checkIfTrue("CBURST"))
            {
                htb.setBurst(Transformations.fromStringToSpeedInBytes(values.get("CBURST")));
            }
            if (htb.checkIfTrue("PRIO"))
            {
                htb.setPrio(Integer.parseInt(values.get("PRIO")));
            }
            if (htb.checkIfTrue("LEAF"))
            {
                String lf = values.get("LEAF");
                switch (lf)
                {
                    case "sfq":
                        htb.setLeaf(Leaf.SFQ);
                        break;
                    case "pfifo":
                        htb.setLeaf(Leaf.PFIFO);
                        break;
                    case "bfifo":
                        htb.setLeaf(Leaf.BFIFO);
                        break;
                    default:
                        htb.setLeaf(Leaf.NONE);
                        break;
                }
            }
            if (htb.checkIfTrue("MTU"))
            {
                htb.setMtu(Transformations.fromStringToSpeedInBytes(values.get("MTU")));
            }
            if (htb.checkIfTrue("LEAF"))
            {
                switch (htb.getLeaf())
                {
                    case SFQ:
                    {
                        htb.setSfq(new SFQParams());
                        if (htb.checkIfTrue("QUANTUM"))
                        {
                            htb.getSfq().setQuantum(Transformations.fromStringToSpeedInBytes(values.get("QUANTUM")));
                        }
                        if (htb.checkIfTrue("PERTURB"))
                        {
                            htb.getSfq().setPerturb(Integer.parseInt(values.get("PERTURB")));
                        }
                    }
                    case PFIFO:
                    {
                        htb.setPfifo(new FIFOParams());
                        if (htb.checkIfTrue("LIMIT"))
                        {
                            htb.getPfifo().setLimit(Transformations.fromStringToSpeedInBytes(values.get("LIMIT")));
                        }
                    }
                    case BFIFO:
                    {
                        htb.setBfifo(new FIFOParams());
                        if (htb.checkIfTrue("LIMIT"))
                        {
                            htb.getBfifo().setLimit(Transformations.fromStringToSpeedInBytes(values.get("LIMIT")));

                        }
                    }
                }
            }

            input.close();
        } catch (Exception e)
        {
            System.out.println("Error: "+e);
            System.out.println("Caused: "+e.getCause());
        }


        return htb;
    }
}
