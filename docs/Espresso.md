### Espresso Modules

- Drawer

    - `open(String elementLocator)`
    
        Open Application Drawer
        
        `drawer().open("ANDROID_DRAWER");`
    
    - `close(String elementLocator)`
    
        Close Application Drawer
        
        `drawer().close("ANDROID_DRAWER");`

- Flash

    - `element(String elementLocator, int durationMillis, int repeatCount)`
    
        Flash an element to make sure it's displayed on the screen
        
        `flash().element("ANDROID_ELEMENT", 500, 4);`

- Get Element

    - `withLocator(String elementLocator)`
    
        Return AndroidElement with given locator
        
        `getElement().withLocator("ANDROID_ELEMENT");`
    
    - `withLocator(String elementLocator, int timeout)`
    
        Return AndroidElement with given locator for specific timeout
        
        `getElement().withLocator("ANDROID_ELEMENT", 5);`


- Get Multiple Element

    - `withLocator(String elementLocator)`
        
        Return List<AndroidElement> with given locator
            
        `getMultipleElement().withLocator("ANDROID_DRAWER");`
        
    - `withLocator(String elementLocator, int timeout)`
    
        Return List<AndroidElement> with given locator for specific timeout
        
        `getMultipleElement().withLocator("ANDROID_DRAWER", 5);`
        
- Multiple Tap

    - `element(String elementLocator, int count)`
    
        Tap element several times
        
        `multipleTap().element("ANDROID_FLOATING_ACTION_BUTTON", 5);`
        
    - `element(String elementLocator, int count)`
        
        Tap element at specific index several times
            
        `multipleTap().element("ANDROID_FLOATING_ACTION_BUTTON", 5);`

- Swipe
    
    - `element(String elementLocator, SwipeSpeed swipeSpeed, Coordinates startCoordinates, Coordinates endCoordinates, PrecisionDescriber precisionDescriber)`
    
        Swipe Android Element based on given parameter
        
        `swipe().element("ANDROID_RECYCLER_VIEW", SwipeSpeed.FAST, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);`
        
    - `element(String elementLocator, SwipeSpeed swipeSpeed, Coordinates startCoordinates, Coordinates endCoordinates, PrecisionDescriber precisionDescriber, int iteration))`
    
        Swipe Android Element based on given parameter several times
        
       `swipe().element("ANDROID_RECYCLER_VIEW", SwipeSpeed.FAST, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER, 5);`

- Swipe To

    - `element(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed)`
        
        Swipe to Android Element by swiping `SWIPE_LOCATOR` with default coordinates from center to top
       
       `swipeTo().element("ANDROID_ELEMENT", "SWIPE_LOCATOR", SwipeSpeed.FAST);`
       
    - `element(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed, Coordinates startCoordinate, Coordinates endCoordinate)`
        
        Swipe to Android Element by swiping `SWIPE_LOCATOR` by defining Coordinates
        
       `swipeTo().element("ANDROID_ELEMENT", "SWIPE_LOCATOR", SwipeSpeed.FAST, Coordinates.RIGHT, Coordinates.LEFT);`

- Tap

    - `element(String elementLocator)`
    
        Tap element
        
        `tap().element("ANDROID_ELEMENT");`
        
    - `element(String elementLocator, int index)`
        
        Tap element at specific index

        `tap().element("ANDROID_ELEMENT", 2);`
        
    - `element(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed)`
    
        Automatically swipe and tap element
        
        `tap().element("ANDROID_ELEMENT", "SWIPE_LOCATOR", SwipeSpeed.FAST);`
        
    - `element(String elementLocator, int index, String swipeLocator, SwipeSpeed swipeSpeed)`
        
        Automatically swipe and tap element at specific index
        
        `tap().element("ANDROID_ELEMENT", 2, "SWIPE_LOCATOR", SwipeSpeed.FAST);`
        
    - `location(int x, int y, Duration time)`
    
        Tap element at specific coordinates
        
        `tap().location(x - 100, y + 30, Duration.ofSeconds(1));`
        
- Type

    - `element(String elementLocator, String text)`
       
       Type text on element
       
       `type().element("ANDROID_ELEMENT, "Text to type"`
       
    - `element(String elementLocator, String text, int index)`
              
       Type text on specific element at index
              
       `type().element("ANDROID_ELEMENT, "Text to type"`
       
    - `element(String elementLocator, String swipeLocator, String text)`
       
       Automatically swipe and type text on element
       
       `type().element("ANDROID_ELEMENT, "Text to type"`
       
    - `element(String elementLocator, String swipeLocator, String text, int index)`
              
       Automatically swipe and type text on specific element at index
              
       `type().element("ANDROID_ELEMENT, "Text to type"`
       
    NB : Includes clear field and hide keyboard

- Validate Toast

    - `exist(String text, boolean isRegexp)`
    
       Check whether toast is exist on page, you can pass text / regex
       
       `validateToast().exist("This is toast!", false);`
       
       `validateToast().exist("^This is toast with regex!$", true);`
        
        
- View Pager

    Scroll Direction : `FIRST, LAST, LEFT, RIGHT`

    - `scrollTo(String elementLocator, ScrollDirection scrollDirection, boolean smoothScroll)`
        
        Scroll view pager
        
        `viewPager().scrollTo("VIEW_PAGER_ELEMENT", ScrollDirection.LEFT, true);`
        
        `viewPager().scrollTo("VIEW_PAGER_ELEMENT", ScrollDirection.LAST, false);`
        
    - `scrollTo(String elementLocator, ScrollDirection scrollDirection, boolean smoothScroll, int iteration)`
            
        Scroll view pager multiple times
        
        `viewPager().scrollTo("VIEW_PAGER_ELEMENT", ScrollDirection.LEFT, true, 2);`

        `viewPager().scrollTo("VIEW_PAGER_ELEMENT", ScrollDirection.RIGHT, false, 3);`
        
    - `scrollToPage(String elementLocator, int page)`
                
        Scroll view pager to specific page
        
        `viewPager().scrollToPage("VIEW_PAGER_ELEMENT", 2);`
            
    
- UiAutomator

    Use this module to interact with element outside Application Under Test ( Camera, Gallery, 3rd party App )
    
    -  sauce(Strategy strategy, String locator, Action action)
    
        `uiAutomator().sauce(Strategy.res, "button_id", Action.click);`
        
        `uiAutomator().sauce(Strategy.textContains, "Come here", Action.getClassName);`
        
    -  sauce(Strategy strategy, String locator, Action action, int index)
    
        `uiAutomator().sauce(Strategy.text, "Login", Action.click, 1);`
    
    If you need response from uiautomator method, it will return `OBJECT`, so make sure you process the value first.

- Web Atoms

    Use this module to interact with element inside Web View
    
    -   `click(String webViewLocator, WebLocator locator, String webLocator)`
    
         Click web element
    
        `webAtoms().click("ANDROID_WEB_VIEW", WebLocator.XPATH, "WEB_ELEMENT_LOCATOR");`
        
    -   `clear(String webViewLocator, WebLocator locator, String webLocator)`
    
         Clear web text field element 
    
        `webAtoms().clear("ANDROID_WEB_VIEW", WebLocator.XPATH, "WEB_ELEMENT_LOCATOR");`
        
    -   `type(String webViewLocator, WebLocator locator, String webLocator, String text)`
    
         Type text into web text field element
    
        `webAtoms().type("ANDROID_WEB_VIEW", WebLocator.XPATH, "WEB_ELEMENT_LOCATOR", "Text to type");`
        
    -   `findElement(String webViewLocator, WebLocator locator, String webLocator)`
    
         Find web element to make sure element loaded
    
        `webAtoms().findElement("ANDROID_WEB_VIEW", WebLocator.ID, "WEB_ELEMENT_LOCATOR", "Text to type");`
        
    -   `scrollIntoView(String webViewLocator, WebLocator locator, String webLocator)`
    
         Scroll to web element
    
        `webAtoms().findElement("ANDROID_WEB_VIEW", WebLocator.CLASS_NAME, "WEB_ELEMENT_LOCATOR", "Text to type");`
        
    NB : `WEB_ELEMENT_LOCATOR` have different value from normal mobile locator, it doesn't have locator in front of the element locator
    
    Example : `WEB_ELEMENT_LOCATOR=//*[text()='LOGIN']`