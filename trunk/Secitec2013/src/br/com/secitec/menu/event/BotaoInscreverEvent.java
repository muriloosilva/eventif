package br.com.secitec.menu.event;

import com.google.gwt.event.shared.GwtEvent;

public class BotaoInscreverEvent extends GwtEvent<BotaoInscreverEventHandler> {
  public static Type<BotaoInscreverEventHandler> TYPE = new Type<BotaoInscreverEventHandler>();
  private static String name = "";
  public BotaoInscreverEvent(String name){
	  BotaoInscreverEvent.name = name;
  }
  
  public static String getNameEvent(){
	  return name;
  }
  
  @Override
  public Type<BotaoInscreverEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(BotaoInscreverEventHandler handler) {
    handler.onBotaoInscerver(this);
  }
}
