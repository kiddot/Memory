package com.android.memory.bean.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.android.memory.base.dao.DaoManager;
import com.android.memory.base.dao.OnSQLiteDatabaseListener;
import com.android.memory.bean.DaoMaster;

import de.greenrobot.dao.AbstractDaoMaster;

/**
 * Created by yzw on 2016/7/13 0013.
 */
public class YXYDaoManager extends DaoManager {
    public YXYDaoManager(Context context, String dbName,OnSQLiteDatabaseListener listener) {
        super(context, dbName, listener);
    }

    @Override
    protected AbstractDaoMaster initDaoMaster(SQLiteDatabase writableDb) {
        // 返回GreenDao生成的DaoMaster
        return new DaoMaster(writableDb);
    }
}
