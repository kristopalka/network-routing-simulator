package layout.components.router;

import java.util.ArrayList;

public class PC extends Router
{
    public PC(String routerID)
    {
        super(routerID, new ArrayList<String>()
        {{
            add("blue");
        }});
    }

}
