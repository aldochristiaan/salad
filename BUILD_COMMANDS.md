# Salad Framework - Quick Commands

## Build Commands

### Clean Build
```bash
./gradlew clean build
```

### Build with Tests
```bash
./gradlew clean build test
```

### Build without Tests
```bash
./gradlew clean build -x test
```

### Create Shadow JAR
```bash
./gradlew shadowJar
```

### Complete Build
```bash
./gradlew clean build shadowJar -x test
```

### Check for Errors
```bash
./gradlew compileJava
```

---

## JAR Location

After building, find your JAR at:
```
build/libs/salad-1.3.0-rc1.jar
```

---

## Using in Your Project

### Gradle
```groovy
dependencies {
    implementation files('libs/salad-1.3.0-rc1.jar')
}
```

### Maven
```xml
<dependency>
    <groupId>salad</groupId>
    <artifactId>salad</artifactId>
    <version>1.3.0-rc1</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/libs/salad-1.3.0-rc1.jar</systemPath>
</dependency>
```

---

## Documentation Files

- **QUICK_REFERENCE.md** - Developer quick start guide
- **REFACTORING_SUMMARY.md** - Technical details
- **CHANGELOG.md** - Version history
- **REFACTORING_COMPLETE.md** - Completion summary
- **README.md** - Project overview

---

## Version

**Current**: 1.3.0-rc1  
**Date**: November 5, 2025

