package layout.components.routerTypes;

import java.util.ArrayList;

public class Router3 extends Router
{
    public Router3(String routerID)
    {
        super(routerID, new ArrayList<String>()
        {{
            add("blue");
            add("green");
            add("red");
        }});

    }
}
