package com.example.picobusinesstouser
import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class TobHookModule : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != "com.pvr.shortcut") return

        try {
            val dockUtilsClass = XposedHelpers.findClass(
                "com.pvr.shortcut.utils.DockUtils",
                lpparam.classLoader
            )

            XposedHelpers.findAndHookMethod(
                dockUtilsClass,
                "isSupportToB",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        param.result = false
                    }
                }
            )
        } catch (e: Exception) {
            Log.e("TobHookModule", "${e.message}")
        }
    }
}
