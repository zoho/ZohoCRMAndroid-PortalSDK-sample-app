package com.kaushal.zcrm_externalsdk_sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zoho.crm.sdk.android.api.handler.DataCallback
import com.zoho.crm.sdk.android.api.response.BulkAPIResponse
import com.zoho.crm.sdk.android.authorization.ZCRMSDKClient
import com.zoho.crm.sdk.android.common.CommonUtil
import com.zoho.crm.sdk.android.configuration.ZCRMSDKConfigs
import com.zoho.crm.sdk.android.configuration.getBuilder
import com.zoho.crm.sdk.android.crud.ZCRMQuery
import com.zoho.crm.sdk.android.crud.ZCRMRecord
import com.zoho.crm.sdk.android.exception.ZCRMException
import com.zoho.crm.sdk.android.exception.ZCRMLogger
import com.zoho.crm.sdk.android.setup.sdkUtil.ZCRMSDKUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Level

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    
        login.setOnClickListener {

            val configs = ZCRMSDKConfigs.getBuilder(
                "1001757695.ZY4GS26CW7V170488NCSB87GAVL5NH",
                "b8b86d3912f680e820b4c54f9668fe854c4e4a1cf4",
                "ZohoCRM.modules.ALL,ZohoCRM.settings.ALL,ZohoCRM.users.ALL,ZohoCRM.org.ALL",
                "1001757695", "testportal55")
                .setApiBaseURL("https://crm.localzoho.com")
                .setAppType(CommonUtil.AppType.ZCRMCP)
                .setHttpRequestMode(CommonUtil.HttpRequestMode.ASYNC)
                .setLoggingPreferences(Level.ALL, true).build()
            
    
            var zcrmsdkClient = ZCRMSDKClient.getInstance(applicationContext)
            zcrmsdkClient.init(configs, object : ZCRMSDKClient.Companion.ZCRMInitCallback {
                override fun onSuccess() {
                    try {
                        ZCRMLogger.logError("init success")
                        runOnUiThread {
                            textView.text = "init success"
                        }
                    }
                    catch (exception: Exception)
                    {
                        runOnUiThread {
                            ZCRMLogger.logError(exception)
                            textView.text = exception.toString()
                        }
                    }
                }
        
                override fun onFailed(ex: ZCRMException) {
                    runOnUiThread {
                        ZCRMLogger.logError("init failed $ex")
                    }
                }
            })
        }
    
        getrecords.setOnClickListener{
            ZCRMSDKUtil.getModuleDelegate("Contacts").getRecords(ZCRMQuery.Companion.GetRecordParams(), object : DataCallback<BulkAPIResponse, List<ZCRMRecord>>
            {
                override fun completed(
                    response: BulkAPIResponse,
                    zcrmentity: List<ZCRMRecord>
                ) {
                    runOnUiThread{ textView.text = zcrmentity.toString() }
                }
            
                override fun failed(exception: ZCRMException) {
                    ZCRMLogger.logError("$exception")
                }
            })
        }
    
        logout.setOnClickListener {
            ZCRMSDKClient.getInstance(applicationContext)
                .logout(object : ZCRMSDKClient.Companion.ZCRMLogoutCallback {
                    override fun onFailed(ex: ZCRMException) {
                        textView.text = ex.toString()
                    }
                
                    override fun onSuccess() {
                        textView.text = "logout success"
                    }
                })
        }
    }
}