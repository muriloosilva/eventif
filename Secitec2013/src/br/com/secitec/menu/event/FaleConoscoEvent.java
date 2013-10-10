package br.com.secitec.menu.event;

import com.google.gwt.event.shared.GwtEvent;

public class FaleConoscoEvent extends GwtEvent<FaleConoscoEventHandler> {
  public static Type<FaleConoscoEventHandler> TYPE = new Type<FaleConoscoEventHandler>();
  private static String name = "";
  public FaleConoscoEvent(String name){
	  FaleConoscoEvent.name = name;
  }
  
  public static String getNameEvent(){
	  return name;
  }
  
  @Override
  public Type<FaleConoscoEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(FaleConoscoEventHandler handler) {
    handler.onFaleConosco(this);
  }
}
