package org.potholes.api.sys;

import java.io.Serializable;

public class SysMenuMeta implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String metaPath;

    public String getMetaPath() {
        return metaPath;
    }

    public void setMetaPath(String metaPath) {
        this.metaPath = metaPath;
    }

}
