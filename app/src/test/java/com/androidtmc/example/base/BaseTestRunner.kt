package com.androidtmc.example.base

import android.support.annotation.NonNull
import com.androidtmc.example.BuildConfig
import org.junit.internal.runners.InitializationError
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.manifest.AndroidManifest
import org.robolectric.res.FileFsFile
import java.lang.reflect.Method


/**
 * Created by cuong on 7/4/17.
 */
class BaseTestRunner : RobolectricTestRunner {
    /**
     * @param testClass the test class to be run
     * *
     * @throws InitializationError if junit says so
     */

    // This value should be changed as soon as Robolectric will support newer api.
    private val SDK_EMULATE_LEVEL = 23

    @Throws(InitializationError::class)
    constructor(testClass: Class<*>) : super(testClass)

    override fun getAppManifest(config: Config): AndroidManifest {
        val appManifest = super.getAppManifest(config)
        var androidManifestFile = appManifest.androidManifestFile

        if (androidManifestFile.exists()) {
            return appManifest
        } else {
            val moduleRoot = getModuleRootPath(config)
            androidManifestFile = FileFsFile.from(moduleRoot, appManifest.androidManifestFile.path.replace("bundles", "manifests/full"))
            val resDirectory = FileFsFile.from(moduleRoot, appManifest.resDirectory.path.replace("/res", "").replace("bundles", "res/merged"))
            val assetsDirectory = FileFsFile.from(moduleRoot, appManifest.assetsDirectory.path.replace("/assets", "").replace("bundles", "assets"))
            return AndroidManifest(androidManifestFile, resDirectory, assetsDirectory)
        }
    }

    private fun getModuleRootPath(config: Config): String {
        val moduleRoot = config.constants::class.java.classLoader.getResource("").toString().replace("file:", "")

       if(moduleRoot.contains("/build")) {
            moduleRoot.substring(0, moduleRoot.indexOf("/build"))
        }
        return moduleRoot
    }

    override fun getConfig(method: Method): Config {

        val config = super.getConfig(method)
        return Config.Implementation(
                intArrayOf(SDK_EMULATE_LEVEL),
                config.manifest,
                config.qualifiers,
                config.packageName,
                config.abiSplit,
                config.resourceDir,
                config.assetDir,
                config.buildDir,
                config.shadows::class.java.classes,
                config.instrumentedPackages,
                MockApplication::class.java,
                config.libraries,
                if (config.constants === Void::class.java) BuildConfig::class.java else config.constants::class.java
        )
    }

    @NonNull
    fun getSTApp(): MockApplication {
        return RuntimeEnvironment.application as MockApplication
    }
}