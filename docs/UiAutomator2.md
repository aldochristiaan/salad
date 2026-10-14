## 🤖 UiAutomator2 Modules

Accessed from page objects extending `UiAutomator2`.

### 🔹 Tap

- `element(String elementLocator)`  
  Tap element  
  `tap().element("ANDROID_ELEMENT");`

- `element(String elementLocator, int index)`  
  Tap element at index  
  `tap().element("ANDROID_ELEMENT", 2);`

- `pendingElement(String elementLocator, int timeout)`  
  Wait up to `timeout` seconds, then tap  
  `tap().pendingElement("ANDROID_ELEMENT", 10);`

- `pendingElement(String elementLocator, int timeout, int index)`  
  Wait, then tap at index  
  `tap().pendingElement("ANDROID_ELEMENT", 10, 2);`

- `tapAt(int x, int y, Duration duration)`  
  Tap at coordinates  
  `tap().tapAt(100, 200, Duration.ofMillis(300));`

---

### 🔹 Long Tap

- `element(String elementLocator)` / `element(String elementLocator, int index)`  
  Long-press element  
  `longTap().element("ANDROID_ELEMENT");`

- `pendingElement(String elementLocator, int timeout)` / `(..., int index)`  
  Wait, then long-press  
  `longTap().pendingElement("ANDROID_ELEMENT", 10);`

---

### 🔹 Type

- `element(String elementLocator, String text)`  
  Type text  
  `type().element("ANDROID_ELEMENT", "Text to type");`

- `element(String elementLocator, int index, String text)`  
  Type text at index  
  `type().element("ANDROID_ELEMENT", 1, "Text to type");`

- `pendingElement(String elementLocator, int timeout, String text)`  
  Wait, then type  
  `type().pendingElement("ANDROID_ELEMENT", 10, "Text to type");`

- `pendingElement(String elementLocator, int timeout, int index, String text)`  
  Wait, then type at index  
  `type().pendingElement("ANDROID_ELEMENT", 10, 1, "Text to type");`

---

### 🔹 Swipe

- `to(double xStart, double xEnd, double yStart, double yEnd)`  
  Swipe between absolute coordinates  
  `swipe().to(0.5, 0.5, 0.5, 0.2);`

- `toDirection(Direction direction)`  
  Swipe in a direction (uses screen size)  
  `swipe().toDirection(Direction.UP);`

---

### 🔹 Get Element

- `withLocator(String elementLocator)`  
  Return `WebElement` with given locator  
  `getElement().withLocator("ANDROID_ELEMENT");`

- `withLocator(String elementLocator, int timeout)`  
  Return `WebElement` with timeout  
  `getElement().withLocator("ANDROID_ELEMENT", 5);`

- `withLocator(String elementLocator, Direction direction)`  
  Return `WebElement`, scrolling in `direction` until found  
  `getElement().withLocator("ANDROID_ELEMENT", Direction.UP);`

- `withDefaultTimeout(String elementLocator)`  
  Return `WebElement` using `SaladConfig.DEFAULT_TIMEOUT` (30s)  
  `getElement().withDefaultTimeout("ANDROID_ELEMENT");`

---

### 🔹 Get Multiple Element

- `withLocator(String elementLocator)` / `withLocator(String elementLocator, int timeout)`  
  Return `List<WebElement>`  
  `getMultipleElement().withLocator("LIST_ITEM");`

- `withLocator(String elementLocator, Direction direction)`  
  Scroll in `direction` until the elements are found  
  `getMultipleElement().withLocator("LIST_ITEM", Direction.UP);`

- `withDefaultTimeout(String elementLocator)`  
  Using the default timeout  
  `getMultipleElement().withDefaultTimeout("LIST_ITEM");`

- `count(String elementLocator)`  
  Return the number of matching elements  
  `int n = getMultipleElement().count("LIST_ITEM");`

---

### 🔹 Toast

- `getText()`  
  Return the current toast text  
  `String t = toast().getText();`

- `getText(int timeoutSeconds)`  
  Wait up to `timeoutSeconds` for a toast, then return its text  
  `toast().getText(5);`

- `isVisible()`  
  Whether a toast is currently visible  
  `if (toast().isVisible()) { ... }`

- `waitForToast(int timeoutSeconds)`  
  Wait for a toast to appear  
  `toast().waitForToast(10);`

---

### 🔹 Mobile Gesture

Low-level UiAutomator2 gestures:

- `tap(locator)` · `doubleTap(locator)` · `longTap(locator)`
- `pinchOpen(locator)` / `pinchOpen(locator, percent)`
- `pinchClose(locator)` / `pinchClose(locator, percent)`
- `swipe(Direction, xStart, yStart, percent)` / `swipe(..., width, height, percent)`
- `scroll(locator, Direction, xStart, yStart, percent)` — returns `boolean`
- `drag(locator, xEnd, yEnd)` / `drag(locator, xStart, xEnd, yStart, yEnd)`
- `fling(locator, Direction)` / `fling(locator, Direction, speed)` — returns `boolean`

```java
mobileGesture().scroll("LIST", Direction.UP, 0.5, 0.8, 0.5);
mobileGesture().fling("LIST", Direction.UP);
```

---

### 🔹 Change Context

- `toWebView()`  
  Switch to WebView context  
  `changeContext().toWebView();`

- `toNative()`  
  Switch back to native context  
  `changeContext().toNative();`

---

### 🔹 App Management

- `installApp(path)` · `uninstallApp(packageName)` · `clearApp(packageName)` · `terminateApp(packageName)`
- `activateApp(packageName)`
- `startActivity(fullActivityIntent)` · `startActivityEspresso(appActivity)`
- `isAppInstalled(packageName)` — returns `boolean`
