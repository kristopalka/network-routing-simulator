package layout.components.router;

import java.util.ArrayList;

public class Router4 extends Router
{
    public Router4(String routerID)
    {

        super(routerID, new ArrayList<String>()
        {{
            add("blue");
            add("green");
            add("red");
            add("yellow");
        }});

    }
}
