package br.com.secitec.menu.event;

import com.google.gwt.event.shared.GwtEvent;

public class AdminEvent extends GwtEvent<AdminEventHandler> {
  public static Type<AdminEventHandler> TYPE = new Type<AdminEventHandler>();
  private static String name = "";
  public AdminEvent(String name){
	  AdminEvent.name = name;
  }
  
  public static String getNameEvent(){
	  return name;
  }
  
  @Override
  public Type<AdminEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AdminEventHandler handler) {
    handler.onAdmin(this);
  }
}
