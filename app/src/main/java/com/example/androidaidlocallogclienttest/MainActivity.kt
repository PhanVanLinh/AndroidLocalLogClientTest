package com.example.androidaidlocallogclienttest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlocallog.ILocalLogService
import com.example.androidlocallog.model.LocalLog

class MainActivity : AppCompatActivity() {

    var iRemoteService: ILocalLogService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mConnection = object : ServiceConnection {
            // Called when the connection with the service is established
            override fun onServiceConnected(className: ComponentName, service: IBinder) {
                // Following the example above for an AIDL interface,
                // this gets an instance of the IRemoteInterface, which we can use to call on the service
                iRemoteService = ILocalLogService.Stub.asInterface(service)
            }

            // Called when the connection with the service disconnects unexpectedly
            override fun onServiceDisconnected(className: ComponentName) {
                Log.e(TAG, "Service has unexpectedly disconnected")
                iRemoteService = null
            }
        }

        bindService(mConnection)

        findViewById<Button>(R.id.button_test_send_normal_log).setOnClickListener {
            sendNormalLogToServerApp()
        }

        findViewById<Button>(R.id.button_test_send_error_log).setOnClickListener {
            sendErrorLogToServerApp()
        }
    }

    private fun sendNormalLogToServerApp() {
        iRemoteService?.writeLog(packageName, LocalLog("i", "Normal Log"))
    }

    private fun sendErrorLogToServerApp() {
        iRemoteService?.writeLog(packageName+"1", LocalLog("e", "Error Log"))
    }

    private fun bindService(mConnection: ServiceConnection) {
        val intent = Intent("service.name")
        intent.setPackage("com.example.androidaidlserver")
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}