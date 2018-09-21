# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class com.vdocipher.aegis.*{*;}


-dontwarn com.squareup.okhttp3.**
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8 #Retain generic type information for use by reflection by converters and adapters
-dontnote retrofit2.Platform #Platform used when running on Java8 VMs.Will not be used at runtime.
-keepattributes Signature #retain declared checked exceptions for use by a Proxy instance
-keepattributes Exceptions
-keepattributes Annotation
-keep class okhttp3.*{*;}
-keep interface okhttp3.*{*;}
-dontwarn okhttp3
-ignorewarnings
-keep class * {public private *;}
-dontwarn uk.**
-keep class retrofit.** { *; }
-keep class com.google.gson.**{*;}
-keep public class com.google.gson.**{public private protected *;}
-keep class com.exampleapp.model.** { *; }
-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
    boolean mShiftingMode;
}
-keep class com.technocracy.app.aavartan.** { *; }
-keep public class com.google.gson