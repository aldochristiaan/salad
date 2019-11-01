# Salad

Salad is an open source, cross-platform test automation wrapper based on [Appium](https://github.com/appium/appium) written in Java and use Page Object Pattern.

### Prerequisites

- Install Node.js 10+

- Install [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

- Install Android SDK and AVD Emulators

- Install Xcode and Simulators

- Install appium

    ``$ npm install -g appium``
    
    Check appium by running:
    
    ``$ appium -v``

- Install appium-doctor

    ``$ npm install -g appium-doctor``
    
    Check your appium setup by running:
    
    ``$ appium-doctor``
    
- Install [IntelliJ IDEA](https://www.jetbrains.com/idea/)

- You can use real device too!
 
Later you have to install several optional dependecies. But we are ready to rock now!

### Environment Variable

To complete our setup you can define PATH for several libraries on our terminal profile e.g: `.bashrc` or `.zshrc`

Example:

```
export JAVA_HOME=$(/usr/libexec/java_home)
export ANDROID_HOME=/Users/<username>/Library/Android/sdk
export PATH=$JAVA_HOME/bin:$PATH
export PATH=$ANDROID_HOME/platform-tools:$PATH
export PATH=$ANDROID_HOME/tools:$PATH
export PATH="/usr/local/bin:$PATH"
```

### Build

Run `./gradlew clean shadowJar` to get jar libs.

Check output folder `build/libs/salad-<version>.jar`

### How to use this library

Open [HOW-TO](https://github.com/aldochristiaan/salad/blob/master/docs/How-to.md) to start implement this library on your project