/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
package com.logicify.htb.configurator.htb.filters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//this class keeps address for Rule class
public class Address
{
    public static final String IP4_SOCKET_PATTERN = "^((((\\d{1,3})\\.){3}(\\d{1,3}))|(\\*))(\\/((0[xX][a-fA-F\\d]+)|(\\d+)))?(:(\\d{1,5})(\\/((0[xX][a-fA-F\\d]+)|(\\d+)))?)?$";
    public static final String IP4_ADDRESS_PATTERN = "^((([12]?\\d?\\d)\\.){3}([12]?\\d?\\d))|(\\*)$";
    public static final String HEX_NUMBER_PATTERN = "0x[a-fA-F\\d]+";

    private String ip;
    private int ipMask;
    private int port;
    private int portMask;

    public Address(String ip, int ipMask, int port, int portMask)
    {
        this.ip = ip;
        this.ipMask = ipMask;
        this.port = port;
        this.portMask = portMask;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public int getPortMask()
    {
        return portMask;
    }

    public void setPortMask(int portMask)
    {
        this.portMask = portMask;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public int getIpMask()
    {
        return ipMask;
    }

    public void setIpMask(int ipMask)
    {
        this.ipMask = ipMask;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (ipMask != address.ipMask) return false;
        if (port != address.port) return false;
        if (portMask != address.portMask) return false;
        if (ip != null ? !ip.equals(address.ip) : address.ip != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + ipMask;
        result = 31 * result + port;
        result = 31 * result + portMask;
        return result;
    }

    public String toString()
    {
        String address;
        address = ip;
        if (ipMask != 0)
        {
            address = address + '/' + ipMask;
        }
        if (port != 0)
        {
            address = address + ':' + port;
            if (portMask != 0)
            {
                address = address + '/' + portMask;
            }
        }
        return address;
    }

    /**
     * This method creates Address object using String interpretation of address
     * String format is "XXX.XXX.XXX.XXX/YYY:PP/MM"
     * XX - ipv4 address
     * YY - ipv4 mask
     * PP - port
     * MM - port mask
     *
     * @param addressString
     */
    public static Address create(String addressString) throws IllegalArgumentException
    {
        String ip = "";
        int ipMask = 0, port = 0, portMask = 0;

        Pattern addressPattern =
                Pattern.compile(IP4_SOCKET_PATTERN);
        Matcher myMatcher = addressPattern.matcher(addressString);
        if (!myMatcher.find()) throw new IllegalArgumentException("wrong ip socket format");

        ip = myMatcher.group(1);
        Pattern ipPattern = Pattern.compile(IP4_ADDRESS_PATTERN);
        if (!ipPattern.matcher(ip).find()) throw new IllegalArgumentException("wrong ip format");

        String maskOfIpGroup = myMatcher.group(8);
        if (maskOfIpGroup != null)
        {
            try
            {

                if (maskOfIpGroup.matches(HEX_NUMBER_PATTERN))
                {
                    ipMask = Integer.parseInt(maskOfIpGroup.substring(2), 16);
                } else ipMask = Integer.parseInt(maskOfIpGroup, 10);
            } catch (NumberFormatException e)
            {
                throw new IllegalArgumentException("incorrect mask of ip format");

            }
        } else
        {
            ipMask = 0;
        }
        String portGroup = myMatcher.group(12);
        if (portGroup != null)
        {
            try
            {
                port = Integer.parseInt(portGroup);
            } catch (NumberFormatException e)
            {
                throw new IllegalArgumentException("incorrect port format");
            }
        } else port = 0;
        String maskOfPortGroup = myMatcher.group(14);

        if (maskOfPortGroup != null)
        {
            try
            {
                if (maskOfPortGroup.matches("0x[a-fA-F\\d]+"))
                {
                    portMask = Integer.parseInt(maskOfPortGroup.substring(2), 16);
                } else portMask = Integer.parseInt(maskOfPortGroup, 10);
            } catch (NumberFormatException e)
            {
                throw new IllegalArgumentException("incorrect mask of port format");
            }
        } else
        {
            portMask = 0;
        }

        Address address = new Address(ip, ipMask, port, portMask);
        return address;
    }

}
