# Salad Showcase App (iOS)

A SwiftUI sample app that mirrors the Android showcase app
(`examples/showcase-app-android`), giving the Salad `XCUITest` module real
screens to automate: list, forms, toasts and gestures, plus a `saladshowcase://`
URL scheme for deeplink tests.

## Layout

```
project.yml                      # XcodeGen spec (generates the .xcodeproj)
ShowcaseApp/ShowcaseApp/
  SaladShowcaseApp.swift         # @main entry
  Screens/
    ContentView.swift            # Home + navigation to each screen
    ListView.swift               # 40-row list (scroll/swipe targets)
    FormsView.swift              # text fields, toggle, submit
    ToastView.swift              # toast banner + result text
    GesturesView.swift           # long-press + swipe targets
```

Accessibility identifiers are stable (`main_list`, `name_field`,
`toast_button`, ...) and are what the iOS page objects in
`examples/showcase/src/test/java/ios/` locate by.

## Build (macOS only)

Requires [XcodeGen](https://github.com/yonaskolb/XcodeGen):

```bash
brew install xcodegen
cd examples/showcase-app-ios
xcodegen generate
xcodebuild -project SaladShowcase.xcodeproj \
  -scheme SaladShowcase \
  -destination 'generic/platform=iOS Simulator' \
  build
```

Copy the produced `SaladShowcase.app` to
`examples/showcase/src/test/resources/`, then run:

```bash
./gradlew -p examples/showcase iosTest
```

## CI

`.github/workflows/run-ui-tests-ios.yml` does all of the above on a
`macos-15` runner (Xcode 26 / iOS 26, iPhone 15 simulator) whenever
files in this directory or the iOS test sources change. The built `.app` is
cached keyed on a hash of the sources in this directory, so it is only rebuilt
when the app source actually changes.
