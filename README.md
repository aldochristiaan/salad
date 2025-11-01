# 🥗 Salad

**Salad** is an open-source, cross-platform test automation wrapper built on [Appium](https://github.com/appium/appium), written in Java, and designed around the **Page Object Pattern**.

---

## 🚀 Tech Stack

- Node.js: v25+
- JDK: v21
- Appium: v3.x

---

## 📦 Prerequisites

Make sure the following tools are installed:

- [Node.js](https://nodejs.org/) (**v25 or higher**)
- [JDK 21](https://www.oracle.com/java/technologies/downloads/)
- Android SDK and AVD Emulators
- Xcode and iOS Simulators (macOS only)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (recommended IDE)

### Appium Setup

Install Appium v3 globally:

``
$ npm install -g appium@latest
``

Verify installation:

``
$ appium -v
``

Install Appium Doctor:

``
$ npm install -g appium-doctor
``

Check your Appium setup:

``
$ appium-doctor
``

> ✅ You can also run tests on real devices!

Later, you may need to install additional optional dependencies — but you're ready to rock now!

---

## ⚙️ Environment Variables

To complete the setup, define the following paths in your shell profile (e.g. `.bashrc`, `.zshrc`):

```
export JAVA_HOME=$(/usr/libexec/java_home)  
export ANDROID_HOME=/Users/<username>/Library/Android/sdk  
export PATH=$JAVA_HOME/bin:$PATH  
export PATH=$ANDROID_HOME/platform-tools:$PATH  
export PATH=$ANDROID_HOME/tools:$PATH  
export PATH="/usr/local/bin:$PATH"
```

---

## 🛠️ Build

Generate the JAR library:

``
$ ./gradlew clean shadowJar
``

Check the output folder:

``
build/libs/salad-<version>.jar
``

---

## 📘 Usage Guide

To start using this library in your project, visit the [HOW-TO guide](https://github.com/aldochristiaan/salad/blob/master/docs/How-to.md).
