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
    
        Return AndroidElement with given locator for specified timeout
        
        `getElement().withLocator("ANDROID_ELEMENT", 5);`


- Get Multiple Element

    - `withLocator(String elementLocator)`
        
        Return List<AndroidElement> with given locator
            
        `getMultipleElement().withLocator("ANDROID_DRAWER");`
        
    - `withLocator(String elementLocator, int timeout)`
    
        Return List<AndroidElement> with given locator for specified timeout
        
        `getMultipleElement().withLocator("ANDROID_DRAWER", 5);`
        
- Multiple Tap

    - `element(String elementLocator, int count)`
    
        Tap element several times
        
        `multipleTap().element("ANDROID_FLOATING_ACTION_BUTTON", 5);`
        
    - `element(String elementLocator, int count)`
        
        Tap element at specified index several times
            
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
        
        Tap element at specified index

        `tap().element("ANDROID_ELEMENT", 2);`
        
    - `element(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed)`
    
        Automatically swipe and tap element
        
        `tap().element("ANDROID_ELEMENT", "SWIPE_LOCATOR", SwipeSpeed.FAST);`
        
    - `element(String elementLocator, int index, String swipeLocator, SwipeSpeed swipeSpeed)`
        
        Automatically swipe and tap element at specified index
        
        `tap().element("ANDROID_ELEMENT", 2, "SWIPE_LOCATOR", SwipeSpeed.FAST);`
        
    - `location(int x, int y, Duration time)`
    
        Tap element at specified coordinates
        
        `tap().location(x - 100, y + 30, Duration.ofSeconds(1));`
        
- Type


- UiAutomator
- Validate Toast
- View Pager
- Web Atoms

