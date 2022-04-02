package generators.android.modules

import generators.base.FileGenerator
import models.FileExtention

class ProguardRulesGenerator constructor(
    private val generatedPath: String,
    private val onGeneratedFileListener: (String) -> Unit
): FileGenerator() {

    override fun getFileName(): String {
        return "proguard-rules"
    }

    override fun getFileContent(): String {
        return "# Add project specific ProGuard rules here.\n" +
                "# You can control the set of applied configuration files using the\n" +
                "# proguardFiles setting in build.gradle.\n" +
                "#\n" +
                "# For more details, see\n" +
                "#   http://developer.android.com/guide/developing/tools/proguard.html\n" +
                "\n" +
                "# If your project uses WebView with JS, uncomment the following\n" +
                "# and specify the fully qualified class name to the JavaScript interface\n" +
                "# class:\n" +
                "#-keepclassmembers class fqcn.of.javascript.interface.for.webview {\n" +
                "#   public *;\n" +
                "#}\n" +
                "\n" +
                "# Uncomment this to preserve the line number information for\n" +
                "# debugging stack traces.\n" +
                "#-keepattributes SourceFile,LineNumberTable\n" +
                "\n" +
                "# If you keep the line number information, uncomment this to\n" +
                "# hide the original source file name.\n" +
                "#-renamesourcefileattribute SourceFile\n" +
                "\n" +
                "-keepclassmembers class * {\n" +
                "    @com.squareup.moshi.FromJson <methods>;\n" +
                "    @com.squareup.moshi.ToJson <methods>;\n" +
                "}\n" +
                "\n" +
                "-dontwarn okhttp3.**\n" +
                "-dontwarn okio.**\n" +
                "-dontwarn javax.annotation.**\n" +
                "-keepclasseswithmembers class * {\n" +
                "    @retrofit2.http.* <methods>;\n" +
                "}\n" +
                "-keepclasseswithmembers class * {\n" +
                "    @com.squareup.moshi.* <methods>;\n" +
                "}\n" +
                "-keep @com.squareup.moshi.JsonQualifier interface *\n" +
                "-keepclassmembers class kotlin.Metadata {\n" +
                "    public <methods>;\n" +
                "}\n" +
                "\n" +
                "-dontwarn org.jetbrains.annotations.**\n" +
                "-keep class kotlin.Metadata { *; }\n" +
                "\n" +
                "# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and\n" +
                "# EnclosingMethod is required to use InnerClasses.\n" +
                "-keepattributes Signature, InnerClasses, EnclosingMethod\n" +
                "\n" +
                "# Retrofit does reflection on method and parameter annotations.\n" +
                "-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations\n" +
                "\n" +
                "# Keep annotation default values (e.g., retrofit2.http.Field.encoded).\n" +
                "-keepattributes AnnotationDefault\n" +
                "\n" +
                "# Retain service method parameters when optimizing.\n" +
                "-keepclassmembers,allowshrinking,allowobfuscation interface * {\n" +
                "    @retrofit2.http.* <methods>;\n" +
                "}\n" +
                "\n" +
                "# Ignore annotation used for build tooling.\n" +
                "-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement\n" +
                "\n" +
                "# Ignore JSR 305 annotations for embedding nullability information.\n" +
                "-dontwarn javax.annotation.**\n" +
                "\n" +
                "# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.\n" +
                "-dontwarn kotlin.Unit\n" +
                "\n" +
                "# Top-level functions that can only be used by Kotlin.\n" +
                "-dontwarn retrofit2.KotlinExtensions\n" +
                "-dontwarn retrofit2.KotlinExtensions\$*\n" +
                "\n" +
                "# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy\n" +
                "# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.\n" +
                "-if interface * { @retrofit2.http.* <methods>; }\n" +
                "-keep,allowobfuscation interface <1>\n" +
                "\n" +
                "# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).\n" +
                "-keep,allowobfuscation,allowshrinking interface retrofit2.Call\n" +
                "-keep,allowobfuscation,allowshrinking class retrofit2.Response\n" +
                "\n" +
                "# With R8 full mode generic signatures are stripped for classes that are not\n" +
                "# kept. Suspend functions are wrapped in continuations where the type argument\n" +
                "# is used.\n" +
                "-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation\n" +
                "\n" +
                "# JSR 305 annotations are for embedding nullability information.\n" +
                "-dontwarn javax.annotation.**\n" +
                "\n" +
                "-keepclasseswithmembers class * {\n" +
                "    @com.squareup.moshi.* <methods>;\n" +
                "}\n" +
                "\n" +
                "-keep @com.squareup.moshi.JsonQualifier @interface *\n" +
                "\n" +
                "# Enum field names are used by the integrated EnumJsonAdapter.\n" +
                "# values() is synthesized by the Kotlin compiler and is used by EnumJsonAdapter indirectly\n" +
                "# Annotate enums with @JsonClass(generateAdapter = false) to use them with Moshi.\n" +
                "-keepclassmembers @com.squareup.moshi.JsonClass class * extends java.lang.Enum {\n" +
                "    <fields>;\n" +
                "    **[] values();\n" +
                "}\n" +
                "\n" +
                "# Keep helper method to avoid R8 optimisation that would keep all Kotlin Metadata when unwanted\n" +
                "-keepclassmembers class com.squareup.moshi.internal.Util {\n" +
                "    private static java.lang.String getKotlinMetadataClassName();\n" +
                "}\n" +
                "\n" +
                "# Keep ToJson/FromJson-annotated methods\n" +
                "-keepclassmembers class * {\n" +
                "  @com.squareup.moshi.FromJson <methods>;\n" +
                "  @com.squareup.moshi.ToJson <methods>;\n" +
                "}\n" +
                "\n" +
                "-keepclassmembernames @com.squareup.moshi.JsonClass class * extends java.lang.Enum {\n" +
                "    <fields>;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "-dontwarn org.jetbrains.annotations.**\n" +
                "-keep class kotlin.Metadata { *; }\n"
    }

    override fun getFileExt(): FileExtention = FileExtention.PRO
    override fun getFilePath(): String = generatedPath

    override fun execute() {
        generateFile {
            onGeneratedFileListener(it)
        }
    }

}