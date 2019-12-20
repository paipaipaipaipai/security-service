package org.potholes.utils;

import org.apache.ibatis.session.RowBounds;
import org.potholes.constants.GlobalConstants;

public class PageUtil {

    public static RowBounds getRowBounds(Integer offset, Integer limit) {
        if (offset == null || offset < 0) {
            offset = GlobalConstants.OFFSET;
        }
        if (limit == null || limit < 0) {
            limit = GlobalConstants.LIMIT;
        }
        return new RowBounds(offset, limit);
    }

}
