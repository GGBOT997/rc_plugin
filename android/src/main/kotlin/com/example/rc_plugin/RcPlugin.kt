package com.example.rc_plugin

import android.content.Context
import android.content.Intent
import androidx.annotation.NonNull
import io.flutter.Log
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.ActivityResultListener
import java.io.File

/** RcPlugin */
class RcPlugin : FlutterPlugin, MethodCallHandler, ActivityAware, ActivityResultListener {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private var mContext: Context? = null
    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "rc_plugin")
        channel.setMethodCallHandler(this)
        mContext = flutterPluginBinding.applicationContext
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "install_apk") {
            //apk路径
            val path = call.argument<String>("path")
            //apk 名
            val name = call.argument<String>("applicationName")
            //安裝
            result.success(if (installApk(path)) "1" else "0")
        } else {
            result.success("0")
        }
    }

    /**
     *
     */
   private fun installApk(path: String?): Boolean {
        if (path == null) {
            Log.e("RcPlugin", "文件路径不存在")
            return false
        }
        try {
            val file = File(path)
            return if (file.exists()) {
                ApkUtil.installApk(mContext, file)
                true
            } else {
                Log.e("RcPlugin", "apk文件不存在")
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("RcPlugin", e.message + "")
        }
        return false
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {

    }

    override fun onDetachedFromActivityForConfigChanges() {

    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {

    }

    override fun onDetachedFromActivity() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return false
    }
}
