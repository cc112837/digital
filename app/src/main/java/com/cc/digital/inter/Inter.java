package com.cc.digital.inter;

/**
 * Created by Administrator on 2015/11/11.
 */
public interface Inter {
    String base="http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&modules=portal&actions=";
    String homePicUri=base+"diydata&bid=274";
    String homeHomeUri=base+"article&limit=0_20&order=dateline_desc";
    String baseNewsUri="http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&sortid=36&sortsearch=1&typeid=0&limit=";
    String newsbase="http://www.dgtle.com/portal.php?mod=view&aid=";
}
