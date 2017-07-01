package com.androidtmc.android_kotlin_base.utils

import android.content.Context
import android.content.pm.PackageManager
import com.androidtmc.android_kotlin_base.base.ConfigModule
import java.util.*

/**
 * Created by cuong on 7/1/17.
 */
class ManifestParser(var context: Context){
    private val MODULE_VALUE = "ConfigModule"



    fun parse(): List<ConfigModule> {
        val configModules = ArrayList<ConfigModule>()
        try {
            val appInfo = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)

            if (appInfo.metaData != null) {
                for (key in appInfo.metaData.keySet()) {
                    if (MODULE_VALUE == appInfo.metaData.get(key)) {
                        configModules.add(parseModule(key))
                    }
                }
            }
        } catch (ex: PackageManager.NameNotFoundException) {
            throw RuntimeException("Unable to find metadata to parse ConfigModule", ex)
        }

        return configModules
    }

    private fun parseModule(className: String): ConfigModule {
        val clazz: Class<*>
        try {
            clazz = Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw IllegalArgumentException("Unable to find ConfigModule implementation", e)
        }

        val module: Any
        try {
            module = clazz.newInstance()
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Unable to instantiate ConfigModule implementation for " + clazz, e)
        } catch (e: InstantiationException) {
            throw RuntimeException("Unable to instantiate ConfigModule implementation for " + clazz, e)
        }

        if (module !is ConfigModule) {
            throw RuntimeException("Expected instanceof ConfigModule, but found :" + module)
        }

        return module as ConfigModule
    }
}