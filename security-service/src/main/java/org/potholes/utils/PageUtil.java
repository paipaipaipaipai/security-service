package org.potholes.utils;

import org.apache.ibatis.session.RowBounds;
import org.potholes.constants.GlobalConstants;

public class PageUtil {

    public static RowBounds getRowBounds(Integer pageNo, Integer pageSize) {
        int page = (pageNo != null && pageNo > 0) ? pageNo - 1 : GlobalConstants.PAGENO;
        int size = (pageSize != null && pageSize > 0) ? pageSize : GlobalConstants.PAGESIZE;
        int offset = page * size;
        return new RowBounds(offset, size);
    }

}
