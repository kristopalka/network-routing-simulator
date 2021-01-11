package simulation;

import simulation.Router.Layout;
import simulation.Router.Router;

public class Main
{
    public static void main(String[] args)
    {
        Router r1 = new Router("r1", 5);
        Router r2 = new Router("r2", 2);

        Layout l = new Layout();
        l.addRouter(r1);
        l.addRouter(r2);



        String log = "";

        log += l.connect(r1.getSocket("0"), r2.getSocket("0"));
        log += l.disconnect(r1.getSocket("0"), r2.getSocket("0"));
        log += l.connect(r1.getSocket("0"), r2.getSocket("1"));


        System.out.println(log);


    }
}
