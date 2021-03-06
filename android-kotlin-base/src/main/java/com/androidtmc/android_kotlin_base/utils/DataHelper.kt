package com.androidtmc.android_kotlin_base.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Environment
import android.util.Base64
import java.io.*

/**
 * Created by cuong on 7/1/17.
 */
object DataHelper {
    private var mSharedPreferences: SharedPreferences? = null
    val SP_NAME = "config"




    /**
     * @param key
     * *
     * @param value
     */
    fun setStringSF(context: Context, key: String, value: String) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        mSharedPreferences!!.edit().putString(key, value).apply()
    }

    /**
     * @param key
     * *
     * @return
     */
    fun getStringSF(context: Context, key: String): String {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        return mSharedPreferences!!.getString(key, null)
    }

    /**
     * @param key
     * *
     * @param value
     */
    fun setIntergerSF(context: Context, key: String, value: Int) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        mSharedPreferences!!.edit().putInt(key, value).apply()
    }

    /**
     * @param key
     * *
     * @return
     */
    fun getIntergerSF(context: Context, key: String): Int {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        return mSharedPreferences!!.getInt(key, -1)
    }

    /**
     * @param context
     * *
     * @param key
     * *
     * @param value
     */
    fun setBooleanSF(context: Context, key: String, value: Boolean) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        mSharedPreferences!!.edit().putBoolean(key, value).apply()
    }

    /**
     * @param context
     * *
     * @param key
     * *
     * @return
     */
    fun getBooleanSF(context: Context, key: String): Boolean {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        return mSharedPreferences!!.getBoolean(key, false)
    }

    /**
     * 清除某个内容
     */
    fun removeSF(context: Context, key: String) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        mSharedPreferences!!.edit().remove(key).apply()
    }

    /**
     * 清除Shareprefrence
     */
    fun clearShareprefrence(context: Context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        mSharedPreferences!!.edit().clear().apply()
    }

    /**
     * 将对象储存到sharepreference

     * @param key
     * *
     * @param device
     * *
     * @param <T>
    </T> */
    fun <T> saveDeviceData(context: Context, key: String, device: T): Boolean {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        val baos = ByteArrayOutputStream()
        try {   //Device为自定义类
            // 创建对象输出流，并封装字节流
            val oos = ObjectOutputStream(baos)
            // 将对象写入字节流
            oos.writeObject(device)
            // 将字节流编码成base64的字符串
            val oAuth_Base64 = String(Base64.encode(baos
                    .toByteArray(), Base64.DEFAULT))
            mSharedPreferences!!.edit().putString(key, oAuth_Base64).apply()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

    /**
     * 将对象从shareprerence中取出来

     * @param key
     * *
     * @param <T>
     * *
     * @return
    </T> */
    fun <T> getDeviceData(context: Context, key: String): T? {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        var device: T? = null
        val productBase64 = mSharedPreferences!!.getString(key, null) ?: return null

// 读取字节
        val base64 = Base64.decode(productBase64.toByteArray(), Base64.DEFAULT)

        // 封装到字节流
        val bais = ByteArrayInputStream(base64)
        try {
            // 再次封装
            val bis = ObjectInputStream(bais)

            // 读取对象
            device = bis.readObject() as T

        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return device
    }

    /**
     * 返回缓存文件夹
     */
    fun getCacheFile(context: Context): File {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            var file: File? = null
            file = context.externalCacheDir//获取系统管理的sd卡缓存文件
            if (file == null) {//如果获取的为空,就是用自己定义的缓存文件夹做缓存路径
                file = File(getCacheFilePath(context))
                makeDirs(file)
            }
            return file
        } else {
            return context.cacheDir
        }
    }

    /**
     * 获取自定义缓存文件地址

     * @param context
     * *
     * @return
     */
    fun getCacheFilePath(context: Context): String {
        val packageName = context.packageName
        return "/mnt/sdcard/" + packageName
    }


    /**
     * 创建未存在的文件夹

     * @param file
     * *
     * @return
     */
    fun makeDirs(file: File): File {
        if (!file.exists()) {
            file.mkdirs()
        }
        return file
    }

    /**
     * 使用递归获取目录文件大小

     * @param dir
     * *
     * @return
     */
    fun getDirSize(dir: File?): Long {
        if (dir == null) {
            return 0
        }
        if (!dir.isDirectory) {
            return 0
        }
        var dirSize: Long = 0
        val files = dir.listFiles()
        for (file in files!!) {
            if (file.isFile) {
                dirSize += file.length()
            } else if (file.isDirectory) {
                dirSize += file.length()
                dirSize += getDirSize(file) // 递归调用继续统计
            }
        }
        return dirSize
    }

    /**
     * 使用递归删除文件夹

     * @param dir
     * *
     * @return
     */
    fun deleteDir(dir: File?): Boolean {
        if (dir == null) {
            return false
        }
        if (!dir.isDirectory) {
            return false
        }
        val files = dir.listFiles()
        for (file in files!!) {
            if (file.isFile) {
                file.delete()
            } else if (file.isDirectory) {
                deleteDir(file) // 递归调用继续删除
            }
        }
        return true
    }


}