package com.example.androidlocallog;

import com.example.androidlocallog.model.LocalLog;

interface ILocalLogService {

    int getLogCount(String app);

    void writeLog(String appPackage, in LocalLog log);

}