/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb.filters;

//this class keeps address for Rule class
public class Address{
    private String ip;
    private int ipMask;
    private int port;
    private int portMask;


    public Address(String address){
        String parts[]=address.split(":");
        if(parts[0]!="")
        {
            String ipParts[]=parts[0].split("/");
            ip=ipParts[0];
            if(ipParts.length==2){
                if ((ipParts[1].charAt(1)=='x') || (ipParts[1].charAt(1)=='X'))
                {
                    ipMask=Integer.parseInt(ipParts[1].substring(2),16);
                }
                else
                {
                    ipMask=Integer.parseInt(ipParts[1]);
                }
            }
            else{
                ipMask=0;
            }
        }
        else
        {
            ip="";
            ipMask=0;
        }
        if(parts.length==2)
        {
            String portParts[]=parts[1].split("/");
            port=Integer.parseInt(portParts[0]);
            if(portParts.length==2){
                if ((portParts[1].charAt(1)=='x') || (portParts[1].charAt(1)=='X'))
                {
                    portMask=Integer.parseInt(portParts[1].substring(2),16);
                }
                else
                {
                    portMask=Integer.parseInt(portParts[1]);
                }
            }else
            {
                portMask=0;
            }
        }else
        {
            port=0;
            portMask=0;
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPortMask() {
        return portMask;
    }

    public void setPortMask(int portMask) {
        this.portMask = portMask;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getIpMask() {
        return ipMask;
    }

    public void setIpMask(int ipMask) {
        this.ipMask = ipMask;
    }

    public String toString()
    {
        String address;
        address=ip;
        if(ipMask!=0) address=address+'/'+ipMask;
        if(port!=0)
        {
            address=address+':'+port;
            if(portMask!=0) address=address+'/'+portMask;
        }
        return address;
    }


}
