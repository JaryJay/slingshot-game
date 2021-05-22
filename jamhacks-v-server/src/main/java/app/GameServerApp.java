package app;

import network.GameServerFacade;
import server.asyncrequesthandler.GameServer;
import server.asyncrequesthandler.ServerObserverNotifierDecorator;

public class GameServerApp {

	public static void main(String[] args) {
		GameServer server = new GameServer();
		ServerObserverNotifierDecorator observerNotifierDecorator = new ServerObserverNotifierDecorator(server);
		new Thread(observerNotifierDecorator).start();
		GameServerFacade facade = new GameServerFacade(observerNotifierDecorator);
		server.run();
		facade.init();
		new Thread(facade).start();
	}

}
