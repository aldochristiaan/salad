### Espresso Modules

- Drawer

    - `open(String elementLocator)`
    
        Open Application Drawer
        
        `drawer().open("DRAWER_LOCATOR");`
    
    - `close(String elementLocator)`
    
        Close Application Drawer
        
        `drawer().close("DRAWER_LOCATOR");`

- Flash

    - `element(String elementLocator, int durationMillis, int repeatCount);`
    
        Flash an element to make sure it's displayed on the screen
        
        `flash().element("SUBMIT_BUTTON",  "1000", 5);`

- Get Element
- Get Multiple Element
- Multiple Tap
- Navigate
- Swipe
- Swipe To
- Tap
- Type
- UiAutomator
- Validate Toast
- View Pager
- Web Atoms

