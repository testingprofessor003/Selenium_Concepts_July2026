package framework.constants;

//Introduced in JDK 1.5 --> Enums are a special type of class that represents a set of constants
public enum BrowserTypes {

    //Every enum value is of static final object
    CHROME, //BrowserTypes.CHROME --> BrowserTypes.CHROME.name() --> "CHROME"
    FIREFOX, //BrowserTypes.FIREFOX --> BrowserTypes.FIREFOX.name() --> "FIREFOX"
    EDGE, //BrowserTypes.EDGE --> BrowserTypes.EDGE.name() --> "EDGE"
    SAFARI; //BrowserTypes.SAFARI --> BrowserTypes.SAFARI.name() --> "SAFARI"

}
