/**
 * Created with IntelliJ IDEA.
 * User: vasya
 * Date: 10/27/13
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
package org.andrey_bayev.htb_configurator.htb.filters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//this class keeps address for Rule class
public class Address
{
    public static final String IP_SOCKET_PATTERN = "^(((\\d{1,3})\\.){3}(\\d{1,3}))(\\/((0[xX][a-fA-F\\d]+)|(\\d+)))?(:(\\d{1,5})(\\/((0[xX][a-fA-F\\d]+)|(\\d+)))?)?$";
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


    /**
     * String format is "XXX.XXX.XXX.XXX/YYY:PP/MM"
     * XX - ipv4 address
     * YY - ipv4 mask
     * PP - port
     * MM - port mask
     *
     * @param address
     */
    public Address(String address)
    {
        // TODO: redo with regexp. Outline what's optional and what's required, and code altogether into the single
        // nice regular expression.
        // Regexp can be tested online here http://www.regexplanet.com/advanced/java/index.html
        // with regexp, all this code virtually collapses to 4 lines.
        // TODO: i believe it's not needed anymore.

        String parts[] = address.split(":");

        if (!parts[0].equals(""))
        {
            String ipParts[] = parts[0].split("/");
            ip = ipParts[0];
            if (ipParts.length == 2)
            {
                if ((ipParts[1].charAt(1) == 'x') || (ipParts[1].charAt(1) == 'X'))
                {
                    ipMask = Integer.parseInt(ipParts[1].substring(2), 16);
                } else
                {
                    ipMask = Integer.parseInt(ipParts[1]);
                }
            } else
            {
                ipMask = 0;
            }
        } else
        {
            ip = "";
            ipMask = 0;
        }
        if (parts.length == 2)
        {
            String portParts[] = parts[1].split("/");
            port = Integer.parseInt(portParts[0]);
            if (portParts.length == 2)
            {
                if ((portParts[1].charAt(1) == 'x') || (portParts[1].charAt(1) == 'X'))
                {
                    portMask = Integer.parseInt(portParts[1].substring(2), 16);
                } else
                {
                    portMask = Integer.parseInt(portParts[1]);
                }
            } else
            {
                portMask = 0;
            }
        } else
        {
            port = 0;
            portMask = 0;
        }
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

    // todo if you override equals, better override hashCode as well, and it should depend on the same filds!
    public boolean equals(Address address)
    {
        if ((this == null) && (address == null)) return true;
        else
        {
            if (((this != null) && (address == null)) || ((this == null) && (address != null))) return false;
        }

        if (this.ip.equals(address.ip) && (this.ipMask == address.ipMask) &&
                (this.port == address.port) && (this.portMask == address.portMask)) return true;
        else return false;
    }

    public String toString()
    {
        String address;
        address = ip;
        if (ipMask != 0) address = address + '/' + ipMask;
        if (port != 0)
        {
            address = address + ':' + port;
            if (portMask != 0) address = address + '/' + portMask;
        }
        return address;
    }

    //this function convert String of ip socket into Address class format
    public static Address convertStringIntoAddress(String addressString) throws IllegalArgumentException
    {
        String ip = "";
        int ipMask = 0, port = 0, portMask = 0;

        Pattern addressPattern =
                Pattern.compile(IP_SOCKET_PATTERN);
        Matcher myMatcher = addressPattern.matcher(addressString);
        // todo  better invert this.
        // e.g. if (!myMatcher.find()) {throw exception} ... -> without else block. This saves 1 indentation for readability
        if (myMatcher.find())
        {
            ip = myMatcher.group(1);
            //todo pattern -> constant
            Pattern ipPattern = Pattern.compile("^(([12]?\\d?\\d)\\.){3}([12]?\\d?\\d)$");
            if (!ipPattern.matcher(ip).find()) throw new IllegalArgumentException("wrong ip format");
            String maskOfIpGroup = myMatcher.group(6);
            if (maskOfIpGroup != null)
            {
                try
                {
                    //todo pattern -> constant
                    if (maskOfIpGroup.matches("0x[a-fA-F\\d]+"))
                    {

                        ipMask = Integer.parseInt(maskOfIpGroup.substring(2), 16);
                    } else ipMask = Integer.parseInt(maskOfIpGroup, 10);
                } catch (NumberFormatException e)
                {

                    throw e;
                    // todo was a good idea. Better throw one type of exception from within the method.
                    // now you are throwing 2 - IAE and NFE; and both unchecked.
                    // trick is that user of your code may handle IAE only, and when this rare NFE fires,
                    // it will crash the whole app.
                    //throw new IllegalArgumentException("incorrect mask of ip format");
                }
            } else
            {
                ipMask = 0;
            }
            String portGroup = myMatcher.group(10);
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
            String maskOfPortGroup = myMatcher.group(12);

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
        } else
        {
            throw new IllegalArgumentException("wrong ip socket format");
        }


    }

}
