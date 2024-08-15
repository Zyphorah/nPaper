public interface Hologram {	
	void delete();

	String getMessage();

	void setMessage(String paramString);

	Location getLocation();

	void setMessageHandler(MessageHandler paramMessageHandler);
	
	Hologram addLineBelow(String text);

	public static interface MessageHandler {
		String getMessage(Player param1Player, String param1String);
	}
}