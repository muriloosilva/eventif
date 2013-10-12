package br.com.secitec.menu.event;

import com.google.gwt.event.shared.GwtEvent;

public class SobreEvent extends GwtEvent<SobreEventHandler> {
  public static Type<SobreEventHandler> TYPE = new Type<SobreEventHandler>();
  private static String name = "";
  public SobreEvent(String name){
	  SobreEvent.name = name;
  }
  
  public static String getNameEvent(){
	  return name;
  }
  
  @Override
  public Type<SobreEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SobreEventHandler handler) {
    handler.onSobre(this);
  }
}
