package server.asyncrequesthandler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ObserverSocketManager implements Runnable {

	private Queue<String> buffer = new LinkedList<>();
	DataOutputStream out;

	public ObserverSocketManager(Socket socket) {
		try {
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			writeData(out);
			sleep();
		}
	}

	private void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void writeData(DataOutputStream out) {
		try {
			while (!buffer.isEmpty()) {
				out.writeUTF(buffer.poll() + '\n');
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public void sendAsync(String data) {
		buffer.add(data);
	}

}
