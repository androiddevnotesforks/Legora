import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jetbrains.compose") version "1.0.0-alpha3"
}

group = "com.yazantarifi.legora"
version = "1.0.0.alpha"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

val macExtraPlistKeys: String
    get() = """
      <key>CFBundleURLTypes</key>
      <array>
        <dict>
          <key>legora</key>
          <string>Legora Application Deeplink</string>
          <key>legora</key>
          <array>
            <string>legora</string>
          </array>
        </dict>
      </array>
    """

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)

            packageName = "Legora"
            packageVersion = "1.0.0"
            description = "Project Starter, Code Generator"
            copyright = "© 2022 Legora.me. All rights reserved."
            vendor = "Legora io (legora.me)"
            appResourcesRootDir.set(project.layout.projectDirectory.dir("resources"))

            val iconsRoot = project.file("./images")
            macOS {
                iconFile.set(iconsRoot.resolve("rounded_icon.png"))
                packageVersion = "1.0.0"
                dmgPackageVersion = "1.0.0"
                pkgPackageVersion = "1.0.0"
                packageName = "Legora"
                dockName = "Legora"
                bundleID = "me.legora.desktop.app"
                infoPlist {
                    extraKeysRawXml = macExtraPlistKeys
                }
            }

            windows {
                iconFile.set(project.file("./images/rounded_icon.ico"))
                menuGroup = "Legora"
                upgradeUuid = "18159995-d967-4CD2-8885-77BFA97CFA9F"
            }

            linux {
                iconFile.set(iconsRoot.resolve("rounded_icon.png"))
                packageName = "me-legora-desktop"
                debPackageVersion = "1.0.0"
                rpmPackageVersion = "1.0.0"
                appCategory = "Applications Generator"
                appRelease = "1"
                debMaintainer = "Yazan98"
            }
        }
    }
}