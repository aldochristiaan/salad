## ☕ Espresso Modules

### 🔹 Drawer

- `open(String elementLocator)`  
  Open application drawer  
  `drawer().open("ANDROID_DRAWER");`

- `close(String elementLocator)`  
  Close application drawer  
  `drawer().close("ANDROID_DRAWER");`

---

### 🔹 Flash

- `element(String elementLocator, int durationMillis, int repeatCount)`  
  Flash an element to ensure it's visible  
  `flash().element("ANDROID_ELEMENT", 500, 4);`

---

### 🔹 Get Element

- `withLocator(String elementLocator)`  
  Return `AndroidElement` with given locator  
  `getElement().withLocator("ANDROID_ELEMENT");`

- `withLocator(String elementLocator, int timeout)`  
  Return `AndroidElement` with timeout  
  `getElement().withLocator("ANDROID_ELEMENT", 5);`

---

### 🔹 Get Multiple Element

- `withLocator(String elementLocator)`  
  Return `List<AndroidElement>` with given locator  
  `getMultipleElement().withLocator("ANDROID_DRAWER");`

- `withLocator(String elementLocator, int timeout)`  
  Return `List<AndroidElement>` with timeout  
  `getMultipleElement().withLocator("ANDROID_DRAWER", 5);`

---

### 🔹 Multiple Tap

- `element(String elementLocator, int count)`  
  Tap element multiple times  
  `multipleTap().element("ANDROID_FLOATING_ACTION_BUTTON", 5);`

---

### 🔹 Swipe

- `element(String elementLocator, SwipeSpeed swipeSpeed, Coordinates startCoordinates, Coordinates endCoordinates, PrecisionDescriber precisionDescriber)`  
  Swipe element with parameters  
  `swipe().element("ANDROID_RECYCLER_VIEW", SwipeSpeed.FAST, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);`

- `element(..., int iteration)`  
  Swipe element multiple times  
  `swipe().element("ANDROID_RECYCLER_VIEW", SwipeSpeed.FAST, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER, 5);`

---

### 🔹 Swipe To

- `element(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed)`  
  Swipe to element using default coordinates  
  `swipeTo().element("ANDROID_ELEMENT", "SWIPE_LOCATOR", SwipeSpeed.FAST);`

- `element(..., Coordinates start, Coordinates end)`  
  Swipe to element with custom coordinates  
  `swipeTo().element("ANDROID_ELEMENT", "SWIPE_LOCATOR", SwipeSpeed.FAST, Coordinates.RIGHT, Coordinates.LEFT);`

---

### 🔹 Tap

- `element(String elementLocator)`  
  Tap element  
  `tap().element("ANDROID_ELEMENT");`

- `element(String elementLocator, int index)`  
  Tap element at index  
  `tap().element("ANDROID_ELEMENT", 2);`

- `element(..., SwipeSpeed swipeSpeed)`  
  Swipe and tap element  
  `tap().element("ANDROID_ELEMENT", "SWIPE_LOCATOR", SwipeSpeed.FAST);`

- `element(..., int index, ..., SwipeSpeed swipeSpeed)`  
  Swipe and tap element at index  
  `tap().element("ANDROID_ELEMENT", 2, "SWIPE_LOCATOR", SwipeSpeed.FAST);`

- `location(int x, int y, Duration time)`  
  Tap at coordinates  
  `tap().location(x - 100, y + 30, Duration.ofSeconds(1));`

---

### 🔹 Type

- `element(String elementLocator, String text)`  
  Type text  
  `type().element("ANDROID_ELEMENT", "Text to type");`

- `element(..., int index)`  
  Type text at index  
  `type().element("ANDROID_ELEMENT", "Text to type", 1);`

- `element(..., String swipeLocator, String text)`  
  Swipe and type  
  `type().element("ANDROID_ELEMENT", "SWIPE_LOCATOR", "Text to type");`

- `element(..., String swipeLocator, String text, int index)`  
  Swipe and type at index  
  `type().element("ANDROID_ELEMENT", "SWIPE_LOCATOR", "Text to type", 1);`

> ℹ️ Includes clear field and hide keyboard

---

### 🔹 Validate Toast

- `exist(String text, boolean isRegexp)`  
  Validate toast presence  
  `validateToast().exist("This is toast!", false);`  
  `validateToast().exist("^This is toast with regex!$", true);`

---

### 🔹 View Pager

Scroll directions: `FIRST`, `LAST`, `LEFT`, `RIGHT`

- `scrollTo(String elementLocator, ScrollDirection direction, boolean smoothScroll)`  
  Scroll view pager  
  `viewPager().scrollTo("VIEW_PAGER_ELEMENT", ScrollDirection.LEFT, true);`

- `scrollTo(..., int iteration)`  
  Scroll multiple times  
  `viewPager().scrollTo("VIEW_PAGER_ELEMENT", ScrollDirection.RIGHT, false, 3);`

- `scrollToPage(String elementLocator, int page)`  
  Scroll to specific page  
  `viewPager().scrollToPage("VIEW_PAGER_ELEMENT", 2);`

---

### 🔹 UiAutomator

Use for external apps (Camera, Gallery, etc.)

- `sauce(Strategy strategy, String locator, Action action)`  
  `uiAutomator().sauce(Strategy.res, "button_id", Action.click);`

- `sauce(..., int index)`  
  `uiAutomator().sauce(Strategy.text, "Login", Action.click, 1);`

> ℹ️ Returns `OBJECT` — process the result accordingly

---

### 🔹 Web Atoms

Use for interacting with WebView content

- `click(String webViewLocator, WebLocator locator, String webLocator)`  
  `webAtoms().click("ANDROID_WEB_VIEW", WebLocator.XPATH, "WEB_ELEMENT_LOCATOR");`

- `clear(...)`  
  `webAtoms().clear("ANDROID_WEB_VIEW", WebLocator.XPATH, "WEB_ELEMENT_LOCATOR");`

- `type(..., String text)`  
  `webAtoms().type("ANDROID_WEB_VIEW", WebLocator.XPATH, "WEB_ELEMENT_LOCATOR", "Text to type");`

- `findElement(...)`  
  `webAtoms().findElement("ANDROID_WEB_VIEW", WebLocator.ID, "WEB_ELEMENT_LOCATOR");`

- `scrollIntoView(...)`  
  `webAtoms().scrollIntoView("ANDROID_WEB_VIEW", WebLocator.CLASS_NAME, "WEB_ELEMENT_LOCATOR");`

> ℹ️ `WEB_ELEMENT_LOCATOR` uses raw XPath or selector — no prefix like `id_` or `text_`  
> Example: `//*[text()='LOGIN']`
