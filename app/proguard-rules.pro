# Koin 依赖注入
# Koin 使用反射创建实例，需保留模块和注解标记的类
-keep class * extends org.koin.core.module.Module { *; }
-keepclassmembers class * {
    @org.koin.core.annotation.* *;
}

# Kotlin Serialization
# 保留序列化所需的注解和合成方法
-keepattributes *Annotation*, InnerClasses, EnclosingMethod, Signature
-keepclassmembers class * {
    @kotlinx.serialization.SerialName <fields>;
    @kotlinx.serialization.Serializable <methods>;
}
-keep class kotlinx.serialization.** { *; }

# DataStore
# 保留 DataStore 内部类和 Preferences 实现
-keep class androidx.datastore.** { *; }
-keepclassmembers class * extends androidx.datastore.preferences.Preferences { *; }

# Navigation Compose Type-safe API
# 保留导航类型和 NavType 实现
-keep class * implements androidx.navigation.NavType { *; }
-keepclassmembers class * {
    @androidx.navigation.NavType <fields>;
}

# 保留行号信息用于 Release 崩溃堆栈分析
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
