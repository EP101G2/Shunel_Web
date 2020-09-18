package Chat;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import DAO.Chat_DAO;
import DAO_Interface.Chat_DAO_InterFace;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/TwoChatServer/{userName}")
public class TwoChatServer {
	Chat_DAO cDao = null;
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		
		System.out.println("================="+userName);
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
		StateMessage stateMessage = new StateMessage("open", userName, userNames);

		String stateMessageJson = gson.toJson(stateMessage);

		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userName, userNames);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		int count = 0;
		int imageID;
		
		byte[] image;
		if (cDao == null) {
			cDao = new Chat_DAO_InterFace();
		}
		
		
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);

		String receiver = chatMessage.getReceiver();

		Session receiverSession = sessionsMap.get(receiver);
		
		if (chatMessage.getType().equals("chat")) {
			imageID = cDao.insert(chatMessage.getChatRoom(), chatMessage.getMessage(), chatMessage.getReceiver(), chatMessage.getSender(), chatMessage.getType(),null);
			
			if (receiverSession != null && receiverSession.isOpen()) {
				receiverSession.getAsyncRemote().sendText(message);
				sessionsMap.remove("receiver");
				
			}
		}else {
			image = Base64.getMimeDecoder().decode(chatMessage.getBase64());
		imageID = cDao.insert(chatMessage.getChatRoom(), chatMessage.getMessage(), chatMessage.getReceiver(), chatMessage.getSender(), chatMessage.getType(), image);
			if (imageID != 0) {
				chatMessage.setBase64(String.valueOf(imageID));
				if (receiverSession != null && receiverSession.isOpen()) {
					receiverSession.getAsyncRemote().sendText(gson.toJson(chatMessage));
					sessionsMap.remove("receiver");
				}
			}
		}
		
		
		
	
		System.out.println("Message received: " + message);

	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			StateMessage stateMessage = new StateMessage("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
