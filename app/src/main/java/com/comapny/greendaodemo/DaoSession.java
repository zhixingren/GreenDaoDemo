package com.comapny.greendaodemo;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.comapny.greendaodemo.entity.StudentInfo;

import com.comapny.greendaodemo.StudentInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig studentInfoDaoConfig;

    private final StudentInfoDao studentInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        studentInfoDaoConfig = daoConfigMap.get(StudentInfoDao.class).clone();
        studentInfoDaoConfig.initIdentityScope(type);

        studentInfoDao = new StudentInfoDao(studentInfoDaoConfig, this);

        registerDao(StudentInfo.class, studentInfoDao);
    }
    
    public void clear() {
        studentInfoDaoConfig.clearIdentityScope();
    }

    public StudentInfoDao getStudentInfoDao() {
        return studentInfoDao;
    }

}
