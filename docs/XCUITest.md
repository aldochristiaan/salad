## 🍎 XCUITest (iOS) Modules

Accessed from page objects extending `XCUITest`.

> ℹ️ The iOS accessors are named `tapElement()`, `typeText()` and `longTapElement()`
> (the Android equivalents are `tap()`, `type()` and `longTap()`).

### 🔹 Tap

- `element(String elementLocator)`  
  Tap element  
  `tapElement().element("IOS_ELEMENT");`

- `element(String elementLocator, int index)`  
  Tap element at index  
  `tapElement().element("IOS_ELEMENT", 2);`

- `elementWithWait(String elementLocator, int timeout)`  
  Wait up to `timeout` seconds, then tap  
  `tapElement().elementWithWait("IOS_ELEMENT", 10);`

- `elementWithWait(String elementLocator, int timeout, int index)`  
  Wait, then tap at index  
  `tapElement().elementWithWait("IOS_ELEMENT", 10, 2);`

- `location(int x, int y, Duration duration)`  
  Tap at coordinates with a custom duration  
  `tapElement().location(100, 200, Duration.ofMillis(500));`

- `location(int x, int y)`  
  Tap at coordinates with the default 500ms duration  
  `tapElement().location(100, 200);`

> ⚠️ `pendingElement(...)` is deprecated — use `elementWithWait(...)` instead.
> It still works and will remain until v2.0.0.

---

### 🔹 Long Tap

- `element(String elementLocator)` / `element(String elementLocator, int index)`  
  Long-press element  
  `longTapElement().element("IOS_ELEMENT");`

- `pendingElement(String elementLocator, int timeout)` / `(..., int index)`  
  Wait, then long-press  
  `longTapElement().pendingElement("IOS_ELEMENT", 10);`

---

### 🔹 Type

- `element(String elementLocator, String text)`  
  Type text  
  `typeText().element("IOS_ELEMENT", "Text to type");`

- `element(String elementLocator, int index, String text)`  
  Type text at index  
  `typeText().element("IOS_ELEMENT", 1, "Text to type");`

- `pendingElement(String elementLocator, int timeout, String text)`  
  Wait, then type  
  `typeText().pendingElement("IOS_ELEMENT", 10, "Text to type");`

- `pendingElement(String elementLocator, int timeout, int index, String text)`  
  Wait, then type at index  
  `typeText().pendingElement("IOS_ELEMENT", 10, 1, "Text to type");`

---

### 🔹 Swipe

Directional helpers: `up()`, `down()`, `left()`, `right()` — each also accepting custom
start/end fractions, plus an `...AtSpecifiedLocator(String elementLocator)` variant that
swipes relative to an element.

- `toLocator(String elementLocator)`  
  Swipe until the element is in view  
  `swipe().toLocator("IOS_ELEMENT");`

- `toLocator(String elementLocator, Direction direction)`  
  Swipe in `direction` until the element is in view  
  `swipe().toLocator("IOS_ELEMENT", Direction.UP);`

- `to(double xStart, double xEnd, double yStart, double yEnd)`  
  Swipe between absolute coordinates  
  `swipe().to(0.5, 0.5, 0.5, 0.2);`

- `toDirection(Direction direction)`  
  Swipe in a direction (uses screen size)  
  `swipe().toDirection(Direction.DOWN);`

- `up()` · `up(double yStart, double yEnd)` · `upAtSpecifiedLocator(locator)`  
- `down()` · `down(...)` · `downAtSpecifiedLocator(locator)`  
- `left()` · `left(...)` · `leftAtSpecifiedLocator(locator)`  
- `right()` · `right(...)` · `rightAtSpecifiedLocator(locator)`

```java
swipe().up();
swipe().right(0.8, 0.2);
swipe().downAtSpecifiedLocator("SCROLL_AREA");
```

---

### 🔹 Get Element

- `withLocator(String elementLocator)`  
  Return `WebElement` with given locator  
  `getElement().withLocator("IOS_ELEMENT");`

- `withLocator(String elementLocator, int timeout)`  
  Return `WebElement` with timeout  
  `getElement().withLocator("IOS_ELEMENT", 5);`

- `withLocator(String elementLocator, Direction direction)`  
  Return `WebElement`, scrolling in `direction` until found  
  `getElement().withLocator("IOS_ELEMENT", Direction.UP);`

- `withDefaultTimeout(String elementLocator)`  
  Return `WebElement` using `SaladConfig.DEFAULT_TIMEOUT` (30s)  
  `getElement().withDefaultTimeout("IOS_ELEMENT");`

---

### 🔹 Get Multiple Element

- `withLocator(String elementLocator)`  
  Return `List` with given locator  
  `getMultipleElement().withLocator("LIST_ITEM");`

- `withLocator(String elementLocator, int timeout)`  
  Return `List<WebElement>` with timeout  
  `getMultipleElement().withLocator("LIST_ITEM", 5);`

---

### 🔹 Deeplink

- `open(String deeplink)`  
  Open a full deeplink URL  
  `deeplink().open("myapp://screen/action");`

- `open(String scheme, String path)`  
  Build and open `scheme://path`  
  `deeplink().open("myapp", "screen/action");`
