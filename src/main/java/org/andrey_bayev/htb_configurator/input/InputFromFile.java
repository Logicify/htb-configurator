/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */

package org.andrey_bayev.htb_configurator.input;
import org.andrey_bayev.htb_configurator.htb.HTBClass;
import org.andrey_bayev.htb_configurator.htb.TimeRange;
import org.andrey_bayev.htb_configurator.htb.filters.Address;
import org.andrey_bayev.htb_configurator.htb.filters.Mark;
import org.andrey_bayev.htb_configurator.htb.filters.Realm;
import org.andrey_bayev.htb_configurator.htb.filters.Rule;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class InputFromFile implements InputHTB{
    private BufferedReader input;
    private HTBClass htb;
    private File fileOfInput;

    public InputFromFile(File file) throws FileNotFoundException{
        this.fileOfInput=file;
        this.input=new BufferedReader(new InputStreamReader(new FileInputStream(fileOfInput)));
    }

    public InputFromFile(String filename) throws FileNotFoundException {
        this.fileOfInput=new File(filename);
        this.input=this.input=new BufferedReader(new InputStreamReader(new FileInputStream(fileOfInput)));
    }

    @Override
    public HTBClass read() {
        //I am defining values I need to create HTB class
        boolean isRoot=false;
        HashMap<String,String> values=new HashMap<String,String>();
        HashMap<String,Boolean> useValues=new HashMap<String,Boolean>();
        HashMap<String,String> comments=new HashMap<String,String>();
        LinkedList<Rule> rules=new LinkedList<Rule>();
        LinkedList<Realm> realms=new LinkedList<Realm>();
        LinkedList<Mark> marks=new LinkedList<Mark>();
        LinkedList<TimeRange> timeRanges=new LinkedList<TimeRange>();
        if (!fileOfInput.getAbsolutePath().contains(".")) isRoot=true;
        values.put("FILENAME",fileOfInput.getAbsolutePath());
        //I am reading data from file
        String str=null;
        String comment=null;
        String key=null;
        String value=null;
        try{
            str=input.readLine();
            if (str.length()!=0 && str.charAt(0)=='#'){
                comments.put("HTB",str.substring(1));//Main comment to HTB file
                str=input.readLine();
            }

            do{
                if (str.length()!=0 && str.charAt(0)=='#'){
                    comment=str.substring(1);      //Comment to some key
                }
                else{
                    if(str.contains("=")){
                        String strs[]=str.split("=");
                        key=strs[0];
                        value=strs[1];
                        useValues.put(key,true);

                        if(key.equals("RULE")){
                            if(value.contains(",")){
                                strs=value.split(",");
                                if(strs.length==2){
                                rules.add(new Rule(strs[0],strs[1],comment));
                                }else{
                                    rules.add(new Rule(strs[0],null,comment));
                                }
                            }
                            else rules.add(new Rule(null,value,comment));


                        }else if(key.equals("REALM")){
                            if (value.contains(",")){
                                strs=value.split(",");
                                if(strs.length==2){
                                    realms.add(new Realm(strs[0],strs[1],comment));
                                }else{
                                    realms.add(new Realm(strs[0],null,comment));
                                }
                            }
                            else realms.add(new Realm(null,value,comment));

                        }else if(key.equals("MARK")){
                            marks.add(new Mark(value,comment));

                        }else if(key.equals("TIME")){
                            timeRanges.add(new TimeRange(value,comment));

                        }else{
                            if(comment!=null){
                                comments.put(key,comment);
                            }
                                values.put(key,value);

                        }
                        comment=null;
                    }
                }
            }while((str=input.readLine())!=null);

            input.close();

            htb=new HTBClass(isRoot,values,useValues,comments,rules,realms,marks,timeRanges);
        }
        catch(IOException e){


        }



        return htb;
    }
}
