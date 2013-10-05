package br.com.secitec.menu.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginEventHandler> {
  public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();
  private static String name = "";
  public LoginEvent(String name){
	  LoginEvent.name = name;
  }
  
  public static String getNameEvent(){
	  return name;
  }
  
  @Override
  public Type<LoginEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(LoginEventHandler handler) {
    handler.onLogin(this);
  }
}
