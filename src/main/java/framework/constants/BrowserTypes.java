package framework.constants;

//Introduced in JDK 1.5 --> Enums are a special type of class that represents a set of constants
public enum BrowserTypes {

    //Every enum value is of static final object
    CHROME, //static final BrowserTypes.CHROME --> BrowserTypes.CHROME.name() --> "CHROME"
    FIREFOX, //static final BrowserTypes.FIREFOX --> BrowserTypes.FIREFOX.name() --> "FIREFOX"
    EDGE, //static final BrowserTypes.EDGE --> BrowserTypes.EDGE.name() --> "EDGE"
    SAFARI; //static final BrowserTypes.SAFARI --> BrowserTypes.SAFARI.name() --> "SAFARI"

}
