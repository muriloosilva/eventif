package br.com.secitec.menu.event;

import com.google.gwt.event.shared.GwtEvent;

public class InscricaoEvent extends GwtEvent<InscricaoEventHandler> {
  public static Type<InscricaoEventHandler> TYPE = new Type<InscricaoEventHandler>();
  private static String name = "";
  public InscricaoEvent(String name){
	  InscricaoEvent.name = name;
  }
  
  public static String getNameEvent(){
	  return name;
  }
  
  @Override
  public Type<InscricaoEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(InscricaoEventHandler handler) {
    handler.onInscricao(this);
  }
}
