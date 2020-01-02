package br.com.poli.view;

public enum Layout {
	CLASSIC("/br/com/poli/resources/classicLayout.jpg"),
	RETRO ("/br/com/poli/resources/retroLayout.jpg"),
	FUTURISTIC("/br/com/poli/resources/futuristicLayout.jpg"),
	USER("/br/com/poli/resources/userLayout.jpg");

	private final String url;

	Layout(String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	public static Layout gameLayout(String s){
		switch(s){
		case "CLASSIC":
			return Layout.CLASSIC;
		case "RETRO":
			return Layout.RETRO;
		case "FUTURISTIC":
			return Layout.FUTURISTIC;
		case "USER":
			return Layout.USER;
		default:
			return Layout.CLASSIC;
		}
	}
}
