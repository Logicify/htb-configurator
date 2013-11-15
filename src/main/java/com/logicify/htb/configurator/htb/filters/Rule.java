package com.logicify.htb.configurator.htb.filters;

import com.logicify.htb.configurator.htb.HTBException;

/**
 * <h1>Rule</h1>
 * <p>This class make up "u32" filter rules that select traffic for
 * each of the classes. This class contains fields:
 * <ul>
 *     <li><b>ssocket</b>-source socket, is where traffic comes from</li>
 *     <li><b>dsocket</b>-destination socket, is where traffic goes to</li>
 *     <li><b>comment</b>-is a comment to the rule</li>
 * </ul></p>
 */
public class Rule {
    private Socket ssocket;
    private Socket dsocket;
    private String comment;

    public Rule() {
        this.ssocket = null;
        this.dsocket = null;
        this.comment = null;
    }

    public Rule(String ssocket, String dsocket, String comment) throws HTBException {
        if (ssocket != null) {
            this.ssocket = Socket.create(ssocket);
        } else {
            this.ssocket = null;
        }

        if (dsocket != null) {
            this.dsocket = Socket.create(dsocket);
        } else {
            this.dsocket = null;
        }

        this.comment = comment;
    }


    public Socket getSsocket() {
        return ssocket;
    }

    public void setSsocket(Socket ssocket) {
        this.ssocket = ssocket;
    }

    public Socket getDsocket() {
        return dsocket;
    }

    public void setDsocket(Socket dsocket) {
        this.dsocket = dsocket;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (dsocket != null ? !dsocket.equals(rule.dsocket) : rule.dsocket != null) return false;
        if (ssocket != null ? !ssocket.equals(rule.ssocket) : rule.ssocket != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ssocket != null ? ssocket.hashCode() : 0;
        result = 31 * result + (dsocket != null ? dsocket.hashCode() : 0);
        return result;
    }
}
