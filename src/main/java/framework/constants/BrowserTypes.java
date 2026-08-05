package framework.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BrowserTypes {

    EDGE("Edge"),
    SAFARI("Safari"),
    FIREFOX("Firefox"),
    CHROME("Chrome");

    String browserName;
}
