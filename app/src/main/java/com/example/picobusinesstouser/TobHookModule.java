package com.example.picobusinesstouser;

import android.util.Log;

import java.util.Objects;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TobHookModule implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (!Objects.equals(lpparam.packageName, "com.pvr.shortcut")) return;

        try {
            var dockUtilsClass = XposedHelpers.findClass(
                    "com.pvr.shortcut.utils.DockUtils",
                    lpparam.classLoader
            );

            var hook = new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }
            };

            XposedHelpers.findAndHookMethod(
                    dockUtilsClass,
                    "isSupportToB",
                    hook
            );
        } catch (Exception e) {
            Log.e("TobHookModule", String.valueOf(e.getMessage()));
        }
    }
}
