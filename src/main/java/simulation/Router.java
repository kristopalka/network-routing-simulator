package simulation;

import java.util.ArrayList;
import java.util.Queue;

public class Router {
    private int routerID;
    private ArrayList<Socket> sockets;
    private Queue<Package> interganBuffer;
    private Console console;

    private void showConsole() {
        console.setVisible(true);
    }

}
