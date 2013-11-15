package com.logicify.htb.configurator.htb;

/**
 * This class keeps params of the HTB root queuening classes
 */
public class RootParams {
    private int defaultID;//this is ID of the default HTB class where UNCLASSIFIED traffic goes
    private int r2q; //coefficient for computing DRR (Deficit Round Robin) quanta
    private boolean dcache; //to use dequeue cache or not

    public RootParams() {
        defaultID = DefaultHTBClassValues.DEFAULT_ROOT_ID;
        r2q = DefaultHTBClassValues.DEFAULT_R2Q;
        dcache = false;
    }

    public RootParams(int defaultId, int r2q, boolean dcache) {
        if (defaultId < 0) this.defaultID = DefaultHTBClassValues.DEFAULT_ROOT_ID;
        else this.defaultID = defaultID;
        if (r2q < DefaultHTBClassValues.DEFAULT_R2Q) this.r2q = DefaultHTBClassValues.DEFAULT_R2Q;
        else this.r2q = r2q;
        this.dcache = dcache;
    }

    public int getDefaultID() {
        return defaultID;
    }

    public void setDefaultID(int defaultID) {
        if (defaultID < 0) this.defaultID = DefaultHTBClassValues.DEFAULT_ROOT_ID;
        else this.defaultID = defaultID;
    }

    public int getR2q() {
        return r2q;
    }

    public void setR2q(int r2q) {
        if (r2q < DefaultHTBClassValues.DEFAULT_R2Q) this.r2q = DefaultHTBClassValues.DEFAULT_R2Q;
        else this.r2q = r2q;
    }

    public boolean isDchache() {
        return dcache;
    }

    public void setDchache(boolean dchache) {
        this.dcache = dchache;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RootParams that = (RootParams) o;

        if (dcache != that.dcache) return false;
        if (defaultID != that.defaultID) return false;
        if (r2q != that.r2q) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = defaultID;
        result = 31 * result + r2q;
        result = 31 * result + (dcache ? 1 : 0);
        return result;
    }
}
