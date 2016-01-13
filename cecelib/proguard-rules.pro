# To enable ProGuard in your project, edit project.properties
# to define the proguard.config property as described in that file.
#
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in ${sdk.dir}/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the ProGuard
# include property in project.properties.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keepattributes *JavascriptInterface*  

-keepattributes JavascriptInterface  



-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgent
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.support.v4.app.FragmentActivity
-keep public class * extends android.app.Fragment
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.v4.** { *; }  
-keep public class * extends android.support.v4.**  
-keep class android.support.v4.view.**{ *;}
-keep class android.support.v4.content.**{ *;}



#mipush
#-keep class com.isat.test.receiver.MiMessageReceiver {*;}

-keepclasseswithmembernames class * {  
    native <methods>;  
}  
  
-keepclasseswithmembernames class * {  
    public <init>(android.content.Context, android.util.AttributeSet);  
}  
  
-keepclasseswithmembernames class * {  
    public <init>(android.content.Context, android.util.AttributeSet, int);  
}  
  
-keepclassmembers enum * {  
    public static **[] values();  
    public static ** valueOf(java.lang.String);  
}  
  
-keep class * implements android.os.Parcelable {  
  public static final android.os.Parcelable$Creator *;  
}  
  
-keepclasseswithmembers class * {  
    public <init>(android.content.Context);  
}  

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}



-keep public class * extends java.lang.Exception{

    public *;

}
-keep public class * implements java.lang.reflect.InvocationHandler{

    public *;

}


-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep class com.isat.lib.** { *; }
#-keep class com.baidu.bottom.** { *; }
#-keep class com.baidu.kirin.** { *; }
#-keep class com.baidu.mobstat.** { *; }
#-keep class org.apache.http.** { *; }
#-dontwarn org.apache.http.**
#-keep class android.net.http.** { *; }
#-dontwarn android.net.http.**

#-keep class com.sina.** { *; }
#-dontwarn com.sina.**

#-libraryjars libs/armeabi/libweibosdkcore.so
#-libraryjars libs/armeabi/libweibosdkcore.so
#-libraryjars libs/arm64-v8a/libweibosdkcore.so
#-libraryjars libs/armeabi-v7a/libweibosdkcore.so
#-libraryjars libs/mips/libweibosdkcore.so
#-libraryjars libs/mips64/libweibosdkcore.so
#-libraryjars libs/x86/libweibosdkcore.so
#-libraryjars libs/x86_64/libweibosdkcore.so

-dontwarn org.achartengine.**
-keepclasseswithmembernames class org.achartengine.**{*;}