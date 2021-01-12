package layout.router;

import java.util.ArrayList;
import java.util.Arrays;

public class PC extends Router
{
    public PC(String routerID)
    {
        super(routerID, new ArrayList<String>()
        {{
            add("blue");
            add("yellow");
        }});
    }

}
