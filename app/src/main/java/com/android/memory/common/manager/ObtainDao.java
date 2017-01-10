package com.android.memory.common.manager;

import android.content.Context;
import android.util.Log;

import com.android.memory.base.dao.AppDaoManager;
import com.android.memory.base.dao.DaoManager;
import com.android.memory.bean.greendao.YXYDaoManager;

/**
 *
 * Created by kiddo on 17-1-10.
 */

public class ObtainDao {
    private static final String TAG = ObtainDao.class.getSimpleName();
    private Context mContext ;

    public ObtainDao(Context context){
        mContext = context;
    }

    public DaoManager getDaoManager(String dbName){
        DaoManager daoManager = AppDaoManager.get(dbName);
        if (daoManager == null){
            Log.d(TAG, "getDaoManager: 当前数据库为空");
            daoManager = new YXYDaoManager(mContext , dbName, null);
        }
        return daoManager;
    }
}
