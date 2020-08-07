package Servlet_Shunel;
import java.io.IOException;
import java.util.List;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
public class FirebaseCloudMsg {
	private FirebaseOptions options;

	private static FirebaseCloudMsg fcm = new FirebaseCloudMsg();

	public static FirebaseCloudMsg getInstance() {
		return fcm;
	}

	private FirebaseCloudMsg() {
		if (options == null) {
			try {
				options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.getApplicationDefault())
						.setDatabaseUrl("https://shunel-e543b.firebaseio.com").build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FirebaseApp.initializeApp(options);
		}
	}
	
    //單推
	public void FCMsendMsg(String token, String title, String msg,int Flag) {


		Message message = Message.builder().putData("title", title).putData("msg", msg).putData("flag", String.valueOf(Flag)).setToken(token).build();

		// Send a message to the device corresponding to the provided
		// registration token.
		try {
			String FcmResponse = FirebaseMessaging.getInstance().send(message);

			// Response is a message ID string.
			System.out.println("Successfully sent message: " + FcmResponse);
		} catch (FirebaseMessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//群推
	public void FCMsendMsgMuti(List<String> registrationTokens,String title, String msg,int Flag ) throws FirebaseMessagingException {

		
		MulticastMessage message = MulticastMessage.builder()
			    .putData("title", title)
			    .putData("msg", msg)
			    .putData("flag", String.valueOf(Flag))
			    .addAllTokens(registrationTokens)
			    .build();
		
		
		
			BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
			// See the BatchResponse reference documentation
			// for the contents of response.
			System.out.println(response.getSuccessCount() + " messages were sent successfully");
	}

}

